/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeVisitor;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class LiteralType {
	
	final static Map<String, LiteralType> cached=new HashMap<String, LiteralType>();

	private static final int GROUP_TYPE_GENERIC_INDEX = 1;
	
	private static final int GROUP_TYPE_PARAMETER_INDEX = 2;

	private static final int GROUP_ARRAY_INDEX = 3;

	private static final int GROUP_SIMPLE_INDEX = 4;
	
	private String value;
	
	/**
	 * @return the value
	 */
//	public String getValue() {
//		return value;
//	}

	protected boolean primitive;

	/**
	 * @return the primitive
	 */
//	public boolean isPrimitive() {
//		return primitive;
//	}

	private static final Pattern CLASS_PATTERN = Pattern.compile("([\\w\\.]+)\\<(.*)\\>|([\\w\\.]+)\\[\\]|([\\w\\.]+)");

	public String rawType;

	protected Class<?> resolvedRawType;

	private boolean array;

	private String typeParameter;

	public String getTypeParameter() {
		return typeParameter;
	}
	
	public boolean isParametrizedType()
	{
		return typeParameter!=null;
	}

	LiteralType()
	{
		
	}

//	protected LiteralType(String input) {
//		parse(this, input);
//	}
	
	public static LiteralType parse(String input) {
		LiteralType result=new LiteralType();
		parse(result, input);
		
		return result;
	}

	/**
	 * 
	 * 
	 * @param value
	 */
	static LiteralType parse(LiteralType result, String input) {
		result.value=input;
		Matcher matcher = CLASS_PATTERN.matcher(input);

		while (matcher.find()) {
			if (matcher.group(GROUP_SIMPLE_INDEX) != null || matcher.group(GROUP_ARRAY_INDEX) != null || matcher.group(GROUP_TYPE_GENERIC_INDEX) != null) {
				result.value = matcher.group(GROUP_SIMPLE_INDEX);
				if (result.value == null && matcher.group(GROUP_ARRAY_INDEX) != null) {
					result.value = matcher.group(GROUP_ARRAY_INDEX);
					result.array = true;
				}
				if (result.value == null && matcher.group(GROUP_TYPE_GENERIC_INDEX) != null) {
					result.value = matcher.group(GROUP_TYPE_GENERIC_INDEX);
					result.typeParameter=matcher.group(GROUP_TYPE_PARAMETER_INDEX);
				}

				if (result.value!=null && result.value.indexOf(".") >= 0) {
					// assert: JDK lang
					result.rawType = result.value;

					if (result.rawType.startsWith("java")) {
						// assert: raw type is type defined in jdk
						try {
							result.resolvedRawType = Class.forName(result.rawType);
						} catch (ClassNotFoundException e) {
						}
					}
				} else {
					// assert: primitive
					result.rawType = result.value;
					result.primitive = true;

					Class<?> resolved[] = { Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };

					for (Class<?> i : resolved) {
						if (result.rawType.equals(i.getSimpleName())) {
							result.resolvedRawType = i;
							break;
						}
					}
					
					if (result.rawType.equals(Void.TYPE.getSimpleName()))
					{
						result.resolvedRawType=Void.TYPE;
					}
				}

			}
		}
		return result;
	}

	public boolean isArray() {
		return array;
	}

	public static LiteralType of(String clazzString) {
		LiteralType newValue;
		if (cached.containsKey(clazzString))
		{
			return cached.get(clazzString);
		} else {
			
			newValue=LiteralType.parse(clazzString);
			cached.put(clazzString, newValue);
			
			return newValue;
		}
	}
	
//	public static LiteralType of(String rawType, String parametrizedType) {
//		String clazzName=rawType+"<"+parametrizedType+">";
//		LiteralType newValue;
//		if (cached.containsKey(clazzName))
//		{
//			return cached.get(clazzName);
//		} else {
//			
//			newValue=LiteralType.parse(clazzName);
//			cached.put(clazzName, newValue);
//			
//			return newValue;
//		}
//				
//	}
	
//	public static LiteralType of(Class<?> rawType, TypeElement parametrizedType) {
//		return of(rawType.getCanonicalName(), parametrizedType.getQualifiedName().toString());
//	}
	
	public boolean isCollection() {
		if (isResolved() && Collection.class.isAssignableFrom(resolvedRawType)) {
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

	public TypeKind getKind() {
		return null;
	}

	public <R, P> R accept(TypeVisitor<R, P> v, P p) {
		return null;
	}
	
	

}
