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

import com.abubusoft.kripton.binder2.xml.XmlType;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class ByteArrayBindTransform extends AbstractBindTransform {

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, false); 

	}
	
	public void generateParseOnJacksonInternal(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}		
		
		if (onString)
		{
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.decode($L.getValueAsString())"), Base64Utils.class, parserName);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getBinaryValue()"), parserName);
		}
		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, true); 
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getAttributeAsBinary()"), parserName);
			break;
		case TAG:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getElementAsBinary()"), parserName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getElementAsBinary()"), parserName);
			break;
		default:
			break;
		}

	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (property.isProperty())
		{
			methodBuilder.addStatement("fieldCount++");
		}

		// in a collection we need to insert only value, not field name
		if (property.isInCollection()) {
			methodBuilder.addStatement("$L.writeBinary($L)", serializerName, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.writeBinaryField($S, $L)", serializerName, property.jacksonInfo.jacksonName, getter(beanName, beanClass, property));
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(methodBuilder, serializerName, beanClass, beanName, property);
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null)", getter(beanName, beanClass, property));
		}
		
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $L)", serializerName, property.xmlInfo.tag, getter(beanName, beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.writeBinary($L, 0, $L.length)", serializerName, getter(beanName, beanClass, property), getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeBinary($L, 0, $L.length)", serializerName, getter(beanName, beanClass, property), getter(beanName, beanClass, property));
			break;
		}

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
	}
}
