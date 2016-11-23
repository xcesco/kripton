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

import java.math.BigDecimal;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

abstract class AbstractNumberTransform extends AbstractBindTransform {
	
	public AbstractNumberTransform()
	{		
		METHOD_TO_CONVERT="toPlainString";		
		NUMBER_CLAZZ=BigDecimal.class;
	}
	
	protected String METHOD_TO_CONVERT;
	
	protected Class<?> NUMBER_CLAZZ;
	
	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		if (property.isNullable())
		{
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

		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}

	}
	
	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		methodBuilder.addStatement("$L.writeStringField($S, $L.$L())", serializerName, property.jacksonName, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
		
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
			methodBuilder.addStatement("$L."+setter(beanClass, property,"new $T($T.read(attributeValue))"), beanName, NUMBER_CLAZZ, parserName);
			break;
		case TAG:
			methodBuilder.addStatement("$L."+setter(beanClass, property,"new $T($T.unescapeXml($L.getElementText()))"), beanName, NUMBER_CLAZZ, StringEscapeUtils.class, parserName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement("$L."+setter(beanClass, property,"new $T($T.unescapeXml($L.getText()))"), beanName, NUMBER_CLAZZ, StringEscapeUtils.class, parserName);			
			break;
		default:
			break;
		}

	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		methodBuilder.addStatement("$L.writeStringField($S, $L.$L())", serializerName, property.jacksonName, getter(beanName, beanClass, property), METHOD_TO_CONVERT);
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		methodBuilder.addStatement("$L."+setter(beanClass, property," new $T($L.getText())"), beanName, NUMBER_CLAZZ, parserName);
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
		
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		methodBuilder.addStatement("$L."+setter(beanClass, property,"new $T($L.getText())"), beanName, NUMBER_CLAZZ, parserName);
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}
}
