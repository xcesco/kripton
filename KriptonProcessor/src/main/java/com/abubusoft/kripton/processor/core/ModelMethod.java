/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

public class ModelMethod extends ModelEntity<ExecutableElement> implements ModelWithAnnotation {

	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
				return item;
			}
		}

		return null;
	}

	public ModelMethod(ExecutableElement element) {
		super(element.getSimpleName().toString(), element);
		this.parameters = new ArrayList<Pair<String, TypeName>>();
		this.annotations = new ArrayList<ModelAnnotation>();

		for (VariableElement p : element.getParameters()) {
			parameters.add(new Pair<String, TypeName>(p.getSimpleName().toString(), TypeUtility.typeName(p.asType())));
		}
		
		returnClass = TypeUtility.typeName(element.getReturnType());
		
//		if (element.getReturnType() instanceof TypeVariable) {			
//			//TODO for the moment, if method return type is typeVariable, we set it to null 
//			returnClass = null;
//		} else {
//			
//		}
	}

	protected List<ModelAnnotation> annotations;

	/**
	 * @return the parameters
	 */
	public List<Pair<String, TypeName>> getParameters() {
		return parameters;
	}

	/**
	 * @return the returnClass
	 */
	public TypeName getReturnClass() {
		return returnClass;
	}

	/**
	 * @param returnClass the returnClass to set
	 */
	public void setReturnClass(TypeName returnClass) {
		this.returnClass = returnClass;
	}

	protected List<Pair<String, TypeName>> parameters;

	protected TypeName returnClass;

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 *            parameter name to find
	 * @return TypeMirror associated
	 */
	public TypeName findParameterType(String name) {
		for (Pair<String, TypeName> item : parameters) {
			if (item.value0.equals(name)) {
				return item.value1;
			}
		}
		return null;
	}

	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 *            parameter name to find
	 * @return true if there is parameter with specified name
	 */
//	public boolean hasParameter(String name) {
//		for (Pair<String, TypeMirror> item : parameters) {
//			if (item.value0.equals(name)) {
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz)!=null;
	}
}
