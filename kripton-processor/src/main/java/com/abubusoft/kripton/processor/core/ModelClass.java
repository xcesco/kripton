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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeVariableResolver;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelClass.
 *
 * @param <E> the element type
 */
@BindType
public class ModelClass<E extends ModelProperty> extends ModelBucket<E, TypeElement> implements ModelElement, ModelWithAnnotation {

	/** The annotations. */
	protected List<ModelAnnotation> annotations;
	
	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public List<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	/** The type variable resolver. */
	protected TypeVariableResolver typeVariableResolver;

	/**
	 * Instantiates a new model class.
	 *
	 * @param element the element
	 */
	public ModelClass(TypeElement element) {
		this(element.getQualifiedName().toString(), element, null);
	}

	/**
	 * Instantiates a new model class.
	 *
	 * @param name the name
	 * @param beanElement the bean element
	 * @param annotationList the annotation list
	 */
	public ModelClass(String name, TypeElement beanElement, List<ModelAnnotation> annotationList) {
		super(name, beanElement);

		this.annotations = annotationList==null ? new ArrayList<ModelAnnotation>() : annotationList;
		this.typeVariableResolver=TypeVariableResolver.build(beanElement);
	}


	/**
	 * Gets the simple name.
	 *
	 * @return the simple name
	 */
	public String getSimpleName() {
		return element.getSimpleName().toString();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelElement#accept(com.abubusoft.kripton.processor.core.ModelElementVisitor)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(null, this);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#getAnnotation(java.lang.Class)
	 */
	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		String canonicalName = value.getCanonicalName();

		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(canonicalName)) {
				return item;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#addAnnotation(com.abubusoft.kripton.processor.core.ModelAnnotation)
	 */
	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	/**
	 * Contains annotation.
	 *
	 * @param annotation the annotation
	 * @return true, if successful
	 */
	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#hasAnnotation(java.lang.Class)
	 */
	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz) != null;
	}

	/**
	 * Resolve type variable.
	 *
	 * @param typeName the type name
	 * @return the type name
	 */
	public TypeName resolveTypeVariable(TypeName typeName) {
		return typeVariableResolver.resolve(typeName);
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelBucket#add(com.abubusoft.kripton.processor.core.ModelEntity)
	 */
	@Override
	public void add(E value) {		 
		TypeName typeName=value.getPropertyType().getTypeName();
		TypeName resolvedTypeName=typeVariableResolver.resolve(typeName);
		
		value.getPropertyType().setTypeName(resolvedTypeName);
		
		super.add(value);
	}

}
