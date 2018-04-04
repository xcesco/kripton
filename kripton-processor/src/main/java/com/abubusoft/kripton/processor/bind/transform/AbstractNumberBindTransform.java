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

import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

abstract class AbstractNumberBindTransform extends AbstractBindTransform {

	protected Class<?> NUMBER_UTIL_CLAZZ;
	protected String ATTRIBUTE_METHOD;

	public AbstractNumberBindTransform() {
		NUMBER_UTIL_CLAZZ = BigDecimalUtils.class;

	}

	@Override
	public boolean isTypeAdapterSupported() {
		return true;
	}

	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($L.getText())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
					TypeUtility.typeName(property.typeAdapter.adapterClazz), NUMBER_UTIL_CLAZZ, parserName);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"), NUMBER_UTIL_CLAZZ, parserName);
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($L.getText())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
					TypeUtility.typeName(property.typeAdapter.adapterClazz), NUMBER_UTIL_CLAZZ, parserName);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"), NUMBER_UTIL_CLAZZ, parserName);
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.hasTypeAdapter()) {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L.getAttributeAs$L(attributeIndex)" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), parserName, ATTRIBUTE_METHOD);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($T.unescapeXml($L.getElementText()))" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($T.unescapeXml($L.getText()))" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
				break;
			}
		} else {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getAttributeAs$L(attributeIndex)"), parserName, ATTRIBUTE_METHOD);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getElementText()))"), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getText()))"), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
				break;
			}
		}

	}

	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (property.hasTypeAdapter()) {
			// if in collection, we need to write only the value
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.writeString($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + "))", serializerName, NUMBER_UTIL_CLAZZ, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, $T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + "))", serializerName, property.label, NUMBER_UTIL_CLAZZ,
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		} else {
			// if in collection, we need to write only the value
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.writeString($T.write($L))", serializerName, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, $T.write($L))", serializerName, property.label, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
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
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.hasTypeAdapter()) {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.write$LAttribute(null, null,$S, " + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")", serializerName, ATTRIBUTE_METHOD, property.label,
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class,
						NUMBER_UTIL_CLAZZ, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class,
						NUMBER_UTIL_CLAZZ, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class,
						NUMBER_UTIL_CLAZZ, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			}
		} else {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.write$LAttribute(null, null,$S, $L)", serializerName, ATTRIBUTE_METHOD, property.label, getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
				break;
			}
		}

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}

	}
}
