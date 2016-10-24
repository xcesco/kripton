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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;

@BindType
public class ModelProperty extends ModelEntity<Element> implements ModelElement, ModelWithAnnotation {
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
		result = prime * result + (fieldWithGetter ? 1231 : 1237);
		result = prime * result + (fieldWithIs ? 1231 : 1237);
		result = prime * result + (fieldWithSetter ? 1231 : 1237);
		result = prime * result + (publicField ? 1231 : 1237);
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelProperty other = (ModelProperty) obj;
		if (annotations == null) {
			if (other.annotations != null)
				return false;
		} else if (!annotations.equals(other.annotations))
			return false;
		if (fieldWithGetter != other.fieldWithGetter)
			return false;
		if (fieldWithIs != other.fieldWithIs)
			return false;
		if (fieldWithSetter != other.fieldWithSetter)
			return false;
		if (publicField != other.publicField)
			return false;
		if (propertyType == null) {
			if (other.propertyType != null)
				return false;
		} else if (!propertyType.equals(other.propertyType))
			return false;
		return true;
	}

	public ModelProperty(Element element) {
		super(element.getSimpleName().toString(), element);
		
		this.propertyType=new ModelType(element.asType());
		setPublicField(element.getModifiers().contains(Modifier.PUBLIC));
		this.annotations = new ArrayList<ModelAnnotation>();
	}
	
	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}
	
	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
				return item;
			}
		}
		
		return null;
	}
	
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz)!=null;
	}
	
	protected List<ModelAnnotation> annotations;
	
	protected ModelType propertyType;
	
	/**
	 * @return the type
	 */
	public ModelType getPropertyType() {
		return propertyType;
	}

	protected boolean publicField;
	
	protected boolean fieldWithGetter;
	
	/**
	 * @return the fieldWithGetter
	 */
	public boolean isFieldWithGetter() {
		return fieldWithGetter;
	}

	/**
	 * @param fieldWithGetter the fieldWithGetter to set
	 */
	public void setFieldWithGetter(boolean fieldWithGetter) {
		this.fieldWithGetter = fieldWithGetter;
	}

	/**
	 * @return the fieldWithSetter
	 */
	public boolean isFieldWithSetter() {
		return fieldWithSetter;
	}

	/**
	 * @param fieldWithSetter the fieldWithSetter to set
	 */
	public void setFieldWithSetter(boolean fieldWithSetter) {
		this.fieldWithSetter = fieldWithSetter;
	}

	/**
	 * @return the fieldWithIs
	 */
	public boolean isFieldWithIs() {
		return fieldWithIs;
	}

	/**
	 * @param fieldWithIs the fieldWithIs to set
	 */
	public void setFieldWithIs(boolean fieldWithIs) {
		this.fieldWithIs = fieldWithIs;
	}

	protected boolean fieldWithSetter;
	
	protected boolean fieldWithIs;

	/**
	 * @return the publicField
	 */
	public boolean isPublicField() {
		return publicField;
	}

	/**
	 * @param publicField the publicField to set
	 */
	public void setPublicField(boolean publicField) {
		this.publicField = publicField;
	}
	
	public boolean isReadable()
	{
		return publicField || fieldWithGetter || fieldWithIs;
	}
	
	public boolean isWritable()
	{
		return publicField || fieldWithSetter;
	}
	
	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}

	public boolean isType(String value) {
		return this.getPropertyType().isEquals(value);
	}

	public boolean isType(Type ... types) {
		return TypeUtility.isTypeIncludedIn(propertyType.name, types);		
	}
		
		
}
