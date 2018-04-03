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
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

@BindType
public class ModelProperty extends ModelEntity<Element> implements ModelElement, ModelWithAnnotation {
	public class TypeAdapter {
		public String adapterClazz;

		public String dataType;

		public TypeName getAdapterTypeName() {
			return TypeUtility.typeName(adapterClazz);
		}

		public TypeName getDataTypeTypename() {
			return TypeUtility.typeName(dataType);
		}
	}
	
	protected void checkTypeAdapter(@SuppressWarnings("rawtypes") ModelEntity entity, TypeMirror propertyType, TypeAdapter typeAdapter, ModelAnnotation annotation) {
		AssertKripton.fail(!this.detectSourceType(entity.getElement(), typeAdapter.adapterClazz).equals(propertyType.toString()),
				"In class '%s', property '%s' uses @%s that manages type '%s' instead of '%s'", entity.getElement().asType(), getName(),
				annotation.getSimpleName(), element.asType().toString(), this.detectSourceType(entity.getElement(), typeAdapter.adapterClazz), getPropertyType().getTypeName());

	}

	protected String detectDestinationType(Element element, String adapterClazz) {
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
	
	String detectSourceType(Element element, String adapterClazz) {
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

	@SuppressWarnings("rawtypes")
	protected WeakReference<ModelEntity> parent;

	@SuppressWarnings("rawtypes")
	public ModelEntity getParent() {
		return parent.get();
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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

	@SuppressWarnings("rawtypes")
	public ModelProperty(ModelEntity<?> entity, Element element, List<ModelAnnotation> modelAnnotations) {
		super((element != null) ? element.getSimpleName().toString() : null, element);

		this.parent = new WeakReference<ModelEntity>(entity);

		if (element != null) {
			propertyType = new ModelType(element.asType());
			publicField = element.getModifiers().contains(Modifier.PUBLIC);
		}
		this.annotations = new ArrayList<ModelAnnotation>();
		if (modelAnnotations != null) {
			this.annotations.addAll(modelAnnotations);
		}
		this.typeAdapter = new TypeAdapter();
	}

	@Override
	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	@Override
	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
				return item;
			}
		}

		return null;
	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz) != null;
	}

	protected List<ModelAnnotation> annotations;

	protected ModelType propertyType;

	public TypeAdapter typeAdapter;

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
	 * @param fieldWithGetter
	 *            the fieldWithGetter to set
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
	 * @param fieldWithSetter
	 *            the fieldWithSetter to set
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
	 * @param fieldWithIs
	 *            the fieldWithIs to set
	 */
	public void setFieldWithIs(boolean fieldWithIs) {
		this.fieldWithIs = fieldWithIs;
	}

	protected boolean fieldWithSetter;

	protected boolean fieldWithIs;

	/**
	 * if true, property is defined in a class. If false property is map entry
	 * component or collection item.
	 * 
	 * @return
	 */
	public boolean isProperty() {
		return element != null;
	}

	/**
	 * @return the publicField
	 */
	public boolean isPublicField() {
		return publicField;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void accept(@SuppressWarnings("rawtypes") ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	public boolean isType(TypeName value) {
		return TypeUtility.isEquals(getPropertyType().getTypeName(), value);
	}

	public boolean isType(Type... types) {
		return TypeUtility.isTypeIncludedIn(propertyType.typeName, types);
	}

	public boolean hasTypeAdapter() {
		return typeAdapter.adapterClazz != null;
	}

}
