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

// TODO: Auto-generated Javadoc
/**
 * The Class LiteralType.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class LiteralType { 
	
	/** The Constant cached. */
	final static Map<String, LiteralType> cached=new HashMap<String, LiteralType>();

	/** The Constant GROUP_TYPE_GENERIC_INDEX. */
	private static final int GROUP_TYPE_GENERIC_INDEX = 1;
	
	/** The Constant GROUP_TYPE_PARAMETER_INDEX. */
	private static final int GROUP_TYPE_PARAMETER_INDEX = 2;

	/** The Constant GROUP_ARRAY_INDEX. */
	private static final int GROUP_ARRAY_INDEX = 3;

	/** The Constant GROUP_SIMPLE_INDEX. */
	private static final int GROUP_SIMPLE_INDEX = 4;
	
	/** The value. */
	private String value;
	
	protected boolean primitive;

	private static final Pattern CLASS_PATTERN = Pattern.compile("([\\w\\.]+)\\<(.*)\\>|([\\w\\.]+)\\[\\]|([\\w\\.]+)");

	/** The raw type. */
	public String rawType;

	/** The resolved raw type. */
	protected Class<?> resolvedRawType;

	/** The array. */
	private boolean array;

	/** The type parameter. */
	private String typeParameter;

	/**
	 * Gets the type parameter.
	 *
	 * @return the type parameter
	 */
	public String getTypeParameter() {
		return typeParameter;
	}
	
	/**
	 * Checks if is parametrized type.
	 *
	 * @return true, if is parametrized type
	 */
	public boolean isParametrizedType()
	{
		return typeParameter!=null;
	}

	/**
	 * Instantiates a new literal type.
	 */
	LiteralType()
	{
		
	}

//	protected LiteralType(String input) {
//		parse(this, input);
//	}
	
	/**
 * Parses the.
 *
 * @param input the input
 * @return the literal type
 */
public static LiteralType parse(String input) {
		LiteralType result=new LiteralType();
		parse(result, input);
		
		return result;
	}

	/**
	 * Parses the.
	 *
	 * @param result the result
	 * @param input the input
	 * @return the literal type
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
					
					if (Void.TYPE.getSimpleName().equals(result.rawType))
					{
						result.resolvedRawType=Void.TYPE;
					}
				}

			}
		}
		return result;
	}

	/**
	 * Checks if is array.
	 *
	 * @return true, if is array
	 */
	public boolean isArray() {
		return array;
	}

	/**
	 * Of.
	 *
	 * @param clazzString the clazz string
	 * @return the literal type
	 */
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
	
	/**
 * Checks if is collection.
 *
 * @return true, if is collection
 */
public boolean isCollection() {
		if (isResolved() && Collection.class.isAssignableFrom(resolvedRawType)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is resolved.
	 *
	 * @return true, if is resolved
	 */
	public boolean isResolved() {
		return resolvedRawType != null;
	}

	/**
	 * Gets the raw type.
	 *
	 * @return the raw type
	 */
	public String getRawType() {
		return rawType;
	}

	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public TypeKind getKind() {
		return null;
	}

	/**
	 * Accept.
	 *
	 * @param <R> the generic type
	 * @param <P> the generic type
	 * @param v the v
	 * @param p the p
	 * @return the r
	 */
	public <R, P> R accept(TypeVisitor<R, P> v, P p) {
		return null;
	}
	
	

}
