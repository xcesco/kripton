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
package com.abubusoft.kripton.processor.sharedprefs.model;

import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;

/**
 * The Class PrefsEntity.
 */
public class PrefsEntity extends ModelClass<PrefsProperty> {

	/**
	 * Instantiates a new prefs entity.
	 *
	 * @param name the name
	 * @param beanElement the bean element
	 * @param annotationList the annotation list
	 */
	public PrefsEntity(String name, TypeElement beanElement, List<ModelAnnotation> annotationList) {
		super(name, beanElement, annotationList);
		
		generateGlobalRx = AnnotationUtility.extractAsBoolean(element, BindSharedPreferences.class, AnnotationAttributeType.GENERATE_RX);
		generateGlobalLiveData = AnnotationUtility.extractAsBoolean(element, BindSharedPreferences.class, AnnotationAttributeType.GENERATE_LIVE_DATA);		
	}
	
	boolean generateGlobalRx;
	
	boolean generateGlobalLiveData;
	
	public boolean hasRxProperties() {
		if (generateGlobalRx==true) return true;
		
		for (PrefsProperty p: this.collection) {
			if (p.generateRx) return true;
		}
		
		return false;
	}
	
	public boolean hasLiveDataProperties() {
		if (generateGlobalLiveData==true) return true;
		
		for (PrefsProperty p: this.collection) {
			if (p.generateLiveData) return true;
		}
		
		return false;
	}


}
