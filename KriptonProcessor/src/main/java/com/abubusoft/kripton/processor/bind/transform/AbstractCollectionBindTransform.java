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
/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AbstractCollectionBindTransform extends AbstractBindTransform {

	private static final String EMPTY_COLLECTION_ATTRIBUTE_NAME = "emptyCollection";

	public enum CollectionType {
		ARRAY, LIST, SET;
	}

	@Override
	public boolean isTypeAdapterSupported() {
		return false;
	}

	protected CollectionType collectionType;
	// private ParameterizedTypeName collectionTypeName;
	// private TypeName elementTypeName;

	public AbstractCollectionBindTransform(ParameterizedTypeName clazz, CollectionType collectionType) {
		this.collectionType = collectionType;

		// this.collectionTypeName = clazz;
		// for now, it supports only parameterized type with 1 argument
		// this.elementTypeName = clazz.typeArguments.get(0);
	}

	/**
	 * Only for arrays
	 * 
	 * @param clazz
	 * @param collectionType
	 */
	public AbstractCollectionBindTransform(TypeName clazz, CollectionType collectionType) {
		this.collectionType = collectionType;

		// this.collectionTypeName = null;
		// for now, it supports only parameterized type with 1 argument
		// this.elementTypeName = clazz;
	}

	protected Class<?> collectionClazz = List.class;
	protected Class<?> defaultClazz = ArrayList.class;

	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.rawType.toString().startsWith(collectionClazz.getCanonicalName())) {
			return defaultClazz;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new KriptonClassNotFoundException(e);
		}
	}

	@Override
	public void generateParseOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, false);
	}

	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, true);
	}

	public void generateParseOnJacksonInternal(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		TypeName elementTypeName = extractTypeParameterName(property);
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_ARRAY)", parserName, JsonToken.class);
			if (collectionType==CollectionType.ARRAY)
			{
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", ArrayList.class, elementTypeName.box(), ArrayList.class);
			} else {
				// it's for sure a parametrized type
				ParameterizedTypeName collectionTypeName=(ParameterizedTypeName)property.getPropertyType().getTypeName();
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), elementTypeName.box(), defineCollectionClass(collectionTypeName));
			}
			
			methodBuilder.addStatement("$T item=$L", elementTypeName.box(), DEFAULT_VALUE);
			
			if (onString)
			{
				methodBuilder.addStatement("String tempValue=null");
			}
			
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).inCollection(true).nullable(false).build();

			methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
				if (onString)
				{
					methodBuilder.addStatement("tempValue=$L.getValueAsString()", parserName);
					methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_STRING && \"null\".equals(tempValue))", parserName, JsonToken.class);
				} else {
					methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_NULL)", parserName, JsonToken.class);
				}
				methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
				methodBuilder.nextControlFlow("else");
				if (onString)
				{
					transform.generateParseOnJacksonAsString(context, methodBuilder, parserName, null, "item", elementProperty);
				} else {
					transform.generateParseOnJackson(context, methodBuilder, parserName, null, "item", elementProperty);
				}
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("collection.add(item)");
			methodBuilder.endControlFlow();
			
			if (collectionType==CollectionType.ARRAY)
			{
				if (TypeUtility.isTypePrimitive(elementTypeName))
				{
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"), CollectionUtils.class, elementTypeName.box());
				} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"), CollectionUtils.class, elementTypeName);
				} else {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"), CollectionUtils.class, elementTypeName);
				}				
			} else {				
				methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
			}


		if (onString)
		{
			// ELSE: check if empty string (== empty collection but not null)
			methodBuilder.nextControlFlow("else if ($L.currentToken()==$T.VALUE_STRING && !$T.hasText($L.getValueAsString()))", parserName, JsonToken.class, StringUtils.class,parserName);
			// create collection 
			if (collectionType==CollectionType.ARRAY)
			{
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", ArrayList.class, elementTypeName.box(), ArrayList.class);
			} else {
				// it's for sure a parametrized type
				ParameterizedTypeName collectionTypeName=(ParameterizedTypeName)property.getPropertyType().getTypeName();
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), elementTypeName.box(), defineCollectionClass(collectionTypeName));
			}
			// set collection
			if (collectionType==CollectionType.ARRAY)
			{
				if (TypeUtility.isTypePrimitive(elementTypeName))
				{
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"), CollectionUtils.class, elementTypeName.box());
				} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"), CollectionUtils.class, elementTypeName.box());
				} else {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"), CollectionUtils.class, elementTypeName);
				}				
			} else {				
				methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
			}
			// END: we use the next endControlFlow
		}
		
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	protected TypeName convert(@SuppressWarnings("rawtypes") ModelEntity modelEntity, ClassName elementTypeName) {
		return elementTypeName;
	}

	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		TypeName elementTypeName = extractTypeParameterName(property);
		//@formatter:off
		methodBuilder.beginControlFlow("");
		
		switch(collectionType)
		{
		case ARRAY:
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", ArrayList.class, elementTypeName.box(), ArrayList.class);
			break;
		case LIST:
		case SET:
			// it's for sure a parametrized type
			ParameterizedTypeName collectionTypeName=(ParameterizedTypeName)property.getPropertyType().getTypeName();
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), elementTypeName.box(), defineCollectionClass(collectionTypeName));
			break;
		}		
				
		methodBuilder.addStatement("$T item", elementTypeName.box());
			
		BindTransform transform=BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty=BindProperty.builder(elementTypeName, property).inCollection(true).build();
						
		if (property.xmlInfo.isWrappedCollection())
		{					
			// with wrap element
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))", parserName, XmlPullParser.class,  parserName, property.xmlInfo.labelItem);
		} else {
			// no wrap element
			methodBuilder.addCode("// add first element\n");
			methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
			methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);
				methodBuilder.addCode("// if there's a an empty collection it marked with attribute emptyCollection\n");
				methodBuilder.beginControlFlow("if ($T.getAttributeAsBoolean($L, $S, false)==false)", XmlAttributeUtils.class, parserName, EMPTY_COLLECTION_ATTRIBUTE_NAME);
					methodBuilder.addStatement("collection.add(item)");					
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.nextControlFlow("else");
			transform.generateParseOnXml(context, methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.addStatement("collection.add(item)");
			methodBuilder.endControlFlow();			
			
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))", parserName, XmlPullParser.class, parserName, property.label);
		}
		
		// for all
		methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
			methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
			methodBuilder.addStatement("$L.nextTag()", parserName);						
		methodBuilder.nextControlFlow("else");
			transform.generateParseOnXml(context, methodBuilder, parserName, null, "item", elementProperty);
		methodBuilder.endControlFlow();
		methodBuilder.addStatement("collection.add(item)");
		
		methodBuilder.endControlFlow();
	                 	
		if (collectionType==CollectionType.ARRAY)
		{
			if (TypeUtility.isTypePrimitive(elementTypeName))
			{
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"), CollectionUtils.class, elementTypeName.box());
			} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"), CollectionUtils.class, elementTypeName);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"), CollectionUtils.class, elementTypeName);
			}				
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
		}
		
		if (!property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addStatement("read=false");
		}
		
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property, false);
	}

	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property, true);
	}

	void generateSerializeOnJacksonInternal(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property,
			boolean onString) {
		TypeName elementTypeName = extractTypeParameterName(property);
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			if (property.isProperty())
			{
				methodBuilder.addStatement("fieldCount++");
			}
		
			if (collectionType==CollectionType.LIST) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			} else if (collectionType==CollectionType.ARRAY) {
				methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			} else if (onString){
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
			}
		
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).inCollection(true).nullable(false).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.label);
			
			if (onString)
			{
			// BEGIN - IF there are some elements
			methodBuilder.beginControlFlow("if (n>0)");
			}
			
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
					
			if (collectionType==CollectionType.SET) {			
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));				
			} else if (collectionType==CollectionType.LIST) {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			} else if (collectionType==CollectionType.ARRAY) {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L[i]", getter(beanName, beanClass, property));
			}
			
			// we have to write here this if because the internal if on serialization does not support else statement.
			// We are in a collection, so we need to write null value too. In all other case, null value will be skipped.		
			if (elementProperty.getPropertyType().isArray() || !TypeUtility.isTypePrimitive(elementTypeName)) {
				
				// CASE 1 ASSERT: we are with item of kink array OR we are not manage a simple primitive
				methodBuilder.beginControlFlow("if (item==null)");
					if (onString)
					{
						// KRIPTON-38: supporto for null value as string
						methodBuilder.addStatement("$L.writeString(\"null\")", serializerName);
					} else {
						methodBuilder.addStatement("$L.writeNull()", serializerName);
					}
				methodBuilder.nextControlFlow("else");
			}
					if (onString)
					{
						transform.generateSerializeOnJacksonAsString(context, methodBuilder, serializerName, null, "item", elementProperty);
					} else {
						transform.generateSerializeOnJackson(context, methodBuilder, serializerName, null, "item", elementProperty);	
					}
			if (elementProperty.getPropertyType().isArray() || !TypeUtility.isTypePrimitive(elementTypeName)) {
				// CASE 1 ASSERT: we are with item of kink array OR we are not manage a simple primitive
				methodBuilder.endControlFlow();
			}
				
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
	        
	        if (onString)
			{
			// ELSE - IF there are some elements
	        methodBuilder.nextControlFlow("else");
	            // if collection has 0 elements, write an empty string
	        	methodBuilder.addStatement("$L.writeString(\"\")", serializerName);	        
			// END - IF there are some elements
	        methodBuilder.endControlFlow();
			}
	        
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	private TypeName extractTypeParameterName(BindProperty property) {
		return property.getPropertyType().getTypeParameter();
	}

	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		TypeName elementTypeName = extractTypeParameterName(property);
		//@formatter:off
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			
			switch(collectionType)
			{
			case LIST:
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
				break;
			case ARRAY:
				methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
				break;
			case SET:
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				break;
			}			
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addCode("// write wrapper tag\n");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
			}
			
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).inCollection(true).elementName(property.xmlInfo.labelItem).build();
			
			switch(collectionType)
			{
			case SET:
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));
				break;
			case LIST:
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
				break;
			case ARRAY:
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L[i]", getter(beanName, beanClass, property));
				break;
			}
			
			
			if (!TypeUtility.isTypePrimitive(elementTypeName))
			{
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.xmlInfo.labelItem);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			} else {
				transform.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item", elementProperty);
			}
				
			methodBuilder.endControlFlow();
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			} else {				
				// if there's no wrap tag, we need to assure that empty collection will be writed.
				// to distinguish between first empty element and empty collection, we write an attribute emptyCollection to
				// say: this collection is empty
				methodBuilder.addCode("// to distinguish between first empty element and empty collection, we write an attribute emptyCollection\n");
				methodBuilder.beginControlFlow("if (n==0)");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.labelItem);
				methodBuilder.addStatement("$L.writeAttribute($S, $S)", serializerName, EMPTY_COLLECTION_ATTRIBUTE_NAME, "true");
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);				
			    methodBuilder.endControlFlow();
			}
						
			methodBuilder.endControlFlow();
			//@formatter:on
	}

}
