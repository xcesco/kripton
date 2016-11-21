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
import com.squareup.javapoet.MethodSpec.Builder;
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
	protected String JSON_TYPE;
	protected String XML_CAST_TYPE="";


	@Override
	public void generateParseOnXml(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		switch (xmlType) {
		case ATTRIBUTE:
			if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE))
			{
				methodBuilder.addStatement("$L."+setter(beanClass, property,"attributeValue.length()>0 ? attributeValue.charAt(0) : \'\\0\'"), beanName);
			} else {
				methodBuilder.addStatement("$L."+setter(beanClass, property,"$L$L.valueOf(attributeValue)"), beanName, XML_CAST_TYPE, XML_TYPE);
			}
			break;
		case TAG:
			methodBuilder.addStatement("$L."+setter(beanClass, property,"$L$L.getElementAs$L()"), beanName, XML_CAST_TYPE, parserName, XML_TYPE);
			break;
		case VALUE:			
		case VALUE_CDATA:
			methodBuilder.addCode("//TODO property $L\n",property.getName());
			break;
		default:
			break;
		}			
	}

	@Override
	public void generateSerializeOnXml(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
		}
		
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, String.valueOf($L.$L))", serializerName, property.xmlInfo.tagName, beanName, getter(beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tagName);
			methodBuilder.addStatement("$L.write$L($L.$L)", serializerName, XML_TYPE, beanName, getter(beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:			
			methodBuilder.addStatement("$L.write$L($L.$L)", serializerName, property.xmlInfo.tagName, XML_TYPE, beanName, getter(beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData(String.valueOf($L.$L))", serializerName, property.xmlInfo.tagName, beanName, getter(beanClass, property));
			break;
		}
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}
	
	@Override
	public void generateSerializeOnJackson(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
		}
				
		methodBuilder.addStatement("$L.write$LField($S, $L.$L)", serializerName, JSON_TYPE, property.jacksonName, beanName, getter(beanClass, property));
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}
	
	@Override
	public void generateSerializeOnJacksonAsString(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
		}
						
		methodBuilder.addStatement("$L.writeStringField($S, String.valueOf($L.$L))", serializerName, property.jacksonName, beanName, getter(beanClass, property));
				
		if (nullable) {
			methodBuilder.endControlFlow();
		}
	}

}
