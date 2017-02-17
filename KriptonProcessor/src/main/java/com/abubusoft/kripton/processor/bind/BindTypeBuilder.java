/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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
package com.abubusoft.kripton.processor.bind;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.AbstractElementVisitor7;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class BindTypeBuilder {

	// protected static
	public static class VisitResult {
		
		public VisitResult(Elements elementUtils)
		{
			this.elementUtils=elementUtils;
		}
		
		public TypeMirror rawType;
		
		public List<? extends TypeMirror> argumentTypes;
		
		public Elements elementUtils;

	}

	protected static AbstractElementVisitor7<Void, VisitResult> visitor = new AbstractElementVisitor7<Void, VisitResult>() {

		@Override
		public Void visitPackage(PackageElement e, VisitResult p) {
			System.out.println("visitPackage "+e.asType());
			return null;
		}

		@Override
		public Void visitType(TypeElement e, VisitResult p) {
			System.out.println("visitType "+e.asType());
			
			if (e.getSuperclass() instanceof DeclaredType) {
		          DeclaredType superclassDeclaredType = (DeclaredType) e.getSuperclass();
		          
		          System.out.println("visitType parent "+superclassDeclaredType.getTypeArguments().size());		          		          
			}
						
			return null;
		}

		@Override
		public Void visitVariable(VariableElement e, VisitResult p) {
			System.out.println("visitVariable "+e.asType());
			
			
			
			return null;
		}

		@Override
		public Void visitExecutable(ExecutableElement e, VisitResult p) {
			System.out.println("visitExecutable "+e.asType());
			return null;
		}

		@Override
		public Void visitTypeParameter(TypeParameterElement e, VisitResult p) {
			System.out.println("visitTypeParameter "+e.asType());
			
			
			return null;
		}

	};

	protected static final String PREFIX = "";

	protected static final String SUFFIX = KriptonBinder.MAPPER_CLASS_SUFFIX;

	/**
	 * @return typeName of generated class
	 * 
	 * @throws IOException
	 */
	public static String generate(Elements elementUtils, Filer filer, BindEntity item) throws IOException {
		String beanClassName = item.getSimpleName().toString();

		boolean needSuffix = true;
		if (beanClassName.endsWith(SUFFIX)) {
			needSuffix = false;
		}

		String className = PREFIX + beanClassName + (needSuffix ? SUFFIX : "");

		PackageElement pkg = elementUtils.getPackageOf(item.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindType.class, packageName, className);

		// @formatter:off		
		TypeSpec.Builder builder = TypeSpec.classBuilder(className)
				.addAnnotation(AnnotationSpec.builder(BindMap.class)
						.addMember("value", "$T.class",typeName(item.getElement().asType()))
						.build())
				.addModifiers(Modifier.PUBLIC)
				.superclass(TypeUtility.parameterizedTypeName(className(AbstractMapper.class), typeName(item.getElement().asType())));
		
		BindTypeContext context=new BindTypeContext(builder, TypeUtility.typeName(packageName, className), Modifier.PRIVATE);		
		// @formatter:on
		builder.addJavadoc("This class is binder map for $T\n\n", item.getElement());
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", item.getElement());

		// order item by order, property typeName
		Collections.sort(item.getCollection(), new Comparator<BindProperty>() {

			@Override
			public int compare(BindProperty lhs, BindProperty rhs) {
				int c1 = lhs.order - rhs.order;
				if (c1 != 0)
					return c1;

				return lhs.getName().compareTo(rhs.getName());
			}
		});

		// generate serializeOnJackson
		generateSerializeOnJackson(context, item);

		// generate serializeOnJacksonAsString
		generateSerializeOnJacksonAsString(context, item);

		// order item by type (attribute, element, value), order, xmlName
		Collections.sort(item.getCollection(), new Comparator<BindProperty>() {

			@Override
			public int compare(BindProperty lhs, BindProperty rhs) {
				int c1 = lhs.xmlInfo.xmlType.ordinal() - rhs.xmlInfo.xmlType.ordinal();
				if (c1 != 0)
					return c1;

				c1 = lhs.order - rhs.order;
				if (c1 != 0)
					return c1;

				return lhs.label.compareTo(rhs.label);
			}
		});

		// generate serializeOnXml
		generateSerializeOnXml(context, item);

		// generate parseOnJackson
		generateParseOnJackson(context, item);

		// generate parseOnJacksonAsString
		generateParseOnJacksonAsString(context, item);

		// generate parseOnXml
		generateParseOnXml(context, item);

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

		return className;
	}

	/**
	 * <p>
	 * Generate method to parse xml stream.
	 * </p>
	 * 
	 * @param bean
	 *            kind of object to manage
	 */
	private static void generateParseOnXml(BindTypeContext context, BindEntity bean) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("parseOnXml")
				.addJavadoc("parse xml\n")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
					//.addParameter(typeName(KriptonXmlContext.class), "context")
					.addParameter(typeName(XMLParser.class), "xmlParser")
					.addParameter(typeName(Integer.TYPE), "currentEventType")
				.returns(typeName(bean.getElement())).addException(Exception.class);
		// @formatter:on

		// methodBuilder.beginControlFlow("try");
		// methodBuilder.addStatement("$T xmlParser = wrapper.xmlParser",
		// XmlPullParser.class);
		methodBuilder.addStatement("$T instance = new $T()", bean.getElement(), bean.getElement());
		methodBuilder.addStatement("int eventType = currentEventType");
		methodBuilder.addStatement("boolean read=true");

		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (currentEventType == 0)");
		methodBuilder.addStatement("eventType = xmlParser.next()");
		methodBuilder.nextControlFlow("else");
		methodBuilder.addStatement("eventType = xmlParser.getEventType()");
		methodBuilder.endControlFlow();
		methodBuilder.addStatement("String currentTag = xmlParser.getName().toString()");

		methodBuilder.addStatement("String elementName = currentTag");

		generateParseOnXmlAttributes(context, methodBuilder, bean);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("//sub-elements\n");
		methodBuilder.beginControlFlow("while (xmlParser.hasNext() && elementName!=null)");

		methodBuilder.beginControlFlow("if (read)");
		methodBuilder.addStatement("eventType = xmlParser.next()");
		methodBuilder.nextControlFlow("else");
		methodBuilder.addStatement("eventType = xmlParser.getEventType()");
		methodBuilder.endControlFlow();
		methodBuilder.addStatement("read=true");

		methodBuilder.beginControlFlow("switch(eventType)$>");
		methodBuilder.addCode("case $T.START_TAG:\n$>", XmlPullParser.class);
		generateParserOnXmlStartElement(context, methodBuilder, "instance", "xmlParser", bean);
		methodBuilder.addStatement("$<break");

		methodBuilder.addCode("case $T.END_TAG:\n$>", XmlPullParser.class);
		generateParserOnXmlEndElement(context, methodBuilder, "instance", "xmlParser", bean);
		methodBuilder.addStatement("$<break");

		methodBuilder.addCode("case $T.CDSECT:\n", XmlPullParser.class);
		methodBuilder.addCode("case $T.TEXT:\n$>", XmlPullParser.class);
		generateParserOnXmlCharacters(context, methodBuilder, "instance", "xmlParser", bean);
		methodBuilder.addStatement("$<break");

		methodBuilder.addCode("default:\n$>");
		methodBuilder.addStatement("$<break");
		methodBuilder.addCode("$<");
		methodBuilder.endControlFlow();
		// @formatter:on

		methodBuilder.endControlFlow();

		methodBuilder.addStatement("return instance");
		// methodBuilder.nextControlFlow("catch($T e)",
		// typeName(Exception.class));
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw (new $T(e))",
		// typeName(KriptonRuntimeException.class));
		// methodBuilder.endControlFlow();

		context.builder.addMethod(methodBuilder.build());
	}

	private static void generateParserOnXmlEndElement(BindTypeContext context, MethodSpec.Builder methodBuilder, String instanceName, String parserName, BindEntity entity) {
		methodBuilder.beginControlFlow("if (elementName.equals($L.getName()))", parserName);
		methodBuilder.addStatement("currentTag = elementName");
		methodBuilder.addStatement("elementName = null");
		methodBuilder.endControlFlow();

	}

	private static void generateParseOnXmlAttributes(BindTypeContext context, MethodSpec.Builder methodBuilder, BindEntity entity) {
		BindTransform bindTransform;

		int count = 0;
		// count property to manage
		{
			// for each elements
			for (BindProperty property : entity.getCollection()) {
				if (property.xmlInfo.xmlType != XmlType.ATTRIBUTE)
					continue;

				count++;
			}
		}

		if (count > 0) {
			// @formatter:off
			methodBuilder.addCode("\n// attributes \n");
			methodBuilder.addStatement("String attributeName = null");
			// methodBuilder.addStatement("String attributeValue = null");

			methodBuilder.addStatement("int attributesCount = xmlParser.getAttributeCount();");
			methodBuilder.beginControlFlow("for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++)");
			methodBuilder.addStatement("attributeName = xmlParser.getAttributeName(attributeIndex)");
			// methodBuilder.addStatement("attributeValue =
			// $T.unescapeXml(xmlParser.getAttributeValue(attributeIndex))",
			// StringEscapeUtils.class);
			methodBuilder.beginControlFlow("switch(attributeName)$>");

			for (BindProperty property : entity.getCollection()) {
				if (property.xmlInfo.xmlType != XmlType.ATTRIBUTE)
					continue;

				methodBuilder.addCode("case $S:\n$>", property.label);

				bindTransform = BindTransformer.lookup(property);
				methodBuilder.addCode("// field $L (mapped by $S)\n", property.getName(), property.label);
				bindTransform.generateParseOnXml(context, methodBuilder, "xmlParser", property.getPropertyType().getTypeName(), "instance", property);

				methodBuilder.addStatement("$<break");
			}

			methodBuilder.addCode("default:\n$>");
			methodBuilder.addStatement("$<break$<");
			methodBuilder.endControlFlow();

			methodBuilder.endControlFlow();
			// @formatter:on
		} else {
			methodBuilder.addCode("// No attributes found\n");
		}
	}

	private static void generateParserOnXmlStartElement(BindTypeContext context, MethodSpec.Builder methodBuilder, String instanceName, String parserName, BindEntity entity) {
		BindTransform bindTransform;
		// start and inner bean
		methodBuilder.addStatement("currentTag = xmlParser.getName().toString()");

		int count = 0;
		// count property to manage
		{
			// for each elements
			for (BindProperty property : entity.getCollection()) {
				if (property.xmlInfo.xmlType != XmlType.TAG)
					continue;

				bindTransform = BindTransformer.lookup(property);

				// here we manage only property of bean type
				if (bindTransform != null) {
					count++;
				}
			}
		}

		if (count > 0) {
			// switch for tag elements
			// @formatter:off
			methodBuilder.beginControlFlow("switch(currentTag)$>");

			// for each elements
			for (BindProperty property : entity.getCollection()) {
				if (property.xmlInfo.xmlType != XmlType.TAG)
					continue;

				bindTransform = BindTransformer.lookup(property);

				// here we manage only property of bean type
				if (bindTransform != null) {
					methodBuilder.addCode("case $S:\n$>", property.label);
					methodBuilder.addCode("// property $L (mapped on $S)\n", property.getName(), property.label);

					// methodBuilder.beginControlFlow("if
					// (!xmlParser.isEmptyElement())");
					bindTransform.generateParseOnXml(context, methodBuilder, "xmlParser", property.getPropertyType().getTypeName(), "instance", property);
					// methodBuilder.endControlFlow();

					methodBuilder.addStatement("$<break");
				}
			}

			methodBuilder.addCode("default:\n$>");
			//methodBuilder.addStatement("$L.skipElement()", parserName);

			methodBuilder.addStatement("$<break");
			methodBuilder.endControlFlow();
		} else {
			methodBuilder.addCode("// No property to manage here\n");
		}
		// @formatter:on

	}

	/**
	 * Parse entity properties and write code to read only CData Text fields
	 * 
	 * @param methodBuilder
	 * @param entity
	 */
	private static void generateParserOnXmlCharacters(BindTypeContext context, MethodSpec.Builder methodBuilder, String instanceName, String parserName, BindEntity entity) {
		BindTransform bindTransform;
		int count = 0;
		for (BindProperty property : entity.getCollection()) {
			if (property.xmlInfo.xmlType != XmlType.VALUE && property.xmlInfo.xmlType != XmlType.VALUE_CDATA)
				continue;

			count++;
			methodBuilder.beginControlFlow("if (elementName!=null && $L.hasText())", parserName);
			methodBuilder.addCode("// property $L\n", property.getName());
			bindTransform = BindTransformer.lookup(property);
			bindTransform.generateParseOnXml(context, methodBuilder, parserName, property.getPropertyType().getTypeName(), "instance", property);
			methodBuilder.endControlFlow();
		}

		if (count == 0) {
			methodBuilder.addCode("// no property is binded to VALUE o CDATA ");
		}
	}

	private static void generateParseOnJackson(BindTypeContext context, BindEntity entity) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("parseOnJackson")
				.addJavadoc("parse with jackson\n")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				//.addParameter(typeName(AbstractJacksonContext.class), "context")
				.addParameter(typeName(JsonParser.class), "jacksonParser")
				.returns(typeName(entity.getElement()))
				.addException(Exception.class);
		// @formatter:on

		// methodBuilder.beginControlFlow("try");
		// methodBuilder.addStatement("$T jacksonParser =
		// wrapper.jacksonParser", JsonParser.class);
		methodBuilder.addStatement("$T instance = new $T()", entity.getElement(), entity.getElement());
		methodBuilder.addStatement("String fieldName");

		methodBuilder.beginControlFlow("if (jacksonParser.currentToken() == null)");
		methodBuilder.addStatement("jacksonParser.nextToken()");
		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("if (jacksonParser.currentToken() != $T.START_OBJECT)", JsonToken.class);
		methodBuilder.addStatement("jacksonParser.skipChildren()");
		methodBuilder.addStatement("return instance");
		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("while (jacksonParser.nextToken() != $T.END_OBJECT)", JsonToken.class);
		methodBuilder.addStatement("fieldName = jacksonParser.getCurrentName()");
		methodBuilder.addStatement("jacksonParser.nextToken()");

		if (entity.getCollection().size() > 0) {
			methodBuilder.addCode("\n// Parse fields:\n");
			methodBuilder.beginControlFlow("switch (fieldName)$>");

			BindTransform bindTransform;
			for (BindProperty item : entity.getCollection()) {
				bindTransform = BindTransformer.lookup(item);

				methodBuilder.addCode("case $S:\n$>", item.label);
				methodBuilder.addCode("// field $L (mapped with $S)\n", item.getName(), item.label);
				bindTransform.generateParseOnJackson(context, methodBuilder, "jacksonParser", item.getPropertyType().getTypeName(), "instance", item);
				methodBuilder.addCode("$<break;\n");
			}

			methodBuilder.addCode("default:$>\n");
			methodBuilder.addStatement("jacksonParser.skipChildren()");
			methodBuilder.addCode("$<break;");

			methodBuilder.addCode("$<");
			methodBuilder.endControlFlow();
		} else {
			methodBuilder.addCode("\n// No field to parse\n");
		}

		methodBuilder.endControlFlow();

		methodBuilder.addStatement("return instance");
		// methodBuilder.nextControlFlow("catch ($T e)", Exception.class);
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw new $T(e)",
		// KriptonRuntimeException.class);
		// methodBuilder.endControlFlow();
		context.builder.addMethod(methodBuilder.build());

	}

	private static void generateParseOnJacksonAsString(BindTypeContext context, BindEntity entity) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("parseOnJacksonAsString")
				.addJavadoc("parse with jackson\n")
				.addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
				//.addParameter(typeName(AbstractJacksonContext.class), "context")
				.addParameter(typeName(JsonParser.class), "jacksonParser")
				.returns(typeName(entity.getElement())).addException(Exception.class);
		// @formatter:on

		// methodBuilder.beginControlFlow("try");
		// methodBuilder.addStatement("$T jacksonParser =
		// wrapper.jacksonParser", JsonParser.class);
		methodBuilder.addStatement("$T instance = new $T()", entity.getElement(), entity.getElement());
		methodBuilder.addStatement("String fieldName");

		methodBuilder.beginControlFlow("if (jacksonParser.getCurrentToken() == null)");
		methodBuilder.addStatement("jacksonParser.nextToken()");
		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("if (jacksonParser.getCurrentToken() != $T.START_OBJECT)", JsonToken.class);
		methodBuilder.addStatement("jacksonParser.skipChildren()");
		methodBuilder.addStatement("return instance");
		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("while (jacksonParser.nextToken() != $T.END_OBJECT)", JsonToken.class);
		methodBuilder.addStatement("fieldName = jacksonParser.getCurrentName()");
		methodBuilder.addStatement("jacksonParser.nextToken()");

		if (entity.getCollection().size() > 0) {
			methodBuilder.addCode("\n// Parse fields:\n");
			methodBuilder.beginControlFlow("switch (fieldName)$>");

			BindTransform bindTransform;
			for (BindProperty item : entity.getCollection()) {
				bindTransform = BindTransformer.lookup(item);

				methodBuilder.addCode("case $S:\n$>", item.label);
				methodBuilder.addCode("// field $L (mapped with $S)\n", item.getName(), item.label);
				bindTransform.generateParseOnJacksonAsString(context, methodBuilder, "jacksonParser", item.getPropertyType().getTypeName(), "instance", item);
				methodBuilder.addCode("$<break;\n");
			}

			methodBuilder.addCode("default:$>\n");
			methodBuilder.addStatement("jacksonParser.skipChildren()");
			methodBuilder.addCode("$<break;");

			methodBuilder.addCode("$<");
			methodBuilder.endControlFlow();

		} else {
			methodBuilder.addCode("\n// No field to parse\n");
		}

		methodBuilder.endControlFlow();

		methodBuilder.addStatement("return instance");
		// methodBuilder.nextControlFlow("catch ($T e)", Exception.class);
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw new $T(e)",
		// KriptonRuntimeException.class);
		// methodBuilder.endControlFlow();
		context.builder.addMethod(methodBuilder.build());

	}

	private static void generateSerializeOnJackson(BindTypeContext context, BindEntity entity) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnJackson")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
					//.addParameter(typeName(AbstractJacksonContext.class), "context")
					.addParameter(typeName(entity.getElement()), "object")
					.addParameter(typeName(JsonGenerator.class), "jacksonSerializer")
				.returns(Integer.TYPE).addException(Exception.class);
		// @formatter:on

		// methodBuilder.beginControlFlow("try");
		// methodBuilder.addStatement("$T jacksonSerializer =
		// wrapper.jacksonGenerator", className(JsonGenerator.class));
		methodBuilder.addStatement("jacksonSerializer.writeStartObject()");
		methodBuilder.addStatement("int fieldCount=0");

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// fields
		methodBuilder.addCode("// Serialized Field:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L (mapped with $S)\n", item.getName(), item.label);
			bindTransform.generateSerializeOnJackson(context, methodBuilder, "jacksonSerializer", item.getPropertyType().getTypeName(), "object", item);
			methodBuilder.addCode("\n");
		}

		methodBuilder.addStatement("jacksonSerializer.writeEndObject()");
		methodBuilder.addStatement("return fieldCount");

		// methodBuilder.nextControlFlow("catch($T e)",
		// typeName(Exception.class));
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw (new $T(e))",
		// typeName(KriptonRuntimeException.class));
		// methodBuilder.endControlFlow();

		context.builder.addMethod(methodBuilder.build());
	}

	private static void generateSerializeOnJacksonAsString(BindTypeContext context, BindEntity entity) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnJacksonAsString")
				.addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
					//.addParameter(typeName(AbstractJacksonContext.class), "context")
					.addParameter(typeName(entity.getElement()), "object")
					.addParameter(typeName(JsonGenerator.class), "jacksonSerializer")
				.returns(Integer.TYPE).addException(Exception.class);
		// @formatter:on

		// methodBuilder.beginControlFlow("try");
		// methodBuilder.addStatement("$T jacksonSerializer =
		// wrapper.jacksonGenerator", className(JsonGenerator.class));
		methodBuilder.addStatement("jacksonSerializer.writeStartObject()");
		methodBuilder.addStatement("int fieldCount=0");

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// fields
		methodBuilder.addCode("// Serialized Field:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L (mapped with $S)\n", item.getName(), item.label);
			bindTransform.generateSerializeOnJacksonAsString(context, methodBuilder, "jacksonSerializer", item.getPropertyType().getTypeName(), "object", item);
			methodBuilder.addCode("\n");
		}

		methodBuilder.addStatement("jacksonSerializer.writeEndObject()");

		methodBuilder.addStatement("return fieldCount");
		// methodBuilder.nextControlFlow("catch($T e)",
		// typeName(Exception.class));
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw (new $T(e))",
		// typeName(KriptonRuntimeException.class));
		// methodBuilder.endControlFlow();

		context.builder.addMethod(methodBuilder.build());

	}

	private static void generateSerializeOnXml(BindTypeContext context, BindEntity entity) {
		// @formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("serializeOnXml").addJavadoc("method for xml serialization\n").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
					//.addParameter(typeName(KriptonXmlContext.class), "context")
					.addParameter(typeName(entity.getElement()), "object")
					.addParameter(typeName(XMLSerializer.class), "xmlSerializer")
					.addParameter(typeName(Integer.TYPE), "currentEventType")
				.returns(Void.TYPE).addException(Exception.class);
		// @formatter:on

		methodBuilder.beginControlFlow("if (currentEventType == 0)");
		methodBuilder.addStatement("xmlSerializer.writeStartElement(\"$L\")", entity.xmlInfo.label);
		methodBuilder.endControlFlow();

		BindTransform bindTransform;

		methodBuilder.addCode("\n");

		// attributes
		methodBuilder.addCode("// Persisted fields:\n\n");
		for (BindProperty item : entity.getCollection()) {
			bindTransform = BindTransformer.lookup(item);

			methodBuilder.addCode("// field $L (mapped with $S)\n", item.getName(), item.label);
			if (item.hasTypeAdapter()) {
				methodBuilder.addCode("// field trasformation $L $L \n", item.typeAdapter.dataType, item.typeAdapter.adapterClazz);
			}
			bindTransform.generateSerializeOnXml(context, methodBuilder, "xmlSerializer", item.getPropertyType().getTypeName(), "object", item);
			methodBuilder.addCode("\n");
		}

		methodBuilder.beginControlFlow("if (currentEventType == 0)");
		methodBuilder.addStatement("xmlSerializer.writeEndElement()");
		methodBuilder.endControlFlow();

		// methodBuilder.nextControlFlow("catch($T e)",
		// typeName(Exception.class));
		// methodBuilder.addStatement("e.printStackTrace()");
		// methodBuilder.addStatement("throw (new $T(e))",
		// typeName(KriptonRuntimeException.class));
		// methodBuilder.endControlFlow();

		context.builder.addMethod(methodBuilder.build());
	}

}
