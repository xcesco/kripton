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

import kripton70.BeanAttribute70;

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
		switch(property.xmlInfo.xmlType)
		{
			case ATTRIBUTE:
				//context.mapperFor(BeanAttribute70.class).parse(context, xmlParser.getAttributeAsBinary(attributeIndex));
				methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parse(context, $L.getAttributeAsBinary(attributeIndex))"), property.getPropertyType().getName(), parserName);
				//methodBuilder.addStatement("$L.writeBinaryAttribute(null, null, $S, context.mapperFor($T.class).serialize(context, $L).getBytes())", serializerName, property.xmlInfo.tag, property.getPropertyType().getName(), getter(beanName, beanClass, property));
				break;
			case TAG:
//				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
//				methodBuilder.addStatement("context.mapperFor($T.class).serializeOnXml(context, $L, wrapper, $L)", property.getPropertyType().getName(), getter(beanName, beanClass, property), XMLEvent.START_ELEMENT);
//				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnXml(context, wrapper, eventType)"), property.getPropertyType().getName());
				break;
			case VALUE:
			case VALUE_CDATA:				
//				methodBuilder.addStatement("byte[] buffer=context.mapperFor($T.class).serialize(context, $L).getBytes()",property.getPropertyType().getName(), getter(beanName, beanClass, property));
//				methodBuilder.addStatement("$L.writeBinaryAttribute(null, null, $S, buffer)", serializerName, property.xmlInfo.tag);
				
				methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parse(context, $L.getElementAsBinary())"), property.getPropertyType().getName(), parserName);
				break;
		}
		//methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnXml(context, wrapper, eventType)"), property.getPropertyType().getName());
		
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {		
		//@formatter:off
		if (property.isNullable() && !property.isInCollection())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
		switch(property.xmlInfo.xmlType)
		{
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeBinaryAttribute(null, null, $S, context.mapperFor($T.class).serialize(context, $L).getBytes())", serializerName, property.xmlInfo.tag, property.getPropertyType().getName(), getter(beanName, beanClass, property));
				//xmlSerializer.writeBinaryAttribute(null, null, localName, context.mapperFor(BeanAttribute70.class).serialize(context, object.valueBean).getBytes());
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
				methodBuilder.addStatement("context.mapperFor($T.class).serializeOnXml(context, $L, wrapper, $L)", property.getPropertyType().getName(), getter(beanName, beanClass, property), XMLEvent.START_ELEMENT);
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);				
				break;
			case VALUE:
			case VALUE_CDATA:				
				methodBuilder.addStatement("byte[] buffer=context.mapperFor($T.class).serialize(context, $L).getBytes()",property.getPropertyType().getName(), getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeBinary(buffer, 0, buffer.length)", serializerName);
				break;
		}
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
		
		if (!property.isInCollection())
		{
			methodBuilder.addStatement("$L.writeFieldName($S)",serializerName, property.jacksonName);
		}
		if (onString)
		{
			methodBuilder.addStatement("context.mapperFor($T.class).serializeOnJacksonAsString(context, $L, wrapper)", property.getPropertyType().getName(), getter(beanName, beanClass, property));
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
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJackson(context, wrapper)"), property.getPropertyType().getName());
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.addStatement(setter(beanClass, beanName, property,"context.mapperFor($T.class).parseOnJacksonAsString(context, wrapper)"), property.getPropertyType().getName());		
	}
}
