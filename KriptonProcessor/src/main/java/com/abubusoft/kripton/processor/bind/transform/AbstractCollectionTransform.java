/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

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

	private ParameterizedTypeName collectionTypeName;
	private TypeName elementTypeName;
	protected boolean useForEach;

	public AbstractCollectionTransform(ParameterizedTypeName clazz, boolean useForEach) {
		this.useForEach=useForEach;

		this.collectionTypeName = clazz;
		this.elementTypeName = collectionTypeName.typeArguments.get(0);
	}

	protected abstract Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName);

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("");
		
		methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(collectionTypeName));		
		methodBuilder.addStatement("$T item", elementTypeName);
			
		BindTransform transform=BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
						
		if (property.xmlInfo.isWrappedCollection())
		{					
			methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT && !$L.getName().toString().equals($S))", parserName, parserName, property.xmlInfo.tag);
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
				methodBuilder.beginControlFlow("if ($L.getName().toString().equals($S))", parserName, property.xmlInfo.tagElement);
					methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
						methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
						methodBuilder.addStatement("$L.nextTag()", parserName);						
					methodBuilder.nextControlFlow("else");
						transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
					methodBuilder.endControlFlow();
					methodBuilder.addStatement("collection.add(item)");
				methodBuilder.endControlFlow();
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
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			
			if (!useForEach) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			}
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addCode("// write wrapper tag\n");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			}
			
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
			
			if (useForEach)
			{
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));				
			} else {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			}
				
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.xmlInfo.tagElement);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnXml(methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			}
						
			methodBuilder.endControlFlow();
	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		
			if (!useForEach) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			}			
		
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonName);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
					
			if (useForEach)
			{
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));				
			} else {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			}
				
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeNull()", serializerName);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnJackson(methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
		methodBuilder.endControlFlow();
		//@formatter:on
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));			
			if (!useForEach) {
				methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
				methodBuilder.addStatement("$T item", elementTypeName);
			}		
		
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonName);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
		
			if (useForEach)
			{
				methodBuilder.beginControlFlow("for ($T item: $L)", elementTypeName, getter(beanName, beanClass, property));				
			} else {
				methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
			}
						
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeNull()", serializerName);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnJacksonAsString(methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
		
	        methodBuilder.addStatement("$L.writeEndArray()", serializerName);
		methodBuilder.endControlFlow();
		//@formatter:on
		
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.START_ARRAY)", parserName, JsonToken.class); 
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(collectionTypeName));
			methodBuilder.addStatement("$T item=$L", elementTypeName, DEFAULT_VALUE);
				
			BindTransform transform=BindTransformer.lookup(elementTypeName);
			BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
						
			methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
				methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.VALUE_NULL)", parserName, JsonToken.class);				
					methodBuilder.addStatement("item=$L", DEFAULT_VALUE);									
				methodBuilder.nextControlFlow("else");
					transform.generateParseOnJackson(methodBuilder, parserName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("collection.add(item)");				
			methodBuilder.endControlFlow();
				methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));		
			
		methodBuilder.endControlFlow();
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.START_ARRAY)", parserName, JsonToken.class); 
		methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(collectionTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(collectionTypeName));
		methodBuilder.addStatement("$T item=$L", elementTypeName, DEFAULT_VALUE);
			
		BindTransform transform=BindTransformer.lookup(elementTypeName);
		BindProperty elementProperty=BindProperty.builder(elementTypeName, property).build();
					
		methodBuilder.beginControlFlow("while ($L.nextToken() != $T.END_ARRAY)", parserName, JsonToken.class);
			methodBuilder.beginControlFlow("if ($L.getCurrentToken()==$T.VALUE_NULL)", parserName, JsonToken.class);				
				methodBuilder.addStatement("item=$L", DEFAULT_VALUE);									
			methodBuilder.nextControlFlow("else");
				transform.generateParseOnJacksonAsString(methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("collection.add(item)");				
		methodBuilder.endControlFlow();
			methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));		
		
		methodBuilder.endControlFlow();
	}

}
