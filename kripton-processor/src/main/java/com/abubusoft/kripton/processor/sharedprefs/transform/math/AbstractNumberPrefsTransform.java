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
package com.abubusoft.kripton.processor.sharedprefs.transform.math;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.math.BigInteger;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.abubusoft.kripton.processor.sharedprefs.transform.AbstractPrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.ReadType;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a java.math.BigInteger object
 * 
 * @author xcesco
 *
 */
abstract class AbstractNumberPrefsTransform extends AbstractPrefsTransform {
	
	/** The method conversion. */
	protected String METHOD_CONVERSION;
	
	/** The clazz. */
	protected Class<?> clazz;
	
	/**
	 * Instantiates a new abstract number prefs transform.
	 */
	public AbstractNumberPrefsTransform()
	{
		super(false);
		defaultValue="0";
		clazz=BigInteger.class;
		METHOD_CONVERSION="toString";
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
		
		methodBuilder.addStatement("String temp=$L.getString($S, $S)", preferenceName, property.getPreferenceKey(), defaultValue);
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
		
		methodBuilder.addCode("($T.hasText(temp)) ? ", StringUtils.class);
		methodBuilder.addCode("new $T(temp)",  clazz);
		methodBuilder.addCode(": $L", getter(DEFAULT_BEAN_NAME, beanClass, property));
		
		if (readAll) {
			methodBuilder.addCode(!property.isPublicField() && beanName!=null ? ")" : "");
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
		if (beanClass!=null)
		{
			methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));
				methodBuilder.addCode("$L.putString($S,$L.$L() );", editorName, property.getPreferenceKey(), getter(beanName, beanClass, property), METHOD_CONVERSION);			
			methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.putString($S, null);", editorName, property.getPreferenceKey());

		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);
				methodBuilder.addCode("$L.putString($S,$L.$L());", editorName, property.getPreferenceKey(), beanName, METHOD_CONVERSION);			
			methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.remove($S);", editorName, property.getPreferenceKey());
		}
			
	}

}
