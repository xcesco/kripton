/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java String object
 * 
 * @author xcesco
 *
 */
public class StringBindTransform extends AbstractBindTransform {

	public boolean isTypeAdapterSupported() {
		return true;
	}
	
	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		
		if (property.hasTypeAdapter())
		{
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);
		
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+"$L.getText()"+POST_TYPE_ADAPTER), TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz),parserName);
		
		} else {
			// there's no type adapter
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getText()"), parserName);
		}
		
		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		
		if (property.hasTypeAdapter())
		{
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);
		
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+"$L.getText()"+POST_TYPE_ADAPTER), TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz),parserName);
		
		} else {
			// there's no type adapter
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getText()"), parserName);
		}
		
		methodBuilder.endControlFlow();
	}

	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		if (property.hasTypeAdapter())
		{
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);			
			
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+"$T.unescapeXml($L.getAttributeValue(attributeIndex))"+POST_TYPE_ADAPTER), TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), StringEscapeUtils.class, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+"$T.unescapeXml($L.getElementText())"+POST_TYPE_ADAPTER), TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+"$T.unescapeXml($L.getText())"+POST_TYPE_ADAPTER), TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), StringEscapeUtils.class, parserName);
				break;		
			}
		
		} else {
			// there's no type adapter
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.unescapeXml($L.getAttributeValue(attributeIndex))"), StringEscapeUtils.class, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.unescapeXml($L.getElementText())"), StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.unescapeXml($L.getText())"), StringEscapeUtils.class, parserName);
				break;		
			}
		}

	}

	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (property.isProperty())
		{
			methodBuilder.addStatement("fieldCount++");
		}
		
		if (property.hasTypeAdapter())
		{
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);
			
			if (property.isInCollection()) {
				// we need to write only value
				methodBuilder.addStatement("$L.writeString("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName,  TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, "+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, property.label, TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		} else {
			// there's no type adapter
			
			if (property.isInCollection()) {
				// we need to write only value
				methodBuilder.addStatement("$L.writeString($L)", serializerName, getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, $L)", serializerName, property.label, getter(beanName, beanClass, property));
			}
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(context, methodBuilder, serializerName, beanClass, beanName, property);
	}

	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (!property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null)", getter(beanName, beanClass, property));
		}

		XmlType xmlType = property.xmlInfo.xmlType;
		
		if (property.hasTypeAdapter())
		{
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);
			
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.escapeXml10("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, property.label, StringEscapeUtils.class, TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, StringEscapeUtils.class, TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, StringEscapeUtils.class, TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.escapeXml10("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, StringEscapeUtils.class, TypeAdapterUtils.class, typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			}
		} else {
			// there's no type adapter
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.escapeXml10($L))", serializerName, property.label, StringEscapeUtils.class, getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.escapeXml10($L))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property));
				break;
			}
		}

		if (!property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
	}
}
