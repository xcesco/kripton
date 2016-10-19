package com.abubusoft.kripton.processor.core;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.TypeName;

@BindType
public class ModelType extends LiteralType {
	
	TypeName name;
	
	/**
	 * @return the value
	 */
	public TypeName getName() {
		return name;
	}

	public ModelType(TypeMirror type) {
		super(type.toString());		
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
			if (this.name.toString().equals(item))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isSameRawType(String ... value)
	{
		for (String item: value)
		{
			if (this.rawType.equals(item))
			{
				return true;
			}
		}
		return false;
	}

	public boolean isSameType(Class<?> clazz) {
		return isSameType(clazz.getName());
	}

	public boolean isEnum() {
		// TODO Auto-generated method stub
		return false;
	}

}
