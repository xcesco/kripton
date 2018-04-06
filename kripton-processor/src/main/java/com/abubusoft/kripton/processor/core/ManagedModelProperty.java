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
package com.abubusoft.kripton.processor.core;

import java.util.List;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.bind.model.BindProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagedModelProperty.
 */
public class ManagedModelProperty extends ModelProperty {

	/**
	 * Instantiates a new managed model property.
	 *
	 * @param entity the entity
	 * @param element the element
	 * @param modelAnnotations the model annotations
	 */
	@SuppressWarnings("rawtypes")
	public ManagedModelProperty(ModelEntity entity, Element element, List<ModelAnnotation> modelAnnotations) {
		super(entity, element, modelAnnotations);
	}

	/** The bind property. */
	public BindProperty bindProperty;
}
