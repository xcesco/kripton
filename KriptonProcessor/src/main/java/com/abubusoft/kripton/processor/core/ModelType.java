package com.abubusoft.kripton.processor.core;

import java.lang.reflect.Type;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class ModelType implements ModelElement {
	
	String value;
	
	public ModelType(TypeMirror type) {
		this.value=type.toString();
	}

	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}
	
	public boolean isSameType(String value)
	{
		//return type.toString().equals(value);
		return this.value.equals(value);
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

	public boolean isSameType(Type ... value) {
		for (Type item: value)
		{
			if (this.value.equals(item.toString()))
			{
				return true;
			}
		}
		return false;
	}

}
