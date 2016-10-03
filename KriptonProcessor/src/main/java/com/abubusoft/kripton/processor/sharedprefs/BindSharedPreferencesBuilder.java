/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefProperty;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 */
public class BindSharedPreferencesBuilder {

	protected static final String PREFIX = "Bind";

	protected static final String SUFFIX = "Preferences";

	protected static Builder builder;

	/**
	 * Generate shared preference manager
	 * 
	 * @return name of generated class
	 * 
	 * @throws IOException
	 */
	public static String generate(Elements elementUtils, Filer filer, PrefEntity entity) throws IOException {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		String beanClassName = entity.getSimpleName().toString();

		if (!beanClassName.endsWith(SUFFIX)) {
			String msg = String.format("Class %s must have a name with suffix \"%s\" to be used with @%s", beanClassName, SUFFIX, BindSharedPreferences.class.getSimpleName());
			throw (new InvalidNameException(msg));
		}

		String className = PREFIX + beanClassName; // + SUFFIX;
		ModelAnnotation annotation = entity.getAnnotation(BindSharedPreferences.class);
		String sharedPreferenceName = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_NAME);

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindSharedPreferences.class, packageName, className);
		//@formatter:off
		builder = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).superclass(AbstractSharedPreference.class);
		//@formatter:on

		if (StringUtil.hasText(sharedPreferenceName)) {
			builder.addField(FieldSpec.builder(String.class, "SHARED_PREFERENCE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$S", converter.convert(entity.getSimpleName().toString())).build());
		}

		builder.addField(FieldSpec.builder(className(beanClassName), "defaultBean", Modifier.PRIVATE, Modifier.FINAL).build());

		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("edit").returns(typeName("BindEditor")).addModifiers(Modifier.PUBLIC);
			methodBuilder.addStatement("return new $T()", typeName("BindEditor"));
			builder.addMethod(methodBuilder.build());
		}

		generateEditor(entity);

		generateConstructor(sharedPreferenceName, beanClassName);

		generateResetMethod(entity);

		generateReadMethod(entity);

		generateWriteMethod(entity);

		generateSingleReadMethod(entity);

		generateInstance(className);

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

		return className;
	}

	private static void generateEditor(PrefEntity entity) {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		Builder innerClassBuilder = TypeSpec.classBuilder("BindEditor").addModifiers(Modifier.PUBLIC).superclass(typeName("AbstractEditor"));
		innerClassBuilder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build());

		// write method
		for (PrefProperty item : entity.getCollection()) {
			MethodSpec.Builder method = MethodSpec.methodBuilder("put" + converter.convert(item.getName())).addModifiers(Modifier.PUBLIC).addParameter(typeName(item.getElement()), "value").returns(typeName("BindEditor"));
			// method.addCode("// set $L property ($L)\n", item.getName(), item.getPreferenceType());

			switch (item.getPreferenceType()) {
			case STRING:
				if (item.getPropertyType().isArray()) {
					method.addStatement("editor.putString($S, array2String(value))", item.getName());
				} else if (item.getPropertyType().isList()) {
					method.addStatement("editor.putString($S, list2String(value))", item.getName());
				} else if (item.getPropertyType().isSameType(String.class)) {
					method.addStatement("editor.putString($S, value)", item.getName());
				} else {
					method.addStatement("editor.putString($S, writeObj(value))", item.getName());
				}
				break;
			case BOOL:
				method.addStatement("editor.putBoolean($S, value)", item.getName());
				break;
			case FLOAT:
				method.addStatement("editor.putFloat($S, value)", item.getName());
				break;
			case INT:
				method.addStatement("editor.putInt($S, value)", item.getName());
				break;
			case LONG:
				method.addStatement("editor.putLong($S, value)", item.getName());
				break;
			default:
				break;
			}

			method.addStatement("return this");
			innerClassBuilder.addMethod(method.build());
		}

		builder.addType(innerClassBuilder.build());
	}

	private static void generateInstance(String className) {
		builder.addField(className(className), "instance", Modifier.PRIVATE, Modifier.STATIC);
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("instance").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(className));

		methodBuilder.addJavadoc("\n$L\n", "instance");
		methodBuilder.beginControlFlow("if (instance==null)");
		methodBuilder.addCode("instance=new $L();\n", className(className));
		methodBuilder.endControlFlow();
		methodBuilder.addCode("return instance;\n");

		builder.addMethod(methodBuilder.build());
	}

	/**
	 * @param sharedPreferenceName
	 * @param beanClassName
	 */
	private static void generateConstructor(String sharedPreferenceName, String beanClassName) {
		{
			MethodSpec.Builder method = MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE);
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
	}

	private static void generateResetMethod(PrefEntity entity) {
		{
			// write method
			MethodSpec.Builder method = MethodSpec.methodBuilder("reset").addModifiers(Modifier.PUBLIC).returns(Void.TYPE);
			method.addStatement("$T bean=new $T()", entity.getElement(), entity.getElement());
			method.addStatement("write(bean)");
			builder.addMethod(method.build());
		}
	}

	/**
	 * @param entity
	 */
	private static void generateWriteMethod(PrefEntity entity) {
		{
			// write method
			MethodSpec.Builder method = MethodSpec.methodBuilder("write").addModifiers(Modifier.PUBLIC).addParameter(typeName(entity.getName()), "bean").returns(Void.TYPE);
			method.addStatement("$T editor=prefs.edit()", SharedPreferences.Editor.class);
			for (PrefProperty item : entity.getCollection()) {
				// method.addCode("// set $L property ($L)\n", item.getName(), item.getPreferenceType());
				switch (item.getPreferenceType()) {
				case STRING:
					if (item.getPropertyType().isArray()) {
						method.addStatement("editor.putString($S, array2String(bean.$L))", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					} else if (item.getPropertyType().isList()) {
						method.addStatement("editor.putString($S, list2String(bean.$L))", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					} else if (item.getPropertyType().isSameType(String.class)) {
						method.addStatement("editor.putString($S, bean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					} else {
						method.addStatement("editor.putString($S, writeObj(bean.$L))", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					}
					break;
				case BOOL:
					method.addStatement("editor.putBoolean($S, bean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case FLOAT:
					method.addStatement("editor.putFloat($S, bean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case INT:
					method.addStatement("editor.putInt($S, bean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case LONG:
					method.addStatement("editor.putLong($S, bean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				default:
					break;
				}

			}
			method.addCode("\n");
			method.addStatement("editor.commit()");
			builder.addMethod(method.build());
		}
	}

	private static void generateReadMethod(PrefEntity entity) {
		// read method
		MethodSpec.Builder method = MethodSpec.methodBuilder("read").addModifiers(Modifier.PUBLIC).returns(typeName(entity.getName()));
		method.addStatement("$T bean=new $T()", typeName(entity.getName()), typeName(entity.getName()));
		for (PrefProperty item : entity.getCollection()) {
			// method.addCode("// get $L property ($L)\n", item.getName(), item.getPreferenceType());

			switch (item.getPreferenceType()) {
			case STRING:
				if (item.getPropertyType().isArray()) {
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "string2array(prefs.getString($S, null), bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else if (item.getPropertyType().isList()) {
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "string2list(prefs.getString($S, null), bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else if (item.getPropertyType().isEnum()) {

				} else if (item.getPropertyType().isSameType(String.class)) {
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getString($S, bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else {
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "($T)readObj(prefs.getString($S, null), $T.class)"), typeName(item.getPropertyType().getRawType()), item.getName(), typeName(item
							.getPropertyType().getRawType()));
					method.beginControlFlow("if (bean." + PropertyUtility.getter(typeName(entity.getElement()), item) + "==null) ");
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "defaultBean.$L"), PropertyUtility.getter(typeName(entity.getElement()), item));
					method.endControlFlow();
				}

				break;
			case BOOL:
				method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getBoolean($S,bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case FLOAT:
				method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getFloat($S,bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case INT:
				method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getInt($S,bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case LONG:
				method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getLong($S,bean.$L)"), item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			default:
				break;
			}

		}

		method.addCode("\n");
		method.addStatement("return bean");
		builder.addMethod(method.build());
	}

	private static void generateSingleReadMethod(PrefEntity entity) {
		// read method

		for (PrefProperty item : entity.getCollection()) {
			MethodSpec.Builder method = MethodSpec.methodBuilder(item.getName()).addModifiers(Modifier.PUBLIC).returns(item.getPropertyType().getName());
			// method.addCode("// get $L property ($L)\n", item.getName(), item.getPreferenceType());

			switch (item.getPreferenceType()) {
			case STRING:
				if (item.getPropertyType().isArray()) {
					method.addStatement("return string2array(prefs.getString($S, null), defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else if (item.getPropertyType().isList()) {
					method.addStatement("return string2list(prefs.getString($S, null), defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else if (item.getPropertyType().isEnum()) {

				} else if (item.getPropertyType().isSameType(String.class)) {
					method.addStatement("return prefs.getString($S, defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				} else {
					TypeName type = typeName(item.getPropertyType().getRawType());
					method.addStatement("$T temp=($T)readObj(prefs.getString($S, null), $T.class)", type, type, item.getName(), type);
					method.beginControlFlow("if (temp!=null) ");
					method.addStatement("return temp");
					method.endControlFlow();
					method.addStatement("return defaultBean.$L", PropertyUtility.getter(typeName(entity.getElement()), item));
				}
				break;
			case BOOL:
				method.addStatement("return prefs.getBoolean($S,defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case FLOAT:
				method.addStatement("return prefs.getFloat($S,defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case INT:
				method.addStatement("return prefs.getInt($S,defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			case LONG:
				method.addStatement("return prefs.getLong($S,defaultBean.$L)", item.getName(), PropertyUtility.getter(typeName(entity.getElement()), item));
				break;
			default:
				break;
			}

			builder.addMethod(method.build());
		}

	}
}
