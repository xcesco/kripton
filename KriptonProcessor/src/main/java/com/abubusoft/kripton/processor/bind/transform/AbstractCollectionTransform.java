/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 */
public abstract class AbstractCollectionTransform extends AbstractBindTransform {
	
	public enum CollectionType
	{
		ARRAY,
		LIST,
		SET;
	}

	protected CollectionType collectionType;
	private ParameterizedTypeName collectionTypeName;
	private TypeName elementTypeName;

	public AbstractCollectionTransform(ParameterizedTypeName clazz, CollectionType collectionType) {
		this.collectionType=collectionType;

		this.collectionTypeName = clazz;
		// for now, it supports only parameterized type with 1 argument 
		this.elementTypeName = clazz.typeArguments.get(0);
	}
	
	/**
	 * Only for arrays
	 * 
	 * @param clazz
	 * @param collectionType
	 */
	public AbstractCollectionTransform(TypeName clazz, CollectionType collectionType) {
		this.collectionType=collectionType;

		this.collectionTypeName = null;
		// for now, it supports only parameterized type with 1 argument 
		this.elementTypeName = clazz;
	}
	
	protected Class<?> collectionClazz=List.class;
	protected Class<?> defaultClazz=ArrayList.class;		

	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.toString().startsWith(collectionClazz.getCanonicalName())) { 
			return defaultClazz;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void generateParseOnJackson(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, false);
	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(methodBuilder, parserName, beanClass, beanName, property, true);
	}
	
	public void generateParseOnJacksonInternal(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.START_ARRAY)", parserName, JsonToken.class);
			if (collectionType==CollectionType.ARRAY)
			{
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", ArrayList.class, elementTypeName, ArrayList.class);
			} else {
				methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), elementTypeName, defineCollectionClass(collectionTypeName));
			}
			
			methodBuilder.addStatement("$T item=$L", elementTypeName, DEFAULT_VALUE);

			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();

			methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
				methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.VALUE_NULL)", parserName, JsonToken.class);
					methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
				methodBuilder.nextControlFlow("else");
				if (onString)
				{
					transform.generateParseOnJacksonAsString(methodBuilder, parserName, null, "item", elementProperty);
				} else {
					transform.generateParseOnJackson(methodBuilder, parserName, null, "item", elementProperty);
				}
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("collection.add(item)");
			methodBuilder.endControlFlow();
			
			if (collectionType==CollectionType.ARRAY)
			{
				if (TypeUtility.isTypePrimitive(elementTypeName))
				{
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$LTypeArray(collection)"), CollectionUtility.class, elementTypeName.box());
				} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"), CollectionUtility.class, elementTypeName);
				} else {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"), CollectionUtility.class, elementTypeName);
				}				
			} else {				
				methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));
			}

		methodBuilder.endControlFlow();
		//@formatter:on
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("");
		
		if (collectionType==CollectionType.ARRAY)
		{
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", ArrayList.class, elementTypeName, ArrayList.class);
		} else {
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), elementTypeName, defineCollectionClass(collectionTypeName));
		}
				
		methodBuilder.addStatement("$T item", elementTypeName);
			
		BindTransform transform=BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
						
		if (property.xmlInfo.isWrappedCollection())
		{					
			methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT && !$L.getName().toString().equals($S))", parserName, parserName, property.xmlInfo.tagElement);
		} else {
			methodBuilder.addCode("// add first element\n");
			methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
				methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
				methodBuilder.addStatement("$L.nextTag()", parserName);
			methodBuilder.nextControlFlow("else");
			transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("collection.add(item)");
			
			methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT && $L.getName().toString().equals($S))", parserName, parserName, property.xmlInfo.tag);
		}
		
		if (property.xmlInfo.isWrappedCollection())
		{
				methodBuilder.beginControlFlow("if ($L.getName().toString().equals($S))", parserName, property.xmlInfo.tagElement);
		}
					methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
						methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
						methodBuilder.addStatement("$L.nextTag()", parserName);						
					methodBuilder.nextControlFlow("else");
						transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
					methodBuilder.endControlFlow();
					methodBuilder.addStatement("collection.add(item)");
		if (property.xmlInfo.isWrappedCollection())
		{
				methodBuilder.endControlFlow();
		}
			methodBuilder.endControlFlow();
    	                 	
			if (collectionType==CollectionType.ARRAY)
			{
				if (TypeUtility.isTypePrimitive(elementTypeName))
				{
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TTypeArray(collection)"), CollectionUtility.class, elementTypeName.box());
				} else if (TypeUtility.isTypeWrappedPrimitive(elementTypeName)) {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.as$TArray(collection)"), CollectionUtility.class, elementTypeName);
				} else {
					methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.asArray(collection, new $T[collection.size()])"), CollectionUtility.class, elementTypeName);
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
		
			if (collectionType==CollectionType.LIST) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			} else if (collectionType==CollectionType.ARRAY) {
				methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			}
		
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonName);
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
				
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeNull()", serializerName);
				methodBuilder.nextControlFlow("else");
					if (onString)
					{
						transform.generateSerializeOnJacksonAsString(methodBuilder, serializerName, null, "item", elementProperty);
					} else {
						transform.generateSerializeOnJackson(methodBuilder, serializerName, null, "item", elementProperty);	
					}
					
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
		methodBuilder.endControlFlow();
		//@formatter:on
	}
	
	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			
			if (collectionType==CollectionType.LIST) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			} else if (collectionType==CollectionType.ARRAY) {
				methodBuilder.addStatement("int n=$L.length", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			}
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addCode("// write wrapper tag\n");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			}
			
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
			
			if (collectionType==CollectionType.SET)
			{
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));				
			} else if (collectionType==CollectionType.LIST) {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			} else if (collectionType==CollectionType.ARRAY) {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L[i]", getter(beanName, beanClass, property));
			}
				
			if (property.isNullable())
			{
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.xmlInfo.tagElement);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnXml(methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			} else {
				transform.generateSerializeOnXml(methodBuilder, serializerName, null, "item", elementProperty);
			}
				
			methodBuilder.endControlFlow();
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			}
						
			methodBuilder.endControlFlow();
			//@formatter:on
	}

}
