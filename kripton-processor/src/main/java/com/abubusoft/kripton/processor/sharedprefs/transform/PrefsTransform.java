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

import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Class implementing this interface can be used to generate code to read and write the property for a binded shared preferences.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public interface PrefsTransform {

	/**
	 * Generate read property.
	 *
	 * @param methodBuilder the method builder
	 * @param preferenceName the preference name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param singleRead the single read
	 * @param generateReturn if true, generates return
	 */
	void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, PrefsProperty property, boolean singleRead, ReadType readType);
	
	/**
	 * Generate write property.
	 *
	 * @param methodBuilder the method builder
	 * @param editorName the editor name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, PrefsProperty property);
	
	/**
	 * Checks if is type adapter aware.
	 *
	 * @return true, if is type adapter aware
	 */
	boolean isTypeAdapterAware();

}


