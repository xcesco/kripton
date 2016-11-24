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

import javax.xml.stream.events.XMLEvent;

import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class ObjectTransform extends AbstractBindTransform {

	protected Class<?> utilClazz;

	public ObjectTransform() {
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {		
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnXml(context, wrapper, eventType)"), property.getElement().asType());
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {		
		//@formatter:off
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("context.mapperFor($T.class).serializeOnXml(context, $L, wrapper, $L)", beanClass, getter(beanName, beanClass, property), XMLEvent.START_ELEMENT);
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
		//@formatter:on
	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		if (property.isNullable())
		{
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (!property.isElementInCollection())
		{
			methodBuilder.addStatement("$L.writeFieldName($S)",serializerName, property.jacksonName);
		}
			methodBuilder.addStatement("context.mapperFor($T.class).serializeOnJackson(context, $L, wrapper)", beanClass, getter(beanName, beanClass, property));
			
		if (property.isNullable())
		{
		methodBuilder.endControlFlow();
		}
		//@formatter:on
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		if (property.isNullable())
		{
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (!property.isElementInCollection())
		{
			methodBuilder.addStatement("$L.writeFieldName($S)",serializerName, property.jacksonName);
		}
			methodBuilder.addStatement("context.mapperFor($T.class).serializeOnJacksonAsString(context, $L, wrapper)", beanClass, getter(beanName, beanClass, property));
			
		if (property.isNullable())
		{
		methodBuilder.endControlFlow();
		}
		//@formatter:on
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJackson(context, wrapper)"), beanClass);
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJacksonAsString(context, wrapper)"), beanClass);		
	}
}
