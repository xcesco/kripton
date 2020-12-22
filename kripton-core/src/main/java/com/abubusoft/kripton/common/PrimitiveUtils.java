/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.common;


/**
 * The Class PrimitiveUtils.
 */
public class PrimitiveUtils {
	
	/**
	 * Read boolean.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the boolean
	 */
	public static Boolean readBoolean(String value, Boolean defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Boolean.valueOf(value);
	}	
	
	/**
	 * Read boolean.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the boolean
	 */
	public static Boolean readBoolean(Boolean value, Boolean defaultValue) {
		return value;
	}	
	
	
	/**
	 * Read byte.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the byte
	 */
	public static Byte readByte(String value, Byte defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Byte.valueOf(value);
	}
	
	/**
	 * Read byte.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the byte
	 */
	public static Byte readByte(int value, Byte defaultValue) {
		return (byte)value;
	}
	
	/**
	 * Read character.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the character
	 */
	public static Character readCharacter(String value, Character defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Character.valueOf((char)Integer.parseInt(value));
	}
	
	/**
	 * Read character.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the character
	 */
	public static Character readCharacter(int value, Character defaultValue) {
		return (char)value;
	}
	
	/**
	 * Read short.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the short
	 */
	public static Short readShort(String value, Short defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Short.valueOf(value);
	}
	
	/**
	 * Read short.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the short
	 */
	public static Short readShort(int value, Short defaultValue) {
		return (short) value;
	}
	
	/**
	 * Read integer.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the integer
	 */
	public static Integer readInteger(String value, Integer defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Integer.valueOf(value);
	}
	
	/**
	 * Read integer.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the integer
	 */
	public static Integer readInteger(int value, Integer defaultValue) {
		return value;
	}
	
	/**
	 * Read long.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the long
	 */
	public static Long readLong(String value, Long defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Long.valueOf(value);
	}
	
	/**
	 * Read long.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the long
	 */
	public static Long readLong(long value, Long defaultValue) {
		return value;
	}
	
	/**
	 * Read float.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the float
	 */
	public static Float readFloat(String value, Float defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Float.valueOf(value);
	}
	
	/**
	 * Read float.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the float
	 */
	public static Float readFloat(Float value, Float defaultValue) {
		return value;
	}
	
	/**
	 * Read double.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the double
	 */
	public static Double readDouble(String value, Double defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Double.valueOf(value);
	}
	
	/**
	 * Read double.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the double
	 */
	public static Double readDouble(double value, Double defaultValue) {
		return value;
	}
	
	/**
	 * Write boolean.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeBoolean(Boolean value) {
		if (value==null) return null;
		
		return Boolean.toString(value);			
	}
	
	/**
	 * Write byte.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeByte(Byte value) {
		if (value==null) return null;
		
		return Byte.toString(value);			
	}
	
	/**
	 * Write character.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeCharacter(Character value) {
		if (value==null) return null;
		
		return Integer.toString((int)value);			
	}
	
	/**
	 * Write short.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeShort(Short value) {
		if (value==null) return null;
		
		return Short.toString(value);			
	}
	
	/**
	 * Write integer.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeInteger(Integer value) {
		if (value==null) return null;
		
		return Integer.toString(value);			
	}
	
	/**
	 * Write long.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeLong(Long value) {
		if (value==null) return null;
		
		return Long.toString(value);			
	}
	
	/**
	 * Write float.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeFloat(Float value) {
		if (value==null) return null;
		
		return Float.toString(value);			
	}
	
	/**
	 * Write double.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String writeDouble(Double value) {
		if (value==null) return null;
		
		return Double.toString(value);			
	}

}
