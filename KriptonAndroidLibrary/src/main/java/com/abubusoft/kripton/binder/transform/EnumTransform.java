/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
class EnumTransform implements Transform<Enum> {

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