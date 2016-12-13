package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.abubusoft.kripton.binder.xml.MapEntryType;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class MapBindTransformation extends AbstractBindTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	private ParameterizedTypeName mapTypeName;
	private TypeName keyTypeName;
	private TypeName valueTypeName;

	public MapBindTransformation(ParameterizedTypeName clazz) {
		this.mapTypeName = clazz;
		this.keyTypeName = mapTypeName.typeArguments.get(0);
		this.valueTypeName = mapTypeName.typeArguments.get(1);
	}

	private Class<?> defineMapClass(ParameterizedTypeName mapTypeName) {
		if (mapTypeName.toString().startsWith(Map.class.getCanonicalName())) {
			// it's a list
			return HashMap.class;
		}
		try {
			return Class.forName(mapTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("");
		
		methodBuilder.addStatement("$T<$T, $T> collection=new $T<>()", defineMapClass(mapTypeName), keyTypeName, valueTypeName, defineMapClass(mapTypeName));
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(true).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).build();
		
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).build();
		
		methodBuilder.addStatement("$T key", elementKeyProperty.getPropertyType().getName());
		methodBuilder.addStatement("$T value", elementValueProperty.getPropertyType().getName());
				
		if (property.xmlInfo.mapEntryType==MapEntryType.ATTRIBUTE)
		{
			methodBuilder.addStatement("int attributeIndex");
		}

		
		if (property.xmlInfo.isWrappedCollection())
		{					
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_ELEMENT && $L.getName().toString().equals($S))", parserName, XMLEventConstants.class, parserName, property.xmlInfo.tagElement);
		} else {
			methodBuilder.addCode("// add first element\n");			
			switch (property.xmlInfo.mapEntryType)
			{
			case TAG:
				methodBuilder.addStatement("$L.nextTag()", parserName);
				transformKey.generateParseOnXml(methodBuilder, parserName, null, "key", elementKeyProperty);
				methodBuilder.addStatement("$L.nextTag()", parserName);
				methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);
					methodBuilder.addStatement("value=null");
					methodBuilder.addStatement("$L.nextTag()", parserName);
				methodBuilder.nextControlFlow("else");
					transformValue.generateParseOnXml(methodBuilder, parserName, null, "value", elementValueProperty);
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("$L.nextTag()", parserName);
				break;
			case ATTRIBUTE:
				methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapKeyName);
				transformKey.generateParseOnXml(methodBuilder, parserName, null, "key", elementKeyProperty);
				methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapValueName);
				transformValue.generateParseOnXml(methodBuilder, parserName, null, "value", elementValueProperty);
				break;
			}
			
			methodBuilder.addStatement("collection.put(key, value)");
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_ELEMENT && $L.getName().toString().equals($S))", parserName, XMLEventConstants.class, parserName, property.xmlInfo.tagElement);
		}
		
		switch (property.xmlInfo.mapEntryType)
		{
		case TAG:
			methodBuilder.addStatement("$L.nextTag()", parserName);
			transformKey.generateParseOnXml(methodBuilder, parserName, null, "key", elementKeyProperty);
			methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);
				methodBuilder.addStatement("value=null");		
				methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.nextControlFlow("else");
				transformValue.generateParseOnXml(methodBuilder, parserName, null, "value", elementValueProperty);
				//methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$L.nextTag()", parserName);
			break;
		case ATTRIBUTE:
			methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapKeyName);
			transformKey.generateParseOnXml(methodBuilder, parserName, null, "key", elementKeyProperty);
			methodBuilder.addStatement("attributeIndex=$L.getAttributeIndex(null, $S)", parserName, property.mapValueName);
			transformValue.generateParseOnXml(methodBuilder, parserName, null, "value", elementValueProperty);
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

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
				
		if (property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
		}
		
		
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		// key can not be null
		// not in collection, it's included in an element 
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(false).nullable(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).build();
				
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		// not in collection, it's included in an element
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).build();
		
		methodBuilder.beginControlFlow("for ($T<$T, $T> item: $L.entrySet())", Entry.class, keyTypeName, valueTypeName, getter(beanName, beanClass, property));		
		
		methodBuilder.addStatement("$L.writeStartElement($S)$>", serializerName, property.xmlInfo.tagElement);
						
		transformKey.generateSerializeOnXml(methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
		
		if (elementValueProperty.isNullable())
		{
			methodBuilder.beginControlFlow("if (item.getValue()==null)");
				methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.mapValueName);
			methodBuilder.nextControlFlow("else");
				transformValue.generateSerializeOnXml(methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
			methodBuilder.endControlFlow();
		} else {
			transformValue.generateSerializeOnXml(methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
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

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(methodBuilder, serializerName, beanClass, beanName, property, false);
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(methodBuilder, serializerName, beanClass, beanName, property, true);
	}

	void generateSerializeOnJacksonInternal(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
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
			
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonInfo.jacksonName);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
									
			methodBuilder.beginControlFlow("for ($T<$T, $T> item: $L.entrySet())", Entry.class, keyTypeName, valueTypeName, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeStartObject()", serializerName);
			
				if (onString)
				{
					transformKey.generateSerializeOnJacksonAsString(methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
				} else {
					transformKey.generateSerializeOnJackson(methodBuilder, serializerName, null, "item.getKey()", elementKeyProperty);
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
					transformValue.generateSerializeOnJacksonAsString(methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);	
				} else {
					transformValue.generateSerializeOnJackson(methodBuilder, serializerName, null, "item.getValue()", elementValueProperty);
				}						
				methodBuilder.endControlFlow();
				
				methodBuilder.addStatement("$L.writeEndObject()", serializerName);
					
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
	    
			// ELSE - if map has elements
	        methodBuilder.nextControlFlow("else");	        
	        	if (onString)
				{
					methodBuilder.addStatement("$L.writeStringField($S, \"null\")",serializerName, property.jacksonInfo.jacksonName);
				} else {
					methodBuilder.addStatement("$L.writeNullField($S)", serializerName, property.jacksonInfo.jacksonName);
				}
	        
			// END - if map has elements
	        methodBuilder.endControlFlow();
	        
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, false);

	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, true);
	}

	public void generateParseOnJacksonInternal(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_ARRAY)", parserName, JsonToken.class);		
		methodBuilder.addStatement("$T<$T, $T> collection=new $T<>()", defineMapClass(mapTypeName), keyTypeName, valueTypeName, defineMapClass(mapTypeName));
		BindTransform transformKey=BindTransformer.lookup(keyTypeName);
		BindProperty elementKeyProperty=BindProperty.builder(keyTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapKeyName).nullable(false).build();
		
		BindTransform transformValue=BindTransformer.lookup(valueTypeName);
		BindProperty elementValueProperty=BindProperty.builder(valueTypeName, property).inCollection(false).xmlType(property.xmlInfo.mapEntryType.toXmlType()).elementName(property.mapValueName).nullable(true).build();
		
		methodBuilder.addStatement("$T key=$L", elementKeyProperty.getPropertyType().getName(), DEFAULT_VALUE);
		methodBuilder.addStatement("$T value=$L", elementValueProperty.getPropertyType().getName(), DEFAULT_VALUE);
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
			
			transformKey.generateParseOnJacksonAsString(methodBuilder, parserName, null, "key", elementKeyProperty);
			
			methodBuilder.addStatement("$<break");
			methodBuilder.addCode("case $S:$>\n", property.mapValueName);
			
			methodBuilder.addStatement("tempValue=$L.getValueAsString()", parserName);
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_STRING && \"null\".equals(tempValue))", parserName, JsonToken.class);
				methodBuilder.addStatement("value=$L", DEFAULT_VALUE);
			methodBuilder.nextControlFlow("else");			
				transformValue.generateParseOnJacksonAsString(methodBuilder, parserName, null, "value", elementValueProperty);			
			methodBuilder.endControlFlow();
			
			methodBuilder.addStatement("$<break");
			methodBuilder.addCode("}\n");
			//
			
			methodBuilder.endControlFlow();
		} else {
			// key
			methodBuilder.addStatement("$L.nextValue()", parserName);
			transformKey.generateParseOnJackson(methodBuilder, parserName, null, "key", elementKeyProperty);
			
			// value
			methodBuilder.addStatement("$L.nextValue()", parserName);
			transformValue.generateParseOnJackson(methodBuilder, parserName, null, "value", elementValueProperty);
			
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
