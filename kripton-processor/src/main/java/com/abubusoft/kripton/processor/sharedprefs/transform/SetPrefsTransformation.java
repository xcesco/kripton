/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.Set;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class SetPrefsTransformation.
 */
public class SetPrefsTransformation extends AbstractGeneratedPrefsTransform {

	/**
	 * Instantiates a new sets the prefs transformation.
	 */
	public SetPrefsTransformation() {
		super(true);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.AbstractGeneratedPrefsTransform#generateReadProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty, boolean)
	 */
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName,
			PrefsProperty property, boolean readAll, ReadType readType) {
		boolean isStringSet = isStringSet(property);

		String tempPre = "";
		String tempPost = "";
		
		String tempPreDefaultValue = "";
		String tempPostDefaultValue = "";

		if (readAll) {
			methodBuilder.beginControlFlow("");
		}

		if (property.hasTypeAdapter()) {
			// this comment is needed to include $T for PrefsTypeAdapterUtils
			methodBuilder.addComment("Use $T to convert objects", PrefsTypeAdapterUtils.class);
			tempPre = String.format("%s.getAdapter(%s.class).toJava(", PrefsTypeAdapterUtils.class.getSimpleName(),
					TypeUtility.className(property.typeAdapter.adapterClazz).simpleName());
			tempPost = ")";
			
			tempPreDefaultValue = String.format("%s.getAdapter(%s.class).toData(", PrefsTypeAdapterUtils.class.getSimpleName(),
					TypeUtility.className(property.typeAdapter.adapterClazz).simpleName());
			tempPostDefaultValue = ")";
		}

		if (isStringSet) {
			methodBuilder.addStatement("$T<String> temp=$L.getStringSet($S, "+tempPreDefaultValue+"defaultBean."+getter(beanClass, property)+tempPostDefaultValue+")", Set.class, preferenceName,
					property.getPreferenceKey());
			if (readAll) {
				methodBuilder.addCode(
						"$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
			} 
			
			switch (readType) {
			case NONE:
				break;
			case RETURN:
				methodBuilder.addCode("return ");
				break;
			case VALUE:
				methodBuilder.addCode("$T _value=", property.getPropertyType().getTypeName());
				break;
			}

			methodBuilder.addCode(tempPre);

			ParameterizedTypeName typeName;
			if (property.hasTypeAdapter()) {
				typeName = (ParameterizedTypeName) TypeUtility.typeName(property.typeAdapter.dataType);
			} else {
				typeName = (ParameterizedTypeName) TypeUtility.typeName(property.getElement().asType());
			}
			if (TypeUtility.isEquals(typeName.rawType, Set.class.getCanonicalName())) {
				methodBuilder.addCode("temp");
			} else {
				methodBuilder.addCode("new $T<String>(temp)", typeName.rawType);
			}

			methodBuilder.addCode(tempPost);

			if (readAll) {
				methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
			}

			methodBuilder.addCode(";\n");
		} else {
			methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName,
					property.getPreferenceKey());
			if (readAll) {
				methodBuilder.addCode(
						"$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
			} 
			
			switch (readType) {
			case NONE:
				break;
			case RETURN:
				methodBuilder.addCode("return ");
				break;
			case VALUE:
				methodBuilder.addCode("$T _value=", property.getPropertyType().getTypeName());
				break;
			}

			methodBuilder.addCode("$T.hasText(temp) ? ", StringUtils.class);
			methodBuilder.addCode("parse$L(temp)", formatter.convert(property.getName()));
			methodBuilder.addCode(": null");

			if (readAll) {
				methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
			}

			methodBuilder.addCode(";\n");
		}

		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.AbstractGeneratedPrefsTransform#generateWriteProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty)
	 */
	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName,
			PrefsProperty property) {
		Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		String tempPre = "";
		String tempPost = "";

		if (property.hasTypeAdapter()) {
			// this comment is needed to include $T for PrefsTypeAdapterUtils
			methodBuilder.addComment("Use $T to convert objects", PrefsTypeAdapterUtils.class);
			tempPre = String.format("%s.getAdapter(%s.class).toData(", PrefsTypeAdapterUtils.class.getSimpleName(),
					TypeUtility.className(property.typeAdapter.adapterClazz).simpleName());
			tempPost = ")";
		}

		boolean isStringSet = isStringSet(property);

		if (!isStringSet) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addStatement("String temp=serialize$L($L)", formatter.convert(property.getName()),
					getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.putString($S,temp)", editorName, property.getPreferenceKey());
			methodBuilder.nextControlFlow(" else ");
			methodBuilder.addStatement("$L.remove($S)", editorName, property.getPreferenceKey());
			methodBuilder.endControlFlow();
		} else {
			methodBuilder.addStatement("$L.putStringSet($S," + tempPre + "$L" + tempPost + ")", editorName,
					property.getPreferenceKey(), getter(beanName, beanClass, property));
		}

	}

	/**
	 * Checks if is string set.
	 *
	 * @param property the property
	 * @return true, if is string set
	 */
	public static boolean isStringSet(PrefsProperty property) {
		boolean isStringSet = false;

		TypeName typeName;
		if (property.hasTypeAdapter()) {
			typeName = TypeUtility.typeName(property.typeAdapter.dataType);
		} else {
			typeName = TypeUtility.typeName(property.getElement().asType());
		}

		// it is parameterized type name for sure

		if (typeName instanceof ParameterizedTypeName && TypeUtility.isSet(typeName)) {
			ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
			if (TypeUtility.isEquals(parameterizedTypeName.typeArguments.get(0), String.class.getCanonicalName())) {
				isStringSet = true;
			}
		}

		return isStringSet;
	}
}
