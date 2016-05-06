package com.abubusoft.kripton.processor.core;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

@BindType
@BindAllFields
public class ModelType implements ModelElement {
	
	TypeName value;
	
	public ModelType(TypeMirror type) {
		this.value=TypeName.get(type);
	}

	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}
	
	public boolean isEquals(String value)
	{
		return TypeUtility.isSameType(this.value, value);
	}
	
	public boolean isSameType(String ... value)
	{
		for (String item: value)
		{
			if (this.value.equals(item))
			{
				return true;
			}
		}
		return false;
	}
	

}
