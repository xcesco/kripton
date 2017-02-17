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

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeVariableResolver;
import com.squareup.javapoet.TypeName;

@BindType
public class ModelClass<E extends ModelProperty> extends ModelBucket<E, TypeElement> implements ModelElement, ModelWithAnnotation {

	protected List<ModelAnnotation> annotations;
	
	protected TypeVariableResolver typeVariableResolver;

	public ModelClass(TypeElement element) {
		this(element.getQualifiedName().toString(), element);
	}

	public ModelClass(String name, TypeElement beanElement) {
		super(name, beanElement);

		this.annotations = new ArrayList<ModelAnnotation>();
		this.typeVariableResolver=TypeVariableResolver.build(beanElement);
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

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz) != null;
	}

	public TypeName resolveTypeVariable(TypeName typeName) {
		return typeVariableResolver.resolve(typeName);
	}
	
	@Override
	public void add(E value) {		 
		TypeName typeName=value.getPropertyType().getTypeName();
		TypeName resolvedTypeName=typeVariableResolver.resolve(typeName);
		
		value.getPropertyType().setTypeName(resolvedTypeName);
		
		super.add(value);
	}

}
