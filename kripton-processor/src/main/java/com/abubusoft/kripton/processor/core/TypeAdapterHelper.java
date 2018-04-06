/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeAdapterHelper.
 */
public class TypeAdapterHelper {
	
	/**
	 * Detect source type.
	 *
	 * @param adapterClazz the adapter clazz
	 * @return the string
	 */
	public static String detectSourceType(String adapterClazz) {
		return detectSourceType(null, adapterClazz);
	}
	
	/**
	 * Detect source type.
	 *
	 * @param element the element
	 * @param adapterClazz the adapter clazz
	 * @return the string
	 */
	public static String detectSourceType(Element element, String adapterClazz) {
		TypeElement a = BaseProcessor.elementUtils.getTypeElement(adapterClazz);
		for (Element i : BaseProcessor.elementUtils.getAllMembers(a)) {
			if (i.getKind() == ElementKind.METHOD && "toJava".equals(i.getSimpleName().toString())) {
				ExecutableElement method = (ExecutableElement) i;
				return TypeUtility.typeName(method.getReturnType()).toString();
			}
		}

		AssertKripton.fail("In '%s', class '%s' can not be used as type adapter", element, adapterClazz);
		return null;
	}

	/**
	 * Detect destination type.
	 *
	 * @param element the element
	 * @param adapterClazz the adapter clazz
	 * @return the string
	 */
	public static String detectDestinationType(Element element, String adapterClazz) {
		TypeElement a = BaseProcessor.elementUtils.getTypeElement(adapterClazz);
		for (Element i : BaseProcessor.elementUtils.getAllMembers(a)) {
			if (i.getKind() == ElementKind.METHOD && "toData".equals(i.getSimpleName().toString())) {
				ExecutableElement method = (ExecutableElement) i;
				return TypeUtility.typeName(method.getReturnType()).toString();
			}
		}

		AssertKripton.fail("In '%s', class '%s' can not be used as type adapter", element, adapterClazz);
		return null;
	}
}
