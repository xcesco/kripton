package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class KriptonProperty extends KriptonEntity implements KriptonElement {
		
	public KriptonProperty(Element element) {
		super(element);
	}
	
	protected KriptonType type;
	
	/**
	 * @return the type
	 */
	public KriptonType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(KriptonType type) {
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
	public void accept(KriptonElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}

}
