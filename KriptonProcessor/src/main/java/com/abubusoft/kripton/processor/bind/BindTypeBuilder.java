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

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefProperty;
import com.abubusoft.kripton.processor.sharedprefs.transform.SPTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.SPTransformer;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 */
public class BindTypeBuilder {

	protected static final String PREFIX = "";

	protected static final String SUFFIX = KriptonBinder2.MAPPER_CLASS_SUFFIX;

	protected static Builder builder;

	/**
	 * Generate shared preference manager
	 * 
	 * @return name of generated class
	 * 
	 * @throws IOException
	 */
	public static String generate(Elements elementUtils, Filer filer, BindEntity item) throws IOException {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		String beanClassName = item.getSimpleName().toString();

		boolean needSuffix=true;
		if (beanClassName.endsWith(SUFFIX)) {
			needSuffix=false;
			//String msg = String.format("Class %s must have a name with suffix \"%s\" to be used with @%s", beanClassName, SUFFIX, BindSharedPreferences.class.getSimpleName());
			//throw (new InvalidNameException(msg));
		}

		String className = PREFIX + beanClassName + (needSuffix ? SUFFIX : "" );
		ModelAnnotation annotation = item.getAnnotation(BindType.class);
		String bindTypeName = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_NAME);

		PackageElement pkg = elementUtils.getPackageOf(item.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindType.class, packageName, className);
		//@formatter:off
		builder = TypeSpec.classBuilder(className)
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT)
				.superclass(TypeUtility.parameterizedTypeName(className(AbstractMapper.class), typeName(item.getElement().asType())));
		//@formatter:on
		builder.addJavadoc("This class is the shared preference binder defined for $T\n\n", item.getElement());
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", item.getElement());

		/*
		
		if (StringUtil.hasText(bindTypeName)) {
			builder.addField(FieldSpec.builder(String.class, "SHARED_PREFERENCE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.initializer("$S", converter.convert(item.getSimpleName().toString()))
					.addJavadoc("shared preferences name for $T\n", item.getElement())
					.build());
		}

		builder.addField(FieldSpec.builder(className(beanClassName), "defaultBean", Modifier.PRIVATE, Modifier.FINAL)
				.addJavadoc("working instance of bean\n")
				.build());

		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("edit")
					.addJavadoc("create an editor to modify shared preferences\n")
					.returns(typeName("BindEditor"))
					.addModifiers(Modifier.PUBLIC);
			methodBuilder.addStatement("return new $T()", typeName("BindEditor"));
			builder.addMethod(methodBuilder.build());
		}

		generateEditor(item);

		generateConstructor(bindTypeName, beanClassName);

		generateResetMethod(item);

		generateReadMethod(item);

		generateWriteMethod(item);

		generateSingleReadMethod(item);

		generateInstance(className);
		*/

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

		return className;
	}

	/**
	 * create editor
	 * 
	 * @param entity
	 */
	private static void generateEditor(PrefEntity entity) {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		Builder innerClassBuilder = TypeSpec.classBuilder("BindEditor")
				.addModifiers(Modifier.PUBLIC)
				.addJavadoc("editor class for shared preferences\n")
				.superclass(typeName("AbstractEditor"));
		innerClassBuilder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build());

		SPTransform transform;
		// write method
		for (PrefProperty item : entity.getCollection()) {
			MethodSpec.Builder builder = MethodSpec.methodBuilder("put" + converter.convert(item.getName()))
					.addModifiers(Modifier.PUBLIC)
					.addParameter(typeName(item.getElement()), "value")
					.addJavadoc("modifier for property $L\n", item.getName())
					.returns(typeName("BindEditor"));

			transform = SPTransformer.lookup(item);
			transform.generateWriteProperty(builder, "editor", null, "value", item); //generateReadProperty(method, "prefs", typeName(item.getElement().asType()), "bean", item, true);
			builder.addCode(";\n");
			
			/*
			switch (item.getPreferenceType()) {
			case STRING:
				if (item.getPropertyType().isArray()) {
					builder.addStatement("editor.putString($S, array2String(value))", item.getName());
				} else if (item.getPropertyType().isList()) {
					builder.addStatement("editor.putString($S, list2String(value))", item.getName());
				} else if (item.getPropertyType().isSameType(String.class)) {
					builder.addStatement("editor.putString($S, value)", item.getName());
				} else {
					builder.addStatement("editor.putString($S, writeObj(value))", item.getName());
				}
				break;
			case BOOL:
				builder.addStatement("editor.putBoolean($S, value)", item.getName());
				break;
			case FLOAT:
				builder.addStatement("editor.putFloat($S, value)", item.getName());
				break;
			case INT:
				builder.addStatement("editor.putInt($S, value)", item.getName());
				break;
			case LONG:
				builder.addStatement("editor.putLong($S, value)", item.getName());
				break;
			default:
				break;
			}*/

			builder.addStatement("return this");
			innerClassBuilder.addMethod(builder.build());
		}

		builder.addType(innerClassBuilder.build());
	}

	/**
	 * Generate instance of shared preferences
	 * 
	 * @param className
	 */
	private static void generateInstance(String className) {
		builder.addField(FieldSpec.builder(className(className), "instance", Modifier.PRIVATE, Modifier.STATIC)
				.addJavadoc("instance of shared preferences\n")
				.build());
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("instance")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addJavadoc("get instance of shared preferences\n")
				.returns(className(className));

		methodBuilder.beginControlFlow("if (instance==null)");
		methodBuilder.addCode("instance=new $L();\n", className(className));
		methodBuilder.endControlFlow();
		methodBuilder.addCode("return instance;\n");

		builder.addMethod(methodBuilder.build());
	}

	/*
	private static void generateConstructor(String sharedPreferenceName, String beanClassName) {
		{
			MethodSpec.Builder method = MethodSpec.constructorBuilder()
					.addModifiers(Modifier.PRIVATE)
					.addJavadoc("constructor\n");
			if (StringUtil.hasText(sharedPreferenceName)) {
				method.addCode("// using name attribute of annotation @BindSharedPreferences as name\n");
				method.addStatement("prefs=$T.context().getSharedPreferences(SHARED_PREFERENCE_NAME, $T.MODE_PRIVATE)", KriptonLibrary.class, Context.class);
			} else {
				method.addCode("// no name specified, using default shared preferences\n");
				method.addStatement("prefs=$T.getDefaultSharedPreferences($T.context())", PreferenceManager.class, KriptonLibrary.class);
			}

			// method.addStatement("converterMap=new $T<$T, $T>()", HashMap.class, String.class, Converter.class);
			method.addStatement("defaultBean=new $T()", className(beanClassName));
			builder.addMethod(method.build());
		}
	}*/

	private static void generateResetMethod(PrefEntity entity) {
		{
			// write method
			MethodSpec.Builder method = MethodSpec.methodBuilder("reset")
					.addModifiers(Modifier.PUBLIC)
					.addJavadoc("reset shared preferences\n")
					.returns(Void.TYPE);
			method.addStatement("$T bean=new $T()", entity.getElement(), entity.getElement());
			method.addStatement("write(bean)");
			builder.addMethod(method.build());
		}
	}

	/*
	private static void generateWriteMethod(PrefEntity entity) {
		{
			// write method
			MethodSpec.Builder method = MethodSpec.methodBuilder("write")
					.addJavadoc("write bean entirely\n\n")
					.addJavadoc("@param bean bean to entirely write\n")
					.addModifiers(Modifier.PUBLIC)
					.addParameter(typeName(entity.getName()), "bean")
					.returns(Void.TYPE);
			method.addStatement("$T editor=prefs.edit()", SharedPreferences.Editor.class);
			
			SPTransform transform;
			for (PrefProperty item : entity.getCollection()) {
				
				transform = SPTransformer.lookup(item);
				transform.generateWriteProperty(method, "editor", typeName(entity.getElement()), "bean", item); //generateReadProperty(method, "prefs", typeName(item.getElement().asType()), "bean", item, true);
				method.addCode(";\n");
				
				// method.addCode("// set $L property ($L)\n", item.getName(), item.getPreferenceType());				

			}
			method.addCode("\n");
			method.addStatement("editor.commit()");
			builder.addMethod(method.build());
		}
	}

	private static void generateReadMethod(PrefEntity entity) {
		// read method
		MethodSpec.Builder method = MethodSpec.methodBuilder("read")
				.addModifiers(Modifier.PUBLIC)
				.addJavadoc("read bean entirely\n\n")
				.addJavadoc("@return read bean\n")
				.returns(typeName(entity.getName()));
		method.addStatement("$T bean=new $T()", typeName(entity.getName()), typeName(entity.getName()));
		
		SPTransform transform;
		
		for (PrefProperty item : entity.getCollection()) {
			// method.addCode("// get $L property ($L)\n", item.getName(), item.getPreferenceType());
			transform=SPTransformer.lookup(item);
			transform.generateReadProperty(method, "prefs", typeName(item.getElement().asType()), "bean", item, true);
			method.addCode(";\n");			
		}

		method.addCode("\n");
		method.addStatement("return bean");
		builder.addMethod(method.build());
	}

	private static void generateSingleReadMethod(PrefEntity entity) {
		// read method
		
		SPTransform transform;

		for (PrefProperty item : entity.getCollection()) {
			MethodSpec.Builder method = MethodSpec.methodBuilder(item.getName())
					.addModifiers(Modifier.PUBLIC)
					.addJavadoc("read property $L\n\n", item.getName())
					.addJavadoc("@return property $L value\n", item.getName())
					.returns(item.getPropertyType().getName());

			method.addCode("return ");
			transform = SPTransformer.lookup(item);
			transform.generateReadProperty(method, "prefs", typeName(item.getElement().asType()), "defaultBean", item, false);
			method.addCode(";\n");			

			builder.addMethod(method.build());
		}

	}*/
}
