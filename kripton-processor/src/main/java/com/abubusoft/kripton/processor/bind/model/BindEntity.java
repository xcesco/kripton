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
package com.abubusoft.kripton.processor.bind.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelClass;

/**
 * The Class BindEntity.
 */
public class BindEntity extends ModelClass<BindProperty> {

	/**
	 * Instantiates a new bind entity.
	 *
	 * @param name the name
	 * @param beanElement the bean element
	 * @param annotationList the annotation list
	 */
	public BindEntity(String name, TypeElement beanElement, List<ModelAnnotation> annotationList) {
		super(name, beanElement, annotationList);

		xmlInfo = new XmlInfo();
	}
	
	/** The xml info. */
	public XmlInfo xmlInfo;
	
	/**
	 * The Class XmlInfo.
	 */
	public static class XmlInfo {
		
		/** The label. */
		public String label;
		
		/**
		 * namespaces set
		 */
		public List<Pair<String, String>> namespaces=new ArrayList<>();
				
	}

}
