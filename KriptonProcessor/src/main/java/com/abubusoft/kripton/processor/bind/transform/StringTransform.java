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

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class StringTransform extends AbstractBindTransform {

	public StringTransform() {
	}

	@Override
	public void generateSerializeOnXml(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, XmlType xmlType) {
		methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);

		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $L.$L)", serializerName, property.xmlInfo.tagName, beanName, getter(beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tagName);
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L.$L))", serializerName, StringEscapeUtils.class, beanName, getter(beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($L.$L))", serializerName, property.xmlInfo.tagName, StringEscapeUtils.class, beanName, getter(beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData($T.escapeXml10($L.$L))", serializerName, property.xmlInfo.tagName, StringEscapeUtils.class, beanName, getter(beanClass, property));
			break;
		}

		methodBuilder.endControlFlow();

	}
	
	@Override
	public void generateSerializeOnJackson(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, XmlType xmlType) {
		methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
		methodBuilder.addStatement("$L.writeStringField($S, $L.$L)", serializerName, property.jacksonName, beanName, getter(beanClass, property));
		methodBuilder.endControlFlow();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateSerializeOnJacksonAsString(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, XmlType xmlType) {
		methodBuilder.beginControlFlow("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
		methodBuilder.addStatement("$L.writeStringField($S, $L.$L)", serializerName, property.jacksonName, beanName, getter(beanClass, property));
		methodBuilder.endControlFlow();
	}

}
