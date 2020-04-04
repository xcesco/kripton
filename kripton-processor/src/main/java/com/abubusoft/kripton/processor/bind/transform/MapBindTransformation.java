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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.MapEntryType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * The Class MapBindTransformation.
 */
public class MapBindTransformation extends AbstractBindTransform {

	/** The nc. */
	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	/**
	 * Instantiates a new map bind transformation.
	 *
	 * @param clazz the clazz
	 */
	public MapBindTransformation(ParameterizedTypeName clazz) {		
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#isTypeAdapterSupported()
	 */
	public boolean isTypeAdapterSupported() {
		return false;
	}

	/**
	 * Define map class.
	 *
	 * @param mapTypeName the map type name
	 * @return the class
	 */
	private Class<?> defineMapClass(ParameterizedTypeName mapTypeName) {
		if (mapTypeName.rawType.toString().startsWith(Map.class.getCanonicalName())) {
			// it's a list
			return HashMap.class;
		} else if (mapTypeName.rawType.toString().startsWith(SortedMap.class.getCanonicalName())) {
			return TreeMap.class;
		}
		try {
			return Class.forName(mapTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		// define key and value type
		ParameterizedTypeName mapTypeName=(ParameterizedTypeName) property.getPropertyType().getTypeName();
		TypeName keyTypeName = mapTypeName.typeArguments.get(0);
		TypeName valueTypeName = mapTypeName.typeArguments.get(1);
		
		//@formatter:off
		methodBuilder.beginControlFlow("");
		
		methodBuilder.addStatement("$T<$T, $T> collection=new $T<>()", defineMapClass(mapTypeName), keyTypeName, valueTypeName, defineMapClass(mapTypeName));
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(true).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).build();
		
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).build();
		
		methodBuilder.addStatement("$T key", elementKeyProperty.getPropertyType().getTypeName());
		methodBuilder.addStatement("$T value", elementValueProperty.getPropertyType().getTypeName());
				
		if (property.xmlInfo.mapEntryType==MapEntryType.ATTRIBUTE)
		{
			methodBuilder.addStatement("int attributeIndex");
		}

		
		if (property.xmlInfo.isWrappedCollection())
		{					
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))", parserName, EventType.class, parserName, property.xmlInfo.labelItem);
		} else {
			methodBuilder.addCode("// add first element\n");			
			switch (property.xmlInfo.mapEntryType)
			{
			case TAG:
				methodBuilder.addStatement("$L.nextTag()", parserName);
				transformKey.generateParseOnXml(context, methodBuilder, parserName, null, "key", elementKeyProperty);
				methodBuilder.addStatement("$L.nextTag()", parserName);
				methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);
					methodBuilder.addStatement("value=null");
					methodBuilder.addStatement("$L.nextTag()", parserName);
				methodBuilder.nextControlFlow("else");
					transformValue.generateParseOnXml(context, methodBuilder, parserName, null, "value", elementValueProperty);
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("$L.nextTag()", parserName);
				break;
			case ATTRIBUTE:
				methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapKeyName);
				transformKey.generateParseOnXml(context, methodBuilder, parserName, null, "key", elementKeyProperty);
				methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapValueName);
				transformValue.generateParseOnXml(context, methodBuilder, parserName, null, "value", elementValueProperty);
				break;
			}
			
			methodBuilder.addStatement("collection.put(key, value)");
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))", parserName, EventType.class, parserName, property.xmlInfo.labelItem);
		}
		
		switch (property.xmlInfo.mapEntryType)
		{
		case TAG:
			methodBuilder.addStatement("$L.nextTag()", parserName);
			transformKey.generateParseOnXml(context, methodBuilder, parserName, null, "key", elementKeyProperty);
			methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);
				methodBuilder.addStatement("value=null");		
				methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.nextControlFlow("else");
				transformValue.generateParseOnXml(context, methodBuilder, parserName, null, "value", elementValueProperty);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$L.nextTag()", parserName);
			break;
		case ATTRIBUTE:
			methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapKeyName);
			transformKey.generateParseOnXml(context, methodBuilder, parserName, null, "key", elementKeyProperty);
			methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapValueName);
			transformValue.generateParseOnXml(context, methodBuilder, parserName, null, "value", elementValueProperty);
			break;
		}
		
		
			methodBuilder.addStatement("collection.put(key, value)");			
		methodBuilder.endControlFlow();
		
		methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));			
		
		if (!property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addStatement("read=false");
		}
			methodBuilder.endControlFlow();
		//@formatter:on
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// define key and value type
		ParameterizedTypeName mapTypeName=(ParameterizedTypeName) property.getPropertyType().getTypeName();
		TypeName keyTypeName = mapTypeName.typeArguments.get(0);
		TypeName valueTypeName = mapTypeName.typeArguments.get(1);
		
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
				
		if (property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
		}
		
		
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		// key can not be null
		// not in collection, it's included in an element 
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(false).nullable(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).build();
				
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		// not in collection, it's included in an element
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).build();
		
		methodBuilder.beginControlFlow("for ($T<$T, $T> item: $L.entrySet())", Entry.class, keyTypeName, valueTypeName, getter(beanName, beanClass, property));		
		
		methodBuilder.addStatement("$L.writeStartElement($S)$>", serializerName, property.xmlInfo.labelItem);
						
		transformKey.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
		
		if (elementValueProperty.isNullable())
		{
			methodBuilder.beginControlFlow("if (item.getValue()==null)");
				methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.mapValueName);
			methodBuilder.nextControlFlow("else");
				transformValue.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
			methodBuilder.endControlFlow();
		} else {
			transformValue.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
		}
		
		methodBuilder.addStatement("$<$L.writeEndElement()", serializerName);		
		methodBuilder.endControlFlow();
				
		if (property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
		}
					
		methodBuilder.endControlFlow();
		//@formatter:on

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property, false);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property, true);
	}

	/**
	 * Generate serialize on jackson internal.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param serializerName the serializer name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param onString the on string
	 */
	void generateSerializeOnJacksonInternal(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		// define key and value type
		ParameterizedTypeName mapTypeName=(ParameterizedTypeName) property.getPropertyType().getTypeName();
		TypeName keyTypeName = mapTypeName.typeArguments.get(0);
		TypeName valueTypeName = mapTypeName.typeArguments.get(1);
		
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		
			if (property.isProperty())
			{
				methodBuilder.addStatement("fieldCount++");
			}
		
			// fields are in objects, no in collection
			BindTransform transformKey=BindTransformer.lookup(keyTypeName);
			BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).nullable(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).inCollection(false).elementName(property.mapKeyName).build();
			
			BindTransform transformValue=BindTransformer.lookup(valueTypeName);
			BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).nullable(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).inCollection(false).elementName(property.mapValueName).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			
			// BEGIN - if map has elements 
			methodBuilder.beginControlFlow("if ($L.size()>0)",getter(beanName, beanClass, property));
			
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.label);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
									
			methodBuilder.beginControlFlow("for ($T<$T, $T> item: $L.entrySet())", Entry.class, keyTypeName, valueTypeName, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeStartObject()", serializerName);
			
				if (onString)
				{
					transformKey.generateSerializeOnJacksonAsString(context, methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
				} else {
					transformKey.generateSerializeOnJackson(context, methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
				}
			
				// field is always nullable
				methodBuilder.beginControlFlow("if (item.getValue()==null)");
				// KRITPON-38: in a collection, for null object we write "null" string
					if (onString)
					{
						methodBuilder.addStatement("$L.writeStringField($S, \"null\")",serializerName, property.mapValueName);
					} else {
						methodBuilder.addStatement("$L.writeNullField($S)", serializerName, property.mapValueName);
					}
				methodBuilder.nextControlFlow("else");
				if (onString)
				{
					transformValue.generateSerializeOnJacksonAsString(context, methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);	
				} else {
					transformValue.generateSerializeOnJackson(context, methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
				}						
				methodBuilder.endControlFlow();
				
				methodBuilder.addStatement("$L.writeEndObject()", serializerName);
					
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
	    
			// ELSE - if map has elements
	        methodBuilder.nextControlFlow("else");	        
	        	if (onString)
				{
					methodBuilder.addStatement("$L.writeStringField($S, \"null\")",serializerName, property.label);
				} else {
					methodBuilder.addStatement("$L.writeNullField($S)", serializerName, property.label);
				}
	        
			// END - if map has elements
	        methodBuilder.endControlFlow();
	        
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, false);

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, true);
	}

	/**
	 * Generate parse on jackson internal.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param parserName the parser name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param onString the on string
	 */
	public void generateParseOnJacksonInternal(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		// define key and value type
		ParameterizedTypeName mapTypeName=(ParameterizedTypeName) property.getPropertyType().getTypeName();
		TypeName keyTypeName = mapTypeName.typeArguments.get(0);
		TypeName valueTypeName = mapTypeName.typeArguments.get(1);
		
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_ARRAY)", parserName, JsonToken.class);		
		methodBuilder.addStatement("$T<$T, $T> collection=new $T<>()", defineMapClass(mapTypeName), keyTypeName, valueTypeName, defineMapClass(mapTypeName));
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).nullable(false).build();
		
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).nullable(true).build();
		
		methodBuilder.addStatement("$T key=$L", elementKeyProperty.getPropertyType().getTypeName(), DEFAULT_VALUE);
		methodBuilder.addStatement("$T value=$L", elementValueProperty.getPropertyType().getTypeName(), DEFAULT_VALUE);
		if (onString) {
			methodBuilder.addStatement("$T current", JsonToken.class);
			methodBuilder.addStatement("String tempValue=null");
		}
			
		methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
		
		if (onString) {
			// on string
			methodBuilder.addStatement("current=$L.currentToken()", parserName);	
			methodBuilder.beginControlFlow("for (int i=0; i<2 ;i++)");
			
			methodBuilder.beginControlFlow("while (current != $T.FIELD_NAME)",JsonToken.class);
			methodBuilder.addStatement("current=$L.nextToken()", parserName);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$L.nextValue()", parserName);
			//
			methodBuilder.addCode("switch(jacksonParser.getCurrentName()) {\n");
			methodBuilder.addCode("case $S:$>\n", property.mapKeyName);
			
			transformKey.generateParseOnJacksonAsString(context, methodBuilder, parserName, null, "key", elementKeyProperty);
			
			methodBuilder.addStatement("$<break");
			methodBuilder.addCode("case $S:$>\n", property.mapValueName);
			
			methodBuilder.addStatement("tempValue=$L.getValueAsString()", parserName);
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_STRING && \"null\".equals(tempValue))", parserName, JsonToken.class);
				methodBuilder.addStatement("value=$L", DEFAULT_VALUE);
			methodBuilder.nextControlFlow("else");			
				transformValue.generateParseOnJacksonAsString(context, methodBuilder, parserName, null, "value", elementValueProperty);			
			methodBuilder.endControlFlow();
			
			methodBuilder.addStatement("$<break");
			methodBuilder.addCode("}\n");
			//
			
			methodBuilder.endControlFlow();
		} else {
			// key
			methodBuilder.addStatement("$L.nextValue()", parserName);
			transformKey.generateParseOnJackson(context, methodBuilder, parserName, null, "key", elementKeyProperty);
			
			// value
			methodBuilder.addStatement("$L.nextValue()", parserName);
			transformValue.generateParseOnJackson(context, methodBuilder, parserName, null, "value", elementValueProperty);
			
		}
			methodBuilder.addStatement("collection.put(key, value)");
			methodBuilder.addStatement("key=$L", DEFAULT_VALUE);
			methodBuilder.addStatement("value=$L" , DEFAULT_VALUE);
			methodBuilder.addStatement("$L.nextToken()", parserName);
		methodBuilder.endControlFlow();
			
							
		methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));

		methodBuilder.endControlFlow();
		//@formatter:on
	}

}
