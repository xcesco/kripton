package com.abubusoft.kripton.binder.transform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
@SuppressWarnings("rawtypes")
class EnumTransform implements Transformable<Enum> {

	private final Class type;
	private Method valueMethod;
	private Method fromValueMethod;

	private boolean customEnum = true;// custom enum supports value/fromValue methods

	@SuppressWarnings("unchecked")
	public EnumTransform(Class type) {
		this.type = type;

		customEnum = true;

		try {
			this.valueMethod = type.getDeclaredMethod("value");
			this.fromValueMethod = type.getDeclaredMethod("fromValue", String.class);
		} catch (Exception e) {
			// throw new ConvertException("Fail to get declared method 'value' from enum type " + type.getName(), e);
			customEnum = false;
		}
	}

	// support invalid enum value
	@SuppressWarnings("unchecked")
	public Enum read(String value) {
		if (!customEnum) {
			try {
				return Enum.valueOf(type, value);
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else {
			try {
				return (Enum) this.fromValueMethod.invoke(null, value);
			} catch (InvocationTargetException e) {
				if (e.getTargetException().getClass() == java.lang.IllegalArgumentException.class) {
					return null;
				} else {
					throw new IllegalArgumentException("fail to convert string value : " + value + " to enum type : " + type.getName(), e);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("fail to convert string value : " + value + " to enum type : " + type.getName(), e);
			}
		}
	}

	public String write(Enum value) {
		if (!customEnum) {
			return value.name();
		} else {
			try {
				return (String) this.valueMethod.invoke(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("fail to convert enum value : " + value.toString() + " to string", e);
			}
		}
	}
}