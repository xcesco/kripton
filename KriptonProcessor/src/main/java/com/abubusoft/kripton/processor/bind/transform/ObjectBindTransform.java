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
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer for generic object. For XML binding no Attribute o Value is allowed.  
 * 
 * @author bulldog
 *
 */
public class ObjectBindTransform extends AbstractBindTransform {

	protected Class<?> utilClazz;

	public ObjectBindTransform() {
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnXml(context, wrapper, eventType)"), property.getPropertyType().getName());		
		
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {		
		//@formatter:off
		if (property.isNullable() && !property.isInCollection())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
		methodBuilder.addStatement("context.mapperFor($T.class).serializeOnXml(context, $L, wrapper, $L)", property.getPropertyType().getName(), getter(beanName, beanClass, property), XMLEvent.START_ELEMENT);
		methodBuilder.addStatement("$L.writeEndElement()", serializerName);				
				
		if (property.isNullable() && !property.isInCollection())
		{
			methodBuilder.endControlFlow();
		}
		//@formatter:on
	}
		
	void generateSerializeInternal(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		//@formatter:off
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		if (property.isProperty())
		{
			methodBuilder.addStatement("fieldCount++");
		}
		
		if (!property.isInCollection())
		{
			methodBuilder.addStatement("$L.writeFieldName($S)",serializerName, property.jacksonInfo.jacksonName);
		}
		if (onString)
		{
			methodBuilder.beginControlFlow("if (context.mapperFor($T.class).serializeOnJacksonAsString(context, $L, wrapper)==0)", property.getPropertyType().getName(), getter(beanName, beanClass, property));
			//methodBuilder.addStatement("$L.writeNullField($S)",serializerName, property.jacksonName);
			// KRITPON-38: in a collection, for null object we write 
			methodBuilder.addStatement("$L.writeNullField($S)",serializerName, property.jacksonInfo.jacksonName);
			methodBuilder.endControlFlow();
		} else
		{
			methodBuilder.addStatement("context.mapperFor($T.class).serializeOnJackson(context, $L, wrapper)", property.getPropertyType().getName(), getter(beanName, beanClass, property));			
		}
			
		if (property.isNullable())
		{
		methodBuilder.endControlFlow();
		}
		//@formatter:on
	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeInternal(methodBuilder, serializerName, beanClass, beanName, property, false);
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeInternal(methodBuilder, serializerName, beanClass, beanName, property, true);
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_OBJECT)", parserName, JsonToken.class);
		}
		
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJackson(context, wrapper)"), property.getPropertyType().getName());
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_OBJECT)", parserName, JsonToken.class);
		}		
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJacksonAsString(context, wrapper)"), property.getPropertyType().getName());		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}
}
