package com.abubusoft.kripton.processor.core;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class ManagedPropertyPersistenceHelper {

	public enum PersistType {
		STRING, BYTE
	}
	
	public static String DEFAULT_FIELD_NAME="element";

	public static void generateFieldPersistance(Builder builder, List<? extends ManagedModelProperty> collection, PersistType persistType, boolean forceName, Modifier ... modifiers) {		
		
		for (ManagedModelProperty property : collection) {
			if (property.bindProperty != null) {
				// if defined a forced name, we use it to define every json mapping, to allow comparison with parameters
				if (forceName) property.bindProperty.label=DEFAULT_FIELD_NAME;
				
				generateFieldSerialize(builder, persistType, property.bindProperty, modifiers);
				generateFieldParser(builder, persistType, property.bindProperty, modifiers);
			}
		}

	}

	public static void generateFieldSerialize(Builder builder, PersistType persistType, BindProperty property, Modifier ...modifiers) {
		Converter<String, String> format = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		String methodName = "serialize" + format.convert(property.getName());

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName)
				.addJavadoc("write\n")
				.addParameter(ParameterSpec.builder(typeName(property.getElement()), "value").build())
				.addModifiers(modifiers);

		switch (persistType) {
		case STRING:
			methodBuilder.returns(className(String.class));
			break;
		case BYTE:
			methodBuilder.returns(TypeUtility.arrayTypeName(Byte.TYPE));
			break;
		}

		methodBuilder.beginControlFlow("if (value==null)");
		methodBuilder.addStatement("return null");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("$T context=$T.jsonBind()", KriptonJsonContext.class, KriptonBinder.class);
		methodBuilder.beginControlFlow("try ($T stream=new $T(); $T wrapper=context.createSerializer(stream))", KriptonByteArrayOutputStream.class, KriptonByteArrayOutputStream.class, JacksonWrapperSerializer.class );
		methodBuilder.addStatement("$T jacksonSerializer=wrapper.jacksonGenerator", JsonGenerator.class);

		if (!property.isBindedObject()) {
			methodBuilder.addStatement("jacksonSerializer.writeStartObject()");
		}

		methodBuilder.addStatement("int fieldCount=0");
				
		BindTransform bindTransform = BindTransformer.lookup(property);
		String serializerName = "jacksonSerializer";
		bindTransform.generateSerializeOnJackson(methodBuilder, serializerName, null, "value", property);

		if (!property.isBindedObject()) {
			methodBuilder.addStatement("jacksonSerializer.writeEndObject()");
		}
		
		methodBuilder.addStatement("jacksonSerializer.flush()");

		switch (persistType) {
		case STRING:
			methodBuilder.addStatement("return stream.toString()");
			break;
		case BYTE:
			methodBuilder.addStatement("return stream.toByteArray()");
			break;
		}

		methodBuilder.nextControlFlow("catch($T e)", Exception.class);
		methodBuilder.addStatement("throw(new $T(e.getMessage()))", KriptonRuntimeException.class);
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());
	}

	public static void generateFieldParser(Builder builder, PersistType persistType, BindProperty property, Modifier ... modifiers) {
		Converter<String, String> format = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("parse" + format.convert(property.getName())).addJavadoc("parse\n").returns(typeName(property.getElement()));
		methodBuilder.addModifiers(modifiers);
		
		switch (persistType) {
		case STRING:
			methodBuilder.addParameter(ParameterSpec.builder(className(String.class), "input").build());
			break;
		case BYTE:
			methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(Byte.TYPE), "input").build());
			break;
		}

		methodBuilder.beginControlFlow("if (input==null)");
		methodBuilder.addStatement("return null");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("$T context=$T.jsonBind()", KriptonJsonContext.class, KriptonBinder.class);

		methodBuilder.beginControlFlow("try ($T wrapper=context.createParser(input))", JacksonWrapperParser.class);
		methodBuilder.addStatement("$T jacksonParser=wrapper.jacksonParser", JsonParser.class);
		
		methodBuilder.addCode("// START_OBJECT\n");
		methodBuilder.addStatement("jacksonParser.nextToken()");
		
		if (!property.isBindedObject()) {					      
			methodBuilder.addCode("// value of \"element\"\n");
			methodBuilder.addStatement("jacksonParser.nextValue()");	
		}		
		
		String parserName = "jacksonParser";
		BindTransform bindTransform = BindTransformer.lookup(property);

		methodBuilder.addStatement("$T result=null", property.getPropertyType().getName());

		bindTransform.generateParseOnJackson(methodBuilder, parserName, null, "result", property);

		methodBuilder.addStatement("return result");

		methodBuilder.nextControlFlow("catch($T e)", Exception.class);
		methodBuilder.addStatement("throw(new $T(e.getMessage()))", KriptonRuntimeException.class);
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());

	}

	public static void generateParamSerializer(Builder builder, String methodName, TypeName parameterTypeName, PersistType persistType) {
		methodName = SQLDaoDefinition.PARAM_SERIALIZER_PREFIX + methodName;
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName).addJavadoc("write\n").addParameter(ParameterSpec.builder(parameterTypeName, "value").build());
		
		methodBuilder.addModifiers(Modifier.PROTECTED, Modifier.STATIC);
		switch (persistType) {
		case STRING:
			methodBuilder.returns(className(String.class));
			break;
		case BYTE:
			methodBuilder.returns(TypeUtility.arrayTypeName(Byte.TYPE));
			break;
		}

		methodBuilder.beginControlFlow("if (value==null)");
		methodBuilder.addStatement("return null");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("$T context=$T.jsonBind()", KriptonJsonContext.class, KriptonBinder.class);
		methodBuilder.beginControlFlow("try ($T stream=new $T(); $T wrapper=context.createSerializer(stream))", KriptonByteArrayOutputStream.class, KriptonByteArrayOutputStream.class, JacksonWrapperSerializer.class );
		methodBuilder.addStatement("$T jacksonSerializer=wrapper.jacksonGenerator", JsonGenerator.class);
		
		methodBuilder.addStatement("int fieldCount=0");
		
		BindTransform bindTransform = BindTransformer.lookup(parameterTypeName);
		String serializerName = "jacksonSerializer";
		
		boolean isInCollection=true;
		if (!BindTransformer.isBindedObject(parameterTypeName)) {
			methodBuilder.addStatement("$L.writeStartObject()", serializerName);
			isInCollection=false;
		}
		
		BindProperty property=BindProperty.builder(parameterTypeName).inCollection(isInCollection).elementName(DEFAULT_FIELD_NAME).build();		
		bindTransform.generateSerializeOnJackson(methodBuilder, serializerName, null, "value", property);

		if (!BindTransformer.isBindedObject(parameterTypeName)) {
			methodBuilder.addStatement("$L.writeEndObject()", serializerName);
		}
		
		methodBuilder.addStatement("$L.flush()", serializerName);

		switch (persistType) {
		case STRING:
			methodBuilder.addStatement("return stream.toString()");
			break;
		case BYTE:
			methodBuilder.addStatement("return stream.toByteArray()");
			break;
		}

		methodBuilder.nextControlFlow("catch($T e)", Exception.class);
		methodBuilder.addStatement("throw(new $T(e.getMessage()))", KriptonRuntimeException.class);
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());

	}
	
	public static void generateParamParser(Builder builder, String methodName, TypeName parameterTypeName, PersistType persistType) {
		methodName = SQLDaoDefinition.PARAM_PARSER_PREFIX + methodName;

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName).addJavadoc("parse\n").returns(parameterTypeName);
		methodBuilder.addModifiers(Modifier.PROTECTED, Modifier.STATIC);
		
		switch (persistType) {
		case STRING:
			methodBuilder.addParameter(ParameterSpec.builder(className(String.class), "input").build());
			break;
		case BYTE:
			methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(Byte.TYPE), "input").build());
			break;
		}

		methodBuilder.beginControlFlow("if (input==null)");
		methodBuilder.addStatement("return null");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("$T context=$T.jsonBind()", KriptonJsonContext.class, KriptonBinder.class);

		methodBuilder.beginControlFlow("try ($T wrapper=context.createParser(input))", JacksonWrapperParser.class);
		methodBuilder.addStatement("$T jacksonParser=wrapper.jacksonParser", JsonParser.class);
				
		methodBuilder.addCode("// START_OBJECT\n");
		methodBuilder.addStatement("jacksonParser.nextToken()");
	      
		methodBuilder.addCode("// value of \"element\"\n");
		methodBuilder.addStatement("jacksonParser.nextValue()");

		String parserName = "jacksonParser";
		BindTransform bindTransform = BindTransformer.lookup(parameterTypeName);

		methodBuilder.addStatement("$T result=null", parameterTypeName);

		BindProperty property=BindProperty.builder(parameterTypeName).inCollection(false).elementName(DEFAULT_FIELD_NAME).build();
		bindTransform.generateParseOnJackson(methodBuilder, parserName, null, "result", property);

		methodBuilder.addStatement("return result");

		methodBuilder.nextControlFlow("catch($T e)", Exception.class);
		methodBuilder.addStatement("throw(new $T(e.getMessage()))", KriptonRuntimeException.class);
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());

	}
}
