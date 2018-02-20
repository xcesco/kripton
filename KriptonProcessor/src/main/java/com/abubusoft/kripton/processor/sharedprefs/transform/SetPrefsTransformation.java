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
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class SetPrefsTransformation extends AbstractGeneratedPrefsTransform {

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, PrefProperty property, boolean readAll) {
		boolean isStringSet = isStringSet(property);

		if (readAll) {
			methodBuilder.beginControlFlow("");
		}

		if (isStringSet) {
			methodBuilder.addStatement("$T<String> temp=$L.getStringSet($S, null)", Set.class, preferenceName, property.getPreferenceKey());
			if (readAll) {
				methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
			} else {
				methodBuilder.addCode("return ");
			}

			ParameterizedTypeName typeName = (ParameterizedTypeName) TypeUtility.typeName(property.getElement().asType());
			if (TypeUtility.isEquals(typeName.rawType, Set.class.getCanonicalName())) {
				methodBuilder.addCode("temp");
			} else {
				methodBuilder.addCode("new $T<String>(temp)", typeName.rawType);
			}

			if (readAll) {
				methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
			}

			methodBuilder.addCode(";\n");
		} else {
			methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getPreferenceKey());
			if (readAll) {
				methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
			} else {
				methodBuilder.addCode("return ");
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

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, PrefProperty property) {
		Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		boolean isStringSet = isStringSet(property);

		if (!isStringSet) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addStatement("String temp=serialize$L($L)", formatter.convert(property.getName()), getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.putString($S,temp)", editorName, property.getPreferenceKey());
			methodBuilder.nextControlFlow(" else ");
			methodBuilder.addStatement("$L.remove($S)", editorName, property.getPreferenceKey());
			methodBuilder.endControlFlow();
		} else {
			methodBuilder.addStatement("$L.putStringSet($S,$L)", editorName, property.getPreferenceKey(), getter(beanName, beanClass, property));
		}

	}

	/**
	 * @param property
	 * @return
	 */
	public static boolean isStringSet(PrefProperty property) {
		boolean isStringSet = false;
		// it is parameterized type name for sure
		TypeName typeName = TypeUtility.typeName(property.getElement().asType());
		if (typeName instanceof ParameterizedTypeName && TypeUtility.isSet(typeName)) {
			ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
			if (TypeUtility.isEquals(parameterizedTypeName.typeArguments.get(0), String.class.getCanonicalName())) {
				isStringSet = true;
			}
		}

		return isStringSet;
	}
}
