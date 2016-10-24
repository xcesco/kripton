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
/**
 * 
 */
package com.abubusoft.kripton.processor.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;

/**
 * @author xcesco
 *
 */
public class LiteralType implements TypeMirror {
	
	final static Map<String, LiteralType> cached=new HashMap<String, LiteralType>();

	private static final int GROUP_COMPLEX_INDEX = 1;
	
	private static final int GROUP_COMPOSED_INDEX = 2;

	private static final int GROUP_ARRAY_INDEX = 3;

	private static final int GROUP_SIMPLE_INDEX = 4;

	private String value;
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	protected boolean primitive;

	/**
	 * @return the primitive
	 */
	public boolean isPrimitive() {
		return primitive;
	}

	private static final Pattern CLASS_PATTERN = Pattern.compile("([\\w\\.]+)\\<(.*)\\>|([\\w\\.]+)\\[\\]|([\\w\\.]+)");

	protected String rawType;

	protected Class<?> resolvedRawType;

	private boolean array;

	private String composedType;

	public String getComposedValue() {
		return composedType;
	}

	protected LiteralType(String value) {
		this.value=value;
		parse(value);
	}

	/**
	 * 
	 * 
	 * @param value
	 */
	protected void parse(String value) {
		Matcher matcher = CLASS_PATTERN.matcher(value);

		while (matcher.find()) {
			if (matcher.group(GROUP_SIMPLE_INDEX) != null || matcher.group(GROUP_ARRAY_INDEX) != null || matcher.group(GROUP_COMPLEX_INDEX) != null) {
				value = matcher.group(GROUP_SIMPLE_INDEX);
				if (value == null && matcher.group(GROUP_ARRAY_INDEX) != null) {
					value = matcher.group(GROUP_ARRAY_INDEX);
					array = true;
				}
				if (value == null && matcher.group(GROUP_COMPLEX_INDEX) != null) {
					value = matcher.group(GROUP_COMPLEX_INDEX);
					composedType=matcher.group(GROUP_COMPOSED_INDEX);;
				}

				if (value.indexOf(".") >= 0) {
					// assert: JDK lang
					rawType = value;

					if (rawType.startsWith("java")) {
						// assert: raw type is type defined in jdk
						try {
							resolvedRawType = Class.forName(rawType);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				} else {
					// assert: primitive
					rawType = value;
					primitive = true;

					// Class<?> primitive[]={Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class};
					Class<?> resolved[] = { Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };

					for (Class<?> i : resolved) {
						if (rawType.equals(i.getSimpleName())) {
							resolvedRawType = i;
							break;
						}
					}
				}

			}
		}
	}

	public boolean isArray() {
		return array;
	}
	
	public static LiteralType of(Class<?> clazz) {
		return of(clazz.getCanonicalName());				
	}

	public static LiteralType of(String clazzString) {
		LiteralType newValue;
		if (cached.containsKey(clazzString))
		{
			return cached.get(clazzString);
		} else {
			
			newValue=new LiteralType(clazzString);
			cached.put(clazzString, newValue);
			
			return newValue;
		}
				
	}
	
	public static LiteralType of(String rawType, String parametrizedType) {
		String clazzName=rawType+"<"+parametrizedType+">";
		LiteralType newValue;
		if (cached.containsKey(clazzName))
		{
			return cached.get(clazzName);
		} else {
			
			newValue=new LiteralType(clazzName);
			cached.put(clazzName, newValue);
			
			return newValue;
		}
				
	}
	
	public static LiteralType of(Class<?> rawType, TypeElement parametrizedType) {
		return of(rawType.getCanonicalName(), parametrizedType.getQualifiedName().toString());
	}
	
	public static LiteralType of(Class<?> rawType, Class<?> parametrizedType) {
		return of(rawType.getCanonicalName(), parametrizedType.getCanonicalName());
	}

	public boolean isCollection() {
		if (isResolved() && Collection.class.isAssignableFrom(resolvedRawType)) {
			return true;
		}

		return false;
	}
	
	public boolean isComposed()
	{
		return isCollection() || isMap() || composedType!=null;
	}


	public boolean isList() {
		if (isResolved() && List.class.isAssignableFrom(resolvedRawType)) {
			return true;
		}

		return false;
	}

	public boolean isMap() {
		if (isResolved() && Map.class.isAssignableFrom(resolvedRawType)) {
			return true;
		}

		return false;
	}

	public boolean isResolved() {
		return resolvedRawType != null;
	}

	public String getRawType() {
		return rawType;
	}
	
	public boolean isComposedType(Class<?> clazz)	
	{
		return isComposedType(clazz.getName());
	}
	
	public boolean isComposedType(String clazzName)
	{
		if (isComposed())
		{
			if (this.composedType.equals(clazzName))
			{
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public TypeKind getKind() {

		return null;
	}

	@Override
	public <R, P> R accept(TypeVisitor<R, P> v, P p) {
		return null;
	}

}
