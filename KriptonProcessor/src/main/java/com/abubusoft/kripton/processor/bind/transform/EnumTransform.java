/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
public class EnumTransform extends AbstractBindTransform {

	private TypeName typeName;

	public EnumTransform(TypeName typeName) {
		this.typeName = typeName;
		
		METHOD_TO_CONVERT="toString";				
	}

	protected String METHOD_TO_CONVERT;
	
	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		if (property.isNullable() && !property.isElementInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $L.$L())", serializerName, property.xmlInfo.tag, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L.$L()))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);						
			break;
		case VALUE:
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L.$L()))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData($T.escapeXml10($L.$L()))", serializerName, StringEscapeUtils.class, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
			break;
		}

		if (property.isNullable() && !property.isElementInCollection()) {
			methodBuilder.endControlFlow();
		}

	}
	
	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
//		if (property.isElementInCollection())
//		{
//			methodBuilder.addStatement("$L.writeString($L.$L())", serializerName, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
//		} else 
		{
			methodBuilder.addStatement("$L.writeStringField($S, $L.$L())", serializerName, property.jacksonName, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
		}
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.valueOf($L.getAttributeValue(attributeIndex))"), typeName, parserName);
			break;
		case TAG:			
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.valueOf($T.unescapeXml($L.getElementText()))"), typeName, StringEscapeUtils.class, parserName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.valueOf($T.unescapeXml($L.getText()))"), typeName, StringEscapeUtils.class, parserName);			
			break;
		default:
			break;
		}

	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(methodBuilder, serializerName, beanClass, beanName, property);
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		} else {
			methodBuilder.beginControlFlow("");
		}
		
		methodBuilder.addStatement("String tempEnum=$L.getText()", parserName);
		methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.hasText(tempEnum)?$T.valueOf(tempEnum):null"), StringUtility.class, typeName);
		
		methodBuilder.endControlFlow();
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJackson(methodBuilder, parserName, beanClass, beanName, property);
	}
		
}