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
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
abstract class PrimitiveBindTransform extends AbstractBindTransform {
	
	

	public PrimitiveBindTransform(boolean nullable)
	{
		this.nullable=nullable;
	}
	
	protected boolean nullable;
	
	protected String XML_TYPE;
	protected String XML_UTILITY_TYPE;
	protected String XML_CAST_TYPE="";	
	
	protected String JSON_TYPE;
	protected String JSON_PARSER_METHOD;
	

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		switch (xmlType) {
		case ATTRIBUTE:
			String nullValue=nullable?"null": "\'\\0\'";			
			if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE))
			{
				methodBuilder.addStatement("$L."+setter(beanClass, property,"attributeValue.length()>0 ? attributeValue.charAt(0) : $L"), beanName, nullValue);
			} else {
				methodBuilder.addStatement("$L."+setter(beanClass, property,"$L$L.valueOf(attributeValue)"), beanName, XML_CAST_TYPE, XML_UTILITY_TYPE);
			}
			break;
		case TAG:
			methodBuilder.addStatement("$L."+setter(beanClass, property,"$L$L.getElementAs$L()"), beanName, XML_CAST_TYPE, parserName, XML_TYPE);
			break;
		case VALUE:			
		case VALUE_CDATA:			
			methodBuilder.addStatement("$L."+setter(beanClass, property,"$L.valueOf($L.getText())"), beanName, XML_UTILITY_TYPE, parserName);
			break;
		default:
			break;
		}			
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, String.valueOf($L))", serializerName, property.xmlInfo.tag, getter(beanName, beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.write$L($L)", serializerName, XML_TYPE, getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:			
			methodBuilder.addStatement("$L.write$L($L)", serializerName, property.xmlInfo.tag, XML_TYPE, getter(beanName, beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData(String.valueOf($L))", serializerName, getter(beanName, beanClass, property));
			break;
		}
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}
	
	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
				
		methodBuilder.addStatement("$L.write$LField($S, $L)", serializerName, JSON_TYPE, property.jacksonName, getter(beanName, beanClass, property));
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}
	
	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE))
		{
			methodBuilder.addStatement("$L.writeStringField($S, String.valueOf((int)$L))", serializerName, property.jacksonName, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.writeStringField($S, String.valueOf($L))", serializerName, property.jacksonName, getter(beanName, beanClass, property));
		}
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJackson(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {				
		if (nullable)
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		
		if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE))
		{
			methodBuilder.addStatement("$L."+setter(beanClass, property,"Character.valueOf((char)$L.$L())"), beanName, parserName, JSON_PARSER_METHOD);
		} else {
			methodBuilder.addStatement("$L."+setter(beanClass, property,"$L.$L()"), beanName, parserName, JSON_PARSER_METHOD);
		}
		
		if (nullable)
		{
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable)
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		
		if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE))
		{
			methodBuilder.addStatement("$L."+setter(beanClass, property,"Character.valueOf((char)(int)Integer.valueOf($L.getText()))"), beanName, parserName);
		} else {
			methodBuilder.addStatement("$L."+setter(beanClass, property,"$L.valueOf($L.getText())"), beanName, XML_UTILITY_TYPE, parserName);
		}
		
		if (nullable)
		{
			methodBuilder.endControlFlow();
		}
	}
}
