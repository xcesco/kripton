package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.sun.tools.internal.xjc.reader.TypeUtil;

@BindType
@BindAllFields
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public ModelProperty(Element element) {
		super(element.getSimpleName().toString(), element);
		
		setType(new ModelType(element.asType()));
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
	
	protected List<ModelAnnotation> annotations;
	
	protected ModelType type;
	
	/**
	 * @return the type
	 */
	public ModelType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ModelType type) {
		this.type = type;
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
		return this.getType().isEquals(value);
	}

	public boolean isType(Type ... types) {
		return TypeUtility.isTypeIncludedIn(type.value, types);		
	}

}
