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

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * The Class AbstractGeneratedPrefsTransform.
 */
public abstract class AbstractGeneratedPrefsTransform extends AbstractPrefsTransform {

	/**
	 * Instantiates a new abstract generated prefs transform.
	 */
	public AbstractGeneratedPrefsTransform() {
		super(false);
	}

	/**
	 * Instantiates a new abstract generated prefs transform.
	 *
	 * @param targetTypeSupported
	 *            the target type supported
	 */
	public AbstractGeneratedPrefsTransform(boolean targetTypeSupported) {
		super(targetTypeSupported);
	}

	/** The formatter. */
	protected static Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#
	 * generateReadProperty(com.squareup.javapoet.MethodSpec.Builder,
	 * java.lang.String, com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty, boolean)
	 */
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName,
			PrefsProperty property, boolean readAll, ReadType readType) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}
		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getPreferenceKey());

		if (readAll) {			
			methodBuilder.addCode(setter(beanClass, beanName, property) + (!property.isPublicField() && beanName!=null ? "(" : "="));
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
		methodBuilder.addCode(": $L", getter(DEFAULT_BEAN_NAME, beanClass, property));

		if (readAll) {
			methodBuilder.addCode((!property.isPublicField() && beanName!=null ? ")" : ""));
		}

		methodBuilder.addCode(";\n");

		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#
	 * generateWriteProperty(com.squareup.javapoet.MethodSpec.Builder,
	 * java.lang.String, com.squareup.javapoet.TypeName, java.lang.String,
	 * com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty)
	 */
	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName,
			PrefsProperty property) {
		Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		methodBuilder.addStatement("String temp=serialize$L($L)", formatter.convert(property.getName()),
				getter(beanName, beanClass, property));
		methodBuilder.addStatement("$L.putString($S,temp)", editorName, property.getPreferenceKey());
		methodBuilder.nextControlFlow(" else ");
		methodBuilder.addStatement("$L.remove($S)", editorName, property.getPreferenceKey());
		methodBuilder.endControlFlow();

	}
}
