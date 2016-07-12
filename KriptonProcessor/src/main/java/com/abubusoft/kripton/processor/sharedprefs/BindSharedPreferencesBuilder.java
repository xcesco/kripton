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

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.android.sharedprefs.Converter;
import com.abubusoft.kripton.android.sharedprefs.DefaultConverter;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefEntity;
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

	//protected static final String SUFFIX = "Preferences";
	
	protected static Builder builder;
	
	/**
	 * Generate shared preference manager 
	 * @return
	 * 		name of generated class 
	 * 
	 * @throws IOException
	 */
	public static String generate(Elements elementUtils, Filer filer, PrefEntity entity) throws IOException {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		String className=PREFIX + entity.getSimpleName().toString();// + SUFFIX;
		
		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		//@formatter:off
		builder = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).superclass(AbstractSharedPreference.class);
		//@formatter:on
		
		builder.addField(FieldSpec.builder(String.class, "SHARED_PREFERENCE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$S",converter.convert(entity.getSimpleName().toString())).build());		
		
		MethodSpec.Builder method=MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
		method.addStatement("prefs=$T.context().getSharedPreferences(SHARED_PREFERENCE_NAME, $T.MODE_PRIVATE)", KriptonLibrary.class, Context.class);
		method.addStatement("converterMap=new $T<$T, $T>()",HashMap.class, String.class, Converter.class);
		builder.addMethod(method.build());
		
		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
		
		return className;
	}
}
