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
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ModelClass<E extends ModelProperty> extends ModelBucket<E, TypeElement> implements ModelElement, ModelWithAnnotation {

	protected List<ModelAnnotation> annotations;

	public ModelClass(TypeElement element) {
		this(element.getQualifiedName().toString(), element);
	}

	public ModelClass(String name, TypeElement element) {
		super(name, element);

		this.annotations = new ArrayList<ModelAnnotation>();
	}

	public String getSimpleName() {
		return element.getSimpleName().toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		String canonicalName = value.getCanonicalName();

		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(canonicalName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * @return the annotations
	 */
//	public List<ModelAnnotation> getAnnotations() {
//		return annotations;
//	}

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz)!=null;
	}

}
