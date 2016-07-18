/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.android.sharedprefs.Converter;
import com.abubusoft.kripton.android.sharedprefs.DefaultConverter;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefProperty;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.TypeSpec.Builder;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

/**
 * @author xcesco
 *
 */
public class BindSharedPreferencesBuilder {

	protected static final String PREFIX = "Bind";

	// protected static final String SUFFIX = "Preferences";

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
		String className = PREFIX + entity.getSimpleName().toString();// + SUFFIX;

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		//@formatter:off
		builder = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).superclass(AbstractSharedPreference.class);
		//@formatter:on

		builder.addField(FieldSpec.builder(String.class, "SHARED_PREFERENCE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$S", converter.convert(entity.getSimpleName().toString())).build());
		ModelAnnotation annotation = entity.getAnnotation(BindSharedPreferences.class);
		String sharedPreferenceName = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_NAME);

		generateConstructor(sharedPreferenceName);

		generateReadMethod(entity);
		
		generateWriteMethod(entity);

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

		return className;
	}

	/**
	 * @param sharedPreferenceName
	 */
	public static void generateConstructor(String sharedPreferenceName) {
		{
			MethodSpec.Builder method = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
			if (StringUtil.hasText(sharedPreferenceName)) {
				method.addCode("// using name attribute of annotation @BindSharedPreferences as name\n");
				method.addStatement("prefs=$T.context().getSharedPreferences(SHARED_PREFERENCE_NAME, $T.MODE_PRIVATE)", KriptonLibrary.class, Context.class);
			} else {
				method.addCode("// no name specified, using default shared preferences\n");
				method.addStatement("prefs=$T.getDefaultSharedPreferences($T.context())", PreferenceManager.class, KriptonLibrary.class);
			}

			method.addStatement("converterMap=new $T<$T, $T>()", HashMap.class, String.class, Converter.class);
			builder.addMethod(method.build());
		}
	}

	/**
	 * @param entity
	 */
	public static void generateWriteMethod(PrefEntity entity) {
		{
			// write method
			MethodSpec.Builder method = MethodSpec.methodBuilder("write").addModifiers(Modifier.PUBLIC).addParameter(typeName(entity.getName()), "bean").returns(Void.TYPE);
			method.addStatement("$T editor=prefs.edit()", SharedPreferences.Editor.class);
			for (PrefProperty item : entity.getCollection()) {
				method.addCode("// set $L property ($L)\n", item.getName(), item.getPreferenceType());

				switch (item.getPreferenceType()) {
				case STRING:
					method.addStatement("editor.putString($S, bean.$L)", item.getName(),PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case FLOAT:
					method.addStatement("editor.putFloat($S, bean.$L)", item.getName(),PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case INT:
					method.addStatement("editor.putInt($S, bean.$L)", item.getName(),PropertyUtility.getter(typeName(entity.getElement()), item));
					break;
				case LONG:
					method.addStatement("editor.putLong($S, bean.$L)", item.getName(),PropertyUtility.getter(typeName(entity.getElement()), item));
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

	/**
	 * @param entity
	 */
	public static void generateReadMethod(PrefEntity entity) {
		{
			// read method
			MethodSpec.Builder method = MethodSpec.methodBuilder("read").addModifiers(Modifier.PUBLIC).returns(typeName(entity.getName()));
			method.addStatement("$T bean=new $T()", typeName(entity.getName()), typeName(entity.getName()));
			for (PrefProperty item : entity.getCollection()) {
				method.addCode("// get $L property ($L)\n", item.getName(), item.getPreferenceType());

				switch (item.getPreferenceType()) {
				case STRING:					
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getString($S,\"\")"), item.getName());
					break;
				case FLOAT:
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getFloat($S,0.0f)"), item.getName());
					break;
				case INT:
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getInt($S,0)"), item.getName());
					break;
				case LONG:
					method.addStatement("bean." + PropertyUtility.setter(typeName(entity.getElement()), item, "prefs.getLong($S,0)"), item.getName());
					break;
				default:
					break;
				}
				
			}
			
			method.addStatement("return bean");			
			builder.addMethod(method.build());
		}
	}
}
