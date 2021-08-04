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
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.abubusoft.kripton.xml.EventType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * The Class AbstractCollectionBindTransform.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractCollectionBindTransform extends AbstractBindTransform {

	public static final String COLLECTION_ADD_ITEM = "collection.add(item)";
	public static final String COLLECTION_NEW = "$T<$T> collection=new $T<>()";
	public static final String ITEM_ASSIGN = "item=$L";
	public static final String ITEM_DEFINE = "$T item";
	public static final String INT_N_SIZE = "int n=$L.size()";
	public static final String FOR_INT_I_N = "for (int i=0; i<n; i++)";

	/**
	 * The Enum CollectionType.
	 */
	public enum CollectionType {

		/** The array. */
		ARRAY,
		/** The list. */
		LIST,
		/** The set. */
		SET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#
	 * isTypeAdapterSupported()
	 */
	@Override
	public boolean isTypeAdapterSupported() {
		return false;
	}

	/** The collection type. */
	protected CollectionType collectionType;

	/**
	 * Instantiates a new abstract collection bind transform.
	 *
	 * @param clazz
	 *            the clazz
	 * @param collectionType
	 *            the collection type
	 */
	protected AbstractCollectionBindTransform(ParameterizedTypeName clazz, CollectionType collectionType) {
		this.collectionType = collectionType;
	}

	/**
	 * Only for arrays.
	 *
	 * @param clazz
	 *            the clazz
	 * @param collectionType
	 *            the collection type
	 */
	protected AbstractCollectionBindTransform(TypeName clazz, CollectionType collectionType) {
		this.collectionType = collectionType;
	}

	/** The collection clazz. */
	protected Class<?> collectionClazz = List.class;

	/** The default clazz. */
	protected Class<?> defaultClazz = ArrayList.class;

	/**
	 * Define collection class.
	 *
	 * @param collectionTypeName
	 *            the collection type name
	 * @return the class
	 */
	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.rawType.toString().startsWith(collectionClazz.getCanonicalName())) {
			return defaultClazz;
		} else if (collectionTypeName.rawType.toString().startsWith(SortedMap.class.getCanonicalName())) {
			return TreeMap.class;
		} else if (collectionTypeName.rawType.toString().startsWith(SortedSet.class.getCanonicalName())) {
			return TreeSet.class;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new KriptonClassNotFoundException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName,
			TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, false);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder,
			String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, true);
	}

	/**
	 * Generate parse on jackson internal.
	 *
	 * @param context
	 *            the context
	 * @param methodBuilder
	 *            the method builder
	 * @param parserName
	 *            the parser name
	 * @param beanClass
	 *            the bean class
	 * @param beanName
	 *            the bean name
	 * @param property
	 *            the property
	 * @param onString
	 *            the on string
	 */
	public void generateParseOnJacksonInternal(BindTypeContext context, Builder methodBuilder, String parserName,
			TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		TypeName elementTypeName = extractTypeParameterName(property);
		// @formatter:off
		methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_ARRAY)", parserName, JsonToken.class);
		if (collectionType == CollectionType.ARRAY) {
			methodBuilder.addStatement(COLLECTION_NEW, ArrayList.class, elementTypeName.box(),
					ArrayList.class);
		} else {
			// it's for sure a parametrized type
			ParameterizedTypeName collectionTypeName = (ParameterizedTypeName) property.getPropertyType().getTypeName();
			methodBuilder.addStatement(COLLECTION_NEW, defineCollectionClass(collectionTypeName),
					elementTypeName.box(), defineCollectionClass(collectionTypeName));
		}

		methodBuilder.addStatement("$T item=$L", elementTypeName.box(), DEFAULT_VALUE);

		if (onString) {
			methodBuilder.addStatement("String tempValue=null");
		}

		BindTransform transform = BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty = BindProperty.builder(elementTypeName, property).inCollection(true)
				.nullable(false).build();

		methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
		if (onString) {
			methodBuilder.addStatement("tempValue=$L.getValueAsString()", parserName);
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_STRING && \"null\".equals(tempValue))",
					parserName, JsonToken.class);
		} else {
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		methodBuilder.addStatement(ITEM_ASSIGN, DEFAULT_VALUE);
		methodBuilder.nextControlFlow("else");
		if (onString) {
			transform.generateParseOnJacksonAsString(context, methodBuilder, parserName, null, "item", elementProperty);
		} else {
			transform.generateParseOnJackson(context, methodBuilder, parserName, null, "item", elementProperty);
		}
		define(methodBuilder, beanClass, beanName, property, elementTypeName);

		if (onString) {
			// ELSE: check if empty string (== empty collection but not null)
			methodBuilder.nextControlFlow(
					"else if ($L.currentToken()==$T.VALUE_STRING && !$T.hasText($L.getValueAsString()))", parserName,
					JsonToken.class, StringUtils.class, parserName);
			// create collection
			if (collectionType == CollectionType.ARRAY) {
				methodBuilder.addStatement(COLLECTION_NEW, ArrayList.class, elementTypeName.box(),
						ArrayList.class);
			} else {
				// it's for sure a parametrized type
				ParameterizedTypeName collectionTypeName = (ParameterizedTypeName) property.getPropertyType()
						.getTypeName();
				methodBuilder.addStatement(COLLECTION_NEW, defineCollectionClass(collectionTypeName),
						elementTypeName.box(), defineCollectionClass(collectionTypeName));
			}
			// set collection
			if (collectionType == CollectionType.ARRAY) {
				if (TypeUtility.isTypePrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"),
							CollectionUtils.class, elementTypeName.box());
				} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"),
							CollectionUtils.class, elementTypeName.box());
				} else {
					methodBuilder.addStatement(
							setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"),
							CollectionUtils.class, elementTypeName);
				}
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
			}
			// END: we use the next endControlFlow
		}

		methodBuilder.endControlFlow();
		// @formatter:on
	}

	private void define(Builder methodBuilder, TypeName beanClass, String beanName, BindProperty property, TypeName elementTypeName) {
		methodBuilder.endControlFlow();
		methodBuilder.addStatement(COLLECTION_ADD_ITEM);
		methodBuilder.endControlFlow();

		if (collectionType == CollectionType.ARRAY) {
			if (TypeUtility.isTypePrimitive(elementTypeName)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"),
						CollectionUtils.class, elementTypeName.box());
			} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"),
						CollectionUtils.class, elementTypeName);
			} else {
				methodBuilder.addStatement(
						setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"),
						CollectionUtils.class, elementTypeName);
			}
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
		}
	}

	/**
	 * Convert.
	 *
	 * @param modelEntity
	 *            the model entity
	 * @param elementTypeName
	 *            the element type name
	 * @return the type name
	 */
	protected TypeName convert(@SuppressWarnings("rawtypes") ModelEntity modelEntity, ClassName elementTypeName) {
		return elementTypeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#
	 * generateParseOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext,
	 * com.squareup.javapoet.MethodSpec.Builder, java.lang.String,
	 * com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName,
			TypeName beanClass, String beanName, BindProperty property) {
		TypeName elementTypeName = extractTypeParameterName(property);
		// @formatter:off
		methodBuilder.beginControlFlow("");
		switch (collectionType) {
		case ARRAY:
			methodBuilder.addStatement("$T<$T> collection=$T.merge(new $T<>(), $L)", 
					ArrayList.class, elementTypeName.box(),
					CollectionUtils.class,
					ArrayList.class,
					((ModelClass<?>) property.getParent()).isImmutablePojo()? ImmutableUtility.IMMUTABLE_PREFIX + property.getName() : getter(beanName, beanClass, property));
			break;
		case LIST:
		case SET:
			// it's for sure a parametrized type
			ParameterizedTypeName collectionTypeName = (ParameterizedTypeName) property.getPropertyType().getTypeName();
			methodBuilder.addStatement("$T<$T> collection=$T.merge(new $T<>(), $L)", 
					defineCollectionClass(collectionTypeName),
					elementTypeName.box(), 
					CollectionUtils.class,			
					defineCollectionClass(collectionTypeName),
					((ModelClass<?>) property.getParent()).isImmutablePojo()? ImmutableUtility.IMMUTABLE_PREFIX + property.getName() : getter(beanName, beanClass, property));
			break;
		}

		methodBuilder.addStatement(ITEM_DEFINE, elementTypeName.box());

		BindTransform transform = BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty = BindProperty.builder(elementTypeName, property).inCollection(true).build();

		if (property.xmlInfo.isWrappedCollection()) {
			// with wrap element
			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))",
					parserName, EventType.class, parserName, property.xmlInfo.labelItem);
		} else {
			// no wrap element
			methodBuilder.addCode("// add first element\n");
			methodBuilder.addStatement(ITEM_ASSIGN, DEFAULT_VALUE);
			methodBuilder.beginControlFlow("if ($T.isEmptyTag($L))", XmlAttributeUtils.class, parserName);
			methodBuilder.addCode("// if there's a an empty collection it marked with attribute emptyCollection\n");
			methodBuilder.beginControlFlow("if ($T.getAttributeAsBoolean($L, $S, false)==false)",
					XmlAttributeUtils.class, parserName, XmlAttributeUtils.EMPTY_COLLECTION_ATTRIBUTE_NAME);
			methodBuilder.addStatement(COLLECTION_ADD_ITEM);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.nextControlFlow("else");
			transform.generateParseOnXml(context, methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.addStatement(COLLECTION_ADD_ITEM);
			methodBuilder.endControlFlow();

			methodBuilder.beginControlFlow("while ($L.nextTag() != $T.END_TAG && $L.getName().toString().equals($S))",
					parserName, EventType.class, parserName, BindProperty.xmlName(property));
		}

		// for all		
		methodBuilder.beginControlFlow("if ($T.isEmptyTag($L))", XmlAttributeUtils.class, parserName);
		methodBuilder.addStatement(ITEM_ASSIGN, DEFAULT_VALUE);
		methodBuilder.addStatement("$L.nextTag()", parserName);
		methodBuilder.nextControlFlow("else");
		transform.generateParseOnXml(context, methodBuilder, parserName, null, "item", elementProperty);
		define(methodBuilder, beanClass, beanName, property, elementTypeName);

		if (!property.xmlInfo.isWrappedCollection()) {
			methodBuilder.addStatement("read=false");
		}

		methodBuilder.endControlFlow();
		// @formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#
	 * generateSerializeOnJackson(com.abubusoft.kripton.processor.bind.
	 * BindTypeContext, com.squareup.javapoet.MethodSpec.Builder,
	 * java.lang.String, com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder,
			String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property,
				false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#
	 * generateSerializeOnJacksonAsString(com.abubusoft.kripton.processor.bind.
	 * BindTypeContext, com.squareup.javapoet.MethodSpec.Builder,
	 * java.lang.String, com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder,
			String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		this.generateSerializeOnJacksonInternal(context, methodBuilder, serializerName, beanClass, beanName, property,
				true);
	}

	/**
	 * Generate serialize on jackson internal.
	 *
	 * @param context
	 *            the context
	 * @param methodBuilder
	 *            the method builder
	 * @param serializerName
	 *            the serializer name
	 * @param beanClass
	 *            the bean class
	 * @param beanName
	 *            the bean name
	 * @param property
	 *            the property
	 * @param onString
	 *            the on string
	 */
	void generateSerializeOnJacksonInternal(BindTypeContext context, MethodSpec.Builder methodBuilder,
			String serializerName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		TypeName elementTypeName = extractTypeParameterName(property);
		// @formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (collectionType == CollectionType.LIST) {
			methodBuilder.addStatement(INT_N_SIZE, getter(beanName, beanClass, property));
			methodBuilder.addStatement(ITEM_DEFINE, elementTypeName);
		} else if (collectionType == CollectionType.ARRAY) {
			methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
			methodBuilder.addStatement(ITEM_DEFINE, elementTypeName);
		} else if (onString) {
			methodBuilder.addStatement(INT_N_SIZE, getter(beanName, beanClass, property));
		}

		BindTransform transform = BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty = BindProperty.builder(elementTypeName, property).inCollection(true)
				.nullable(false).build();

		methodBuilder.addCode("// write wrapper tag\n");
		methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.label);

		if (onString) {
			// BEGIN - IF there are some elements
			methodBuilder.beginControlFlow("if (n>0)");
		}

		methodBuilder.addStatement("$L.writeStartArray()", serializerName);

		if (collectionType == CollectionType.SET) {
			methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));
		} else if (collectionType == CollectionType.LIST) {
			methodBuilder.beginControlFlow(FOR_INT_I_N);
			methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
		} else if (collectionType == CollectionType.ARRAY) {
			methodBuilder.beginControlFlow(FOR_INT_I_N);
			methodBuilder.addStatement("item=$L[i]", getter(beanName, beanClass, property));
		}

		// we have to write here this if because the internal if on
		// serialization does not support else statement.
		// We are in a collection, so we need to write null value too. In all
		// other case, null value will be skipped.
		if (elementProperty.getPropertyType().isArray() || !TypeUtility.isTypePrimitive(elementTypeName)) {

			// CASE 1 ASSERT: we are with item of kink array OR we are not
			// manage a simple primitive
			methodBuilder.beginControlFlow("if (item==null)");
			if (onString) {
				// KRIPTON-38: supporto for null value as string
				methodBuilder.addStatement("$L.writeString(\"null\")", serializerName);
			} else {
				methodBuilder.addStatement("$L.writeNull()", serializerName);
			}
			methodBuilder.nextControlFlow("else");
		}
		if (onString) {
			transform.generateSerializeOnJacksonAsString(context, methodBuilder, serializerName, null, "item",
					elementProperty);
		} else {
			transform.generateSerializeOnJackson(context, methodBuilder, serializerName, null, "item", elementProperty);
		}
		if (elementProperty.getPropertyType().isArray() || !TypeUtility.isTypePrimitive(elementTypeName)) {
			// CASE 1 ASSERT: we are with item of kink array OR we are not
			// manage a simple primitive
			methodBuilder.endControlFlow();
		}

		methodBuilder.endControlFlow();

		methodBuilder.addStatement("$L.writeEndArray()", serializerName);

		if (onString) {
			// ELSE - IF there are some elements
			methodBuilder.nextControlFlow("else");
			// if collection has 0 elements, write an empty string
			methodBuilder.addStatement("$L.writeString(\"\")", serializerName);
			// END - IF there are some elements
			methodBuilder.endControlFlow();
		}

		methodBuilder.endControlFlow();
		// @formatter:on
	}

	/**
	 * Extract type parameter name.
	 *
	 * @param property
	 *            the property
	 * @return the type name
	 */
	private TypeName extractTypeParameterName(BindProperty property) {
		return property.getPropertyType().getTypeParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#
	 * generateSerializeOnXml(com.abubusoft.kripton.processor.bind.
	 * BindTypeContext, com.squareup.javapoet.MethodSpec.Builder,
	 * java.lang.String, com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName,
			TypeName beanClass, String beanName, BindProperty property) {
		TypeName elementTypeName = extractTypeParameterName(property);
		// @formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));

		switch (collectionType) {
		case LIST:
			methodBuilder.addStatement(INT_N_SIZE, getter(beanName, beanClass, property));
			methodBuilder.addStatement(ITEM_DEFINE, elementTypeName);
			break;
		case ARRAY:
			methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
			methodBuilder.addStatement(ITEM_DEFINE, elementTypeName);
			break;
		case SET:
			methodBuilder.addStatement(INT_N_SIZE, getter(beanName, beanClass, property));
			break;
		}

		if (property.xmlInfo.isWrappedCollection()) {
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
		}

		BindTransform transform = BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty = BindProperty.builder(elementTypeName, property).inCollection(true)
				.elementName(BindProperty.xmlNameForItem(property)).build();

		switch (collectionType) {
		case SET:
			methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));
			break;
		case LIST:
			methodBuilder.beginControlFlow(FOR_INT_I_N);
			methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			break;
		case ARRAY:
			methodBuilder.beginControlFlow(FOR_INT_I_N);
			methodBuilder.addStatement("item=$L[i]", getter(beanName, beanClass, property));
			break;
		}

		if (!TypeUtility.isTypePrimitive(elementTypeName)) {
			methodBuilder.beginControlFlow("if (item==null)");
			methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, BindProperty.xmlNameForItem(property));
			methodBuilder.nextControlFlow("else");
			transform.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item", elementProperty);
			methodBuilder.endControlFlow();
		} else {
			transform.generateSerializeOnXml(context, methodBuilder, serializerName, null, "item", elementProperty);
		}

		methodBuilder.endControlFlow();

		if (property.xmlInfo.isWrappedCollection()) {
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
		} else {
			// if there's no wrap tag, we need to assure that empty collection
			// will be writed.
			// to distinguish between first empty element and empty collection,
			// we write an attribute emptyCollection to
			// say: this collection is empty
			methodBuilder.addCode(
					"// to distinguish between first empty element and empty collection, we write an attribute emptyCollection\n");
			methodBuilder.beginControlFlow("if (n==0)");
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlNameForItem(property));
			methodBuilder.addStatement("$L.writeAttribute($S, $S)", serializerName, XmlAttributeUtils.EMPTY_COLLECTION_ATTRIBUTE_NAME,
					"true");
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			methodBuilder.endControlFlow();
		}

		methodBuilder.endControlFlow();
		// @formatter:on
	}

}
