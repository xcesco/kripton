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

import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java Byte object.
 *
 * @author xcesco
 */
abstract class AbstractPrimitivePrefsTransform extends AbstractPrefsTransform {

	/**
	 * Instantiates a new abstract primitive prefs transform.
	 *
	 * @param nullable the nullable
	 */
	public AbstractPrimitivePrefsTransform(boolean nullable) {
		super(true);
		this.nullable = nullable;		
	}
	
	/**
	 * Instantiates a new abstract primitive prefs transform.
	 *
	 * @param nullable the nullable
	 * @param typeAware the type aware
	 */
	public AbstractPrimitivePrefsTransform(boolean nullable, boolean typeAware) {
		super(typeAware);
		this.nullable = nullable;		
	}
	
	/** The nullable. */
	protected boolean nullable;

	/** The simple type. */
	protected String SIMPLE_TYPE;
	
	/** The prefs convert. */
	protected String PREFS_CONVERT;
	
	/** The prefs type. */
	protected String PREFS_TYPE;
	
	/** The prefs default value. */
	protected String PREFS_DEFAULT_VALUE;

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#generateReadProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty, boolean)
	 */
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, PrefsProperty property, boolean readAll, ReadType readType) {
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

		String tempPre="";
		String tempPost="";
		if (property.hasTypeAdapter()) {
			methodBuilder.addCode("$T.getAdapter($T.class).toJava(", PrefsTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz));
			tempPre=String.format("%s.getAdapter(%s.class).toData(", PrefsTypeAdapterUtils.class.getSimpleName(), TypeUtility.className(property.typeAdapter.adapterClazz).simpleName());
			tempPost=")";
		}
		if (nullable) {
			methodBuilder.addCode(SIMPLE_TYPE + "$L.get" + PREFS_TYPE + "($S, " + SIMPLE_TYPE + "($L==null?" + PREFS_DEFAULT_VALUE + ":"+tempPre+"$L"+tempPost+"))", preferenceName, property.getPreferenceKey(), getter(beanName, beanClass, property),
					getter(beanName, beanClass, property));
		} else {
			methodBuilder.addCode(SIMPLE_TYPE + "$L.get" + PREFS_TYPE + "($S, " + SIMPLE_TYPE + tempPre+"$L"+tempPost+")", preferenceName, property.getPreferenceKey(), getter(beanName, beanClass, property));
		}

		if (readAll) {
			methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
		}
		if (property.hasTypeAdapter()) {
			methodBuilder.addCode(")");
		}

		methodBuilder.addCode(";");
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform#generateWriteProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty)
	 */
	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, PrefsProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		methodBuilder.addCode("$L.put" + PREFS_TYPE + "($S,", editorName, property.getPreferenceKey());
		
		if (property.hasTypeAdapter()) {
			methodBuilder.addCode("$T.getAdapter($T.class).toData(", PrefsTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz));
		}
		methodBuilder.addCode( PREFS_CONVERT + "$L", getter(beanName, beanClass, property));
		if (property.hasTypeAdapter()) {
			methodBuilder.addCode(")");
		}
		
		methodBuilder.addCode(");\n");
		
		if (nullable) {
			methodBuilder.endControlFlow();
		}

	}
	


}
