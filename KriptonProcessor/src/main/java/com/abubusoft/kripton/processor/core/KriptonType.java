package com.abubusoft.kripton.processor.core;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class KriptonType implements KriptonElement {
	
	//TypeMirror type;
	
	String value;
	
	public KriptonType(TypeMirror type) {
		this.value=type.toString();
	}

	@Override
	public void accept(KriptonElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}
	
	public boolean isSameType(String value)
	{
		//return type.toString().equals(value);
		return this.value.equals(value);
	}

}
