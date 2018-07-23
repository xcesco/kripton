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
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * The Class ModelProperty.
 */
@BindType
public class ModelProperty extends ModelEntity<Element> implements ModelElement, ModelWithAnnotation {
	
	/**
	 * The Class TypeAdapter.
	 */
	public class TypeAdapter {
		
		/** The adapter clazz. */
		public String adapterClazz;

		/** The data type. */
		public String dataType;

		/**
		 * Gets the adapter type name.
		 *
		 * @return the adapter type name
		 */
		public TypeName getAdapterTypeName() {
			return TypeUtility.typeName(adapterClazz);
		}

		/**
		 * Gets the data type typename.
		 *
		 * @return the data type typename
		 */
		public TypeName getDataTypeTypename() {
			return TypeUtility.typeName(dataType);
		}
	}
	
	/**
	 * Check type adapter.
	 *
	 * @param entity the entity
	 * @param propertyType the property type
	 * @param typeAdapter the type adapter
	 * @param annotation the annotation
	 */
	protected void checkTypeAdapter(@SuppressWarnings("rawtypes") ModelEntity entity, TypeMirror propertyType, TypeAdapter typeAdapter, ModelAnnotation annotation) {
		TypeName sourceType = TypeUtility.typeName(TypeAdapterHelper.detectSourceType(entity.getElement(), typeAdapter.adapterClazz));
		TypeName uboxSourceType=sourceType;
		
		if (TypeUtility.isTypeWrappedPrimitive(sourceType)) {
			uboxSourceType=sourceType.unbox();
		}
		
		boolean expr=uboxSourceType.toString().equals(propertyType.toString()) || sourceType.toString().equals(propertyType.toString());
		
		
		AssertKripton.fail(!expr,
				"In class '%s', property '%s' uses @%s that manages type '%s' instead of '%s'", entity.getElement().asType(), getName(),
				annotation.getSimpleName(), element.asType().toString(), TypeAdapterHelper.detectSourceType(entity.getElement(), typeAdapter.adapterClazz), getPropertyType().getTypeName());

	}

	/** The parent. */
	@SuppressWarnings("rawtypes")
	protected WeakReference<ModelEntity> parent;

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
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

	/**
	 * Instantiates a new model property.
	 *
	 * @param entity the entity
	 * @param element the element
	 * @param modelAnnotations the model annotations
	 */
	@SuppressWarnings("rawtypes")
	public ModelProperty(ModelEntity<?> entity, Element element, List<ModelAnnotation> modelAnnotations) {
		super((element != null) ? element.getSimpleName().toString() : null, element);

		this.parent = new WeakReference<ModelEntity>(entity);

		if (element != null) {			
			TypeName temp1=TypeName.get(element.asType());
			LiteralType temp2 = LiteralType.of(element.asType().toString());
			AssertKripton.fail((temp1 instanceof ClassName) && temp2.isCollection(), "In bean '%s' property '%s' can not use Object as parameter", entity.getElement().asType().toString() ,element.getSimpleName().toString()); 
			
			
			propertyType = new ModelType(element.asType());
			publicField = element.getModifiers().contains(Modifier.PUBLIC);
		}
		this.annotations = new ArrayList<ModelAnnotation>();
		if (modelAnnotations != null) {
			this.annotations.addAll(modelAnnotations);
		}
		this.typeAdapter = new TypeAdapter();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#addAnnotation(com.abubusoft.kripton.processor.core.ModelAnnotation)
	 */
	@Override
	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#getAnnotation(java.lang.Class)
	 */
	@Override
	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
				return item;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelWithAnnotation#hasAnnotation(java.lang.Class)
	 */
	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz) != null;
	}

	/** The annotations. */
	protected List<ModelAnnotation> annotations;

	/** The property type. */
	protected ModelType propertyType;

	/** The type adapter. */
	public TypeAdapter typeAdapter;

	/**
	 * Gets the property type.
	 *
	 * @return the type
	 */
	public ModelType getPropertyType() {
		return propertyType;
	}

	/** The public field. */
	protected boolean publicField;

	/** The field with getter. */
	protected boolean fieldWithGetter;

	/**
	 * Checks if is field with getter.
	 *
	 * @return the fieldWithGetter
	 */
	public boolean isFieldWithGetter() {
		return fieldWithGetter;
	}

	/**
	 * Sets the field with getter.
	 *
	 * @param fieldWithGetter            the fieldWithGetter to set
	 */
	public void setFieldWithGetter(boolean fieldWithGetter) {
		this.fieldWithGetter = fieldWithGetter;
	}

	/**
	 * Checks if is field with setter.
	 *
	 * @return the fieldWithSetter
	 */
	public boolean isFieldWithSetter() {
		return fieldWithSetter;
	}

	/**
	 * Sets the field with setter.
	 *
	 * @param fieldWithSetter            the fieldWithSetter to set
	 */
	public void setFieldWithSetter(boolean fieldWithSetter) {
		this.fieldWithSetter = fieldWithSetter;
	}

	/**
	 * Checks if is field with is.
	 *
	 * @return the fieldWithIs
	 */
	public boolean isFieldWithIs() {
		return fieldWithIs;
	}

	/**
	 * Sets the field with is.
	 *
	 * @param fieldWithIs            the fieldWithIs to set
	 */
	public void setFieldWithIs(boolean fieldWithIs) {
		this.fieldWithIs = fieldWithIs;
	}

	/** The field with setter. */
	protected boolean fieldWithSetter;

	/** The field with is. */
	protected boolean fieldWithIs;

	/**
	 * if true, property is defined in a class. If false property is map entry
	 * component or collection item.
	 *
	 * @return true, if is property
	 */
	public boolean isProperty() {
		return element != null;
	}

	/**
	 * Checks if is public field.
	 *
	 * @return the publicField
	 */
	public boolean isPublicField() {
		return publicField;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelElement#accept(com.abubusoft.kripton.processor.core.ModelElementVisitor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void accept(@SuppressWarnings("rawtypes") ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	/**
	 * Checks if is type.
	 *
	 * @param value the value
	 * @return true, if is type
	 */
	public boolean isType(TypeName value) {
		return TypeUtility.isEquals(getPropertyType().getTypeName(), value);
	}

	/**
	 * Checks if is type.
	 *
	 * @param types the types
	 * @return true, if is type
	 */
	public boolean isType(Type... types) {
		return TypeUtility.isTypeIncludedIn(propertyType.typeName, types);
	}

	/**
	 * Checks for type adapter.
	 *
	 * @return true, if successful
	 */
	public boolean hasTypeAdapter() {
		return typeAdapter.adapterClazz != null;
	}

}
