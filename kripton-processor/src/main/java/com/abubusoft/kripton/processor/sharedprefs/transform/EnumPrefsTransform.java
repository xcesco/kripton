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

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java5 Enum object.
 *
 * @author xcesco
 */
public class EnumPrefsTransform extends AbstractPrefsTransform {

	/** The type name. */
	private TypeName typeName;

	/**
	 * Instantiates a new enum prefs transform.
	 *
	 * @param typeName the type name
	 */
	public EnumPrefsTransform(TypeName typeName) {
		super(false);
		this.typeName = typeName;
		defaultValue = "null";
	}

	/** The default value. */
	protected String defaultValue;

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#generateReadProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty, boolean)
	 */
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, PrefsProperty property, boolean readAll, ReadType readType) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}

		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getPreferenceKey());

		if (readAll) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
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

		methodBuilder.addCode("($T.hasText(temp)) ? ", StringUtils.class);
		methodBuilder.addCode("$T.valueOf(temp)", typeName);
		methodBuilder.addCode(": $L", getter(beanName, beanClass, property));

		if (readAll) {
			methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
		}

		methodBuilder.addCode(";\n");

		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#generateWriteProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty)
	 */
	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, PrefsProperty property) {
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		methodBuilder.addStatement("$L.putString($S,$L.toString() )", editorName, property.getPreferenceKey(), getter(beanName, beanClass, property));		
		methodBuilder.nextControlFlow("else");
		methodBuilder.addStatement("$L.remove($S)", editorName, property.getName());
		methodBuilder.endControlFlow();

	}
}