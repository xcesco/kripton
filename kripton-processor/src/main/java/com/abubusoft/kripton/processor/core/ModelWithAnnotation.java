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
package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;

// TODO: Auto-generated Javadoc
/**
 * The Interface ModelWithAnnotation.
 */
public interface ModelWithAnnotation extends ModelWrapperElement {
	
	/**
	 * Adds the annotation.
	 *
	 * @param annotation the annotation
	 */
	void addAnnotation(ModelAnnotation annotation);

	/**
	 * Gets the annotation.
	 *
	 * @param value the value
	 * @return the annotation
	 */
	ModelAnnotation getAnnotation(Class<? extends Annotation> value);
	
	/**
	 * Checks for annotation.
	 *
	 * @param annotationClazz the annotation clazz
	 * @return true, if successful
	 */
	boolean hasAnnotation(Class<? extends Annotation> annotationClazz);
}
