package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class SetTransformation extends AbstractBindTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> utilClazz;
	private ParameterizedTypeName listTypeName;
	private TypeName rawTypeName;

	public SetTransformation(ParameterizedTypeName clazz) {
		this.utilClazz = ProcessorHelper.class;

		this.listTypeName = clazz;
		this.rawTypeName = listTypeName.typeArguments.get(0);
	}

	private Class<?> defineCollectionClass(ParameterizedTypeName listTypeName) {
		if (listTypeName.toString().startsWith(Set.class.getCanonicalName())) {
			// it's a list
			return HashSet.class;
		}
		try {
			return Class.forName(listTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		//@formatter:off
		methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(listTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(listTypeName));
		methodBuilder.addStatement("$T item", TypeUtility.className(property.getPropertyType().getComposedValue()));
			
		BindTransform transform=BindTransformer.lookup(rawTypeName);
		BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
						
		if (property.xmlInfo.isWrappedCollection())
		{					
			methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT && !$L.getName().toString().equals($S))", parserName, parserName, property.xmlInfo.tag);
		} else {
			methodBuilder.addCode("// add first element\n");
			methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
				methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
				methodBuilder.addStatement("$L.skipElement()", parserName);				
			methodBuilder.nextControlFlow("else");
			transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.endControlFlow();
			methodBuilder.addStatement("collection.add(item)");
			
			methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT)", parserName);
		}
				methodBuilder.beginControlFlow("if ($L.getName().toString().equals($S))", parserName, property.xmlInfo.tagElement);
					methodBuilder.beginControlFlow("if ($L.isEmptyElement())", parserName);				
						methodBuilder.addStatement("item=$L", DEFAULT_VALUE);
						methodBuilder.addStatement("$L.skipElement()", parserName);				
					methodBuilder.nextControlFlow("else");
						transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
					methodBuilder.endControlFlow();
					methodBuilder.addStatement("collection.add(item)");
				methodBuilder.endControlFlow();			
			methodBuilder.endControlFlow();
    	                 	
			methodBuilder.addStatement(setter(beanClass, beanName, property, "collection"));			
		//@formatter:on
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
			//methodBuilder.addStatement("$T item", rawTypeName);
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addCode("// write wrapper tag\n");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			}
			
			BindTransform transform=BindTransformer.lookup(rawTypeName);
			BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
			
			methodBuilder.beginControlFlow("for ($T item: $L)", rawTypeName, getter(beanName, beanClass, property));
				//methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
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
			methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
			//methodBuilder.addStatement("$T item", rawTypeName);
		
			BindTransform transform=BindTransformer.lookup(rawTypeName);
			BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonName);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
		
			methodBuilder.beginControlFlow("for ($T item: $L)", rawTypeName, getter(beanName, beanClass, property));
				//methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
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
			methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
			//methodBuilder.addStatement("$T item", rawTypeName);
		
			BindTransform transform=BindTransformer.lookup(rawTypeName);
			BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
		
			methodBuilder.addCode("// write wrapper tag\n");
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.jacksonName);
			methodBuilder.addStatement("$L.writeStartArray()", serializerName);
		
			//methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
			methodBuilder.beginControlFlow("for ($T item: $L)", rawTypeName, getter(beanName, beanClass, property));
				//methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
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
			methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(listTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(listTypeName));
			methodBuilder.addStatement("$T item=$L", rawTypeName, DEFAULT_VALUE);
				
			BindTransform transform=BindTransformer.lookup(rawTypeName);
			BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
						
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
		methodBuilder.addStatement("$T<$T> collection=new $T<>()", defineCollectionClass(listTypeName), TypeUtility.className(property.getPropertyType().getComposedValue()), defineCollectionClass(listTypeName));
		methodBuilder.addStatement("$T item=$L", rawTypeName, DEFAULT_VALUE);
			
		BindTransform transform=BindTransformer.lookup(rawTypeName);
		BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
					
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
