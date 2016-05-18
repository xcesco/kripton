package com.abubusoft.kripton.processor.core;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

@BindType
@BindAllFields
public class ModelType {
	
	TypeName name;
	
	/**
	 * @return the value
	 */
	public TypeName getName() {
		return name;
	}

	public ModelType(TypeMirror type) {
		this.name=TypeName.get(type);
	}

	
	public boolean isEquals(String value)
	{
		return TypeUtility.isSameType(this.name, value);
	}
	
	public boolean isSameType(String ... value)
	{
		for (String item: value)
		{
			if (this.name.equals(item))
			{
				return true;
			}
		}
		return false;
	}
	

}
