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
/**
 * 
 */
package com.abubusoft.kripton.processor.bind;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.io.IOException;
import java.util.Comparator;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.fasterxml.jackson.core.JsonGenerator;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author xcesco
 *
 */
public class BindTypeBuilder {

	protected static Builder builder;

	protected static final String PREFIX = "";

	protected static final String SUFFIX = KriptonBinder2.MAPPER_CLASS_SUFFIX;

	/**
	 * Generate shared preference manager
	 * 
	 * @return name of generated class
	 * 
	 * @throws IOException
	 */
	public static String generate(Elements elementUtils, Filer filer, BindEntity item) throws IOException {
		String beanClassName = item.getSimpleName().toString();

		boolean needSuffix = true;
		if (beanClassName.endsWith(SUFFIX)) {
			needSuffix = false;
			// String msg = String.format("Class %s must have a name with suffix \"%s\" to be used with @%s", beanClassName, SUFFIX, BindSharedPreferences.class.getSimpleName());
			// throw (new InvalidNameException(msg));
		}

		String className = PREFIX + beanClassName + (needSuffix ? SUFFIX : "");
		ModelAnnotation annotation = item.getAnnotation(BindType.class);
		String bindTypeName = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_NAME);

		PackageElement pkg = elementUtils.getPackageOf(item.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindType.class, packageName, className);
		//@formatter:off
		builder = TypeSpec.classBuilder(className)
				.addAnnotation(BindMap.class)
				.addModifiers(Modifier.PUBLIC)
				.superclass(TypeUtility.parameterizedTypeName(className(AbstractMapper.class), typeName(item.getElement().asType())));
		//@formatter:on
		builder.addJavadoc("This class is the shared preference binder defined for $T\n\n", item.getElement());
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", item.getElement());

		// createInstance
		generateCreateInstance(item);
		
		// order item by order, property name 
		Collections.sort(item.getCollection(), new Comparator<BindProperty>() {

			@Override
			public int compare(BindProperty lhs, BindProperty rhs) {
				int c1=lhs.order-rhs.order;
				if (c1!=0) return c1;
				
				return lhs.getName().compareTo(rhs.getName());
			}
		});

		// generate serializeOnJackson
		generateSerializeOnJackson(item);

		// generate serializeOnJacksonAsString
		generateSerializeOnJacksonAsString(item);

		// order item by type (attribute, element, value), order, xmlName
		Collections.sort(item.getCollection(), new Comparator<BindProperty>() {

			@Override
			public int compare(BindProperty lhs, BindProperty rhs) {
				int c1=lhs.xmlInfo.xmlType.ordinal()-rhs.xmlInfo.xmlType.ordinal();				
				if (c1!=0) return c1;
				
				c1=lhs.order-rhs.order;
				if (c1!=0) return c1;
				
				return lhs.xmlInfo.tagName.compareTo(rhs.xmlInfo.tagName);
			}
		});
		
		// generate serializeOnXml
		generateSerializeOnXml(item);

		// generate parseOnJackson
		generateParseOnJackson(item);

		// generate parseOnJacksonAsString
		generateParseOnJacksonAsString(item);

		// generate parseOnXml
		generateParseOnXml(item);

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

		return className;
	}

	/**
	 * @param item
	 */
	public static void generateCreateInstance(BindEntity item) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("createInstance").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addJavadoc("create new object instance\n").returns(typeName(item.getElement()));
		method.addStatement("return new $T()", typeName(item.getElement()));
		builder.addMethod(method.build());
	}

	private static void generateParseOnJackson(BindEntity entity) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("parseOnJackson").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter(typeName(JacksonContext.class), "context")
				.addParameter(typeName(JacksonWrapperParser.class), "wrapper").addParameter(typeName(Boolean.TYPE), "readStartAndEnd").addJavadoc("create new object instance\n").returns(typeName(entity.getElement()));
		method.addStatement("return new $T()", typeName(entity.getElement()));
		builder.addMethod(method.build());

	}

	private static void generateParseOnJacksonAsString(BindEntity item) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("parseOnJacksonAsString").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter(typeName(JacksonContext.class), "context")
				.addParameter(typeName(JacksonWrapperParser.class), "wrapper").addParameter(typeName(Boolean.TYPE), "readStartAndEnd").addJavadoc("create new object instance\n").returns(typeName(item.getElement()));
		method.addStatement("return new $T()", typeName(item.getElement()));
		builder.addMethod(method.build());

	}

	private static void generateParseOnXml(BindEntity item) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("parseOnXml").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter(typeName(XmlBinderContext.class), "context")
				.addParameter(typeName(XmlWrapperParser.class), "wrapper").addParameter(typeName(Integer.TYPE), "currentEventType").addJavadoc("create new object instance\n").returns(typeName(item.getElement()));
		method.addStatement("return new $T()", typeName(item.getElement()));
		builder.addMethod(method.build());

	}

	private static void generateSerializeOnJackson(BindEntity entity) {
		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnJackson")
				.addModifiers(Modifier.PUBLIC)
				.addJavadoc("reset shared preferences\n")
				.addParameter(typeName(JacksonContext.class), "context")
				.addParameter(typeName(entity.getElement()), "object")
				.addParameter(typeName(JacksonWrapperSerializer.class), "wrapper")
				.addParameter(typeName(Boolean.TYPE), "writeStartAndEnd")
				.returns(Void.TYPE);
		//@formatter:on
		
		methodBuilder.beginControlFlow("try");
		methodBuilder.addStatement("$T jacksonSerializer = wrapper.jacksonGenerator", className(JsonGenerator.class));
		methodBuilder.beginControlFlow("if (writeStartAndEnd)");
		methodBuilder.addStatement("jacksonSerializer.writeStartObject()");
		methodBuilder.endControlFlow();

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// fields
		methodBuilder.addCode("// Serialized Field:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L\n", item.getName());
			bindTransform.generateSerializeOnJackson(methodBuilder, "jacksonSerializer", typeName(item.getPropertyType()), "object", item, item.xmlInfo.xmlType);
			methodBuilder.addCode("\n");
		}
		
		methodBuilder.beginControlFlow("if (writeStartAndEnd)");
		methodBuilder.addStatement("jacksonSerializer.writeEndObject()");
		methodBuilder.endControlFlow();

		methodBuilder.nextControlFlow("catch($T e)", typeName(IOException.class));
		methodBuilder.addStatement("e.printStackTrace()");
		methodBuilder.addStatement("throw (new $T(e))", typeName(KriptonRuntimeException.class));
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());
	}

	private static void generateSerializeOnJacksonAsString(BindEntity entity) {
		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnJacksonAsString")
				.addModifiers(Modifier.PUBLIC)
				.addJavadoc("reset shared preferences\n")
				.addParameter(typeName(JacksonContext.class), "context")
				.addParameter(typeName(entity.getElement()), "object")
				.addParameter(typeName(JacksonWrapperSerializer.class), "wrapper")
				.addParameter(typeName(Boolean.TYPE), "writeStartAndEnd")
				.returns(Void.TYPE);
		//@formatter:on

		methodBuilder.beginControlFlow("try");
		methodBuilder.addStatement("$T jacksonSerializer = wrapper.jacksonGenerator", className(JsonGenerator.class));
		methodBuilder.beginControlFlow("if (writeStartAndEnd)");
		methodBuilder.addStatement("jacksonSerializer.writeStartObject()");
		methodBuilder.endControlFlow();

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// fields
		methodBuilder.addCode("// Serialized Field:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L\n", item.getName());
			bindTransform.generateSerializeOnJacksonAsString(methodBuilder, "jacksonSerializer", typeName(item.getPropertyType()), "object", item, item.xmlInfo.xmlType);
			methodBuilder.addCode("\n");
		}
		
		methodBuilder.beginControlFlow("if (writeStartAndEnd)");
		methodBuilder.addStatement("jacksonSerializer.writeEndObject()");
		methodBuilder.endControlFlow();

		methodBuilder.nextControlFlow("catch($T e)", typeName(IOException.class));
		methodBuilder.addStatement("e.printStackTrace()");
		methodBuilder.addStatement("throw (new $T(e))", typeName(KriptonRuntimeException.class));
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());

	}

	private static void generateSerializeOnXml(BindEntity entity) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnXml").addModifiers(Modifier.PUBLIC).addJavadoc("reset shared preferences\n").addParameter(typeName(XmlBinderContext.class), "context")
				.addParameter(typeName(entity.getElement()), "object").addParameter(typeName(XmlWrapperSerializer.class), "wrapper").addParameter(typeName(Integer.TYPE), "currentEventType").returns(Void.TYPE);

		methodBuilder.beginControlFlow("try");
		methodBuilder.addStatement("$T xmlSerializer = wrapper.xmlSerializer", className(XMLStreamWriter2.class));

		methodBuilder.beginControlFlow("if (currentEventType == 0)");
		methodBuilder.addStatement("xmlSerializer.writeStartElement(\"$L\")", entity.xmlInfo.tagName);
		methodBuilder.endControlFlow();

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// attributes
		methodBuilder.addCode("// Persisted fields:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L\n", item.getName());
			bindTransform.generateSerializeOnXml(methodBuilder, "xmlSerializer", typeName(item.getPropertyType()), "object", item, item.xmlInfo.xmlType);
			// Transformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor", "index" + i + "");
			// methodBuilder.addCode(";");

			methodBuilder.addCode("\n");
		}	
		
		methodBuilder.nextControlFlow("catch($T e)", typeName(XMLStreamException.class));
		methodBuilder.addStatement("e.printStackTrace()");
		methodBuilder.addStatement("throw (new $T(e))", typeName(KriptonRuntimeException.class));
		methodBuilder.endControlFlow();

		builder.addMethod(methodBuilder.build());
	}

}
