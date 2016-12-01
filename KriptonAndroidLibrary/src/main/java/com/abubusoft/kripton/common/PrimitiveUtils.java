package com.abubusoft.kripton.common;

public class PrimitiveUtils {
	public static Boolean readBoolean(String value, Boolean defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Boolean.valueOf(value);
	}	
	
	public static Boolean readBoolean(Boolean value, Boolean defaultValue) {
		return value;
	}	
	
	
	public static Byte readByte(String value, Byte defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Byte.valueOf(value);
	}
	
	public static Byte readByte(int value, Byte defaultValue) {
		return (byte)value;
	}
	
	public static Character readCharacter(String value, Character defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Character.valueOf((char)Integer.parseInt(value));
	}
	
	public static Character readCharacter(int value, Character defaultValue) {
		return (char)value;
	}
	
	public static Short readShort(String value, Short defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Short.valueOf(value);
	}
	
	public static Short readShort(int value, Short defaultValue) {
		return (short) value;
	}
	
	public static Integer readInteger(String value, Integer defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Integer.valueOf(value);
	}
	
	public static Integer readInteger(int value, Integer defaultValue) {
		return value;
	}
	
	public static Long readLong(String value, Long defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Long.valueOf(value);
	}
	
	public static Long readLong(long value, Long defaultValue) {
		return value;
	}
	
	public static Float readFloat(String value, Float defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Float.valueOf(value);
	}
	
	public static Float readFloat(Float value, Float defaultValue) {
		return value;
	}
	
	public static Double readDouble(String value, Double defaultValue) {
		if (!StringUtils.hasText(value)) return defaultValue;
		
		return Double.valueOf(value);
	}
	
	public static Double readDouble(double value, Double defaultValue) {
		return value;
	}
	
	public static String writeBoolean(Boolean value) {
		if (value==null) return null;
		
		return Boolean.toString(value);			
	}
	
	public static String writeByte(Byte value) {
		if (value==null) return null;
		
		return Byte.toString(value);			
	}
	
	public static String writeCharacter(Character value) {
		if (value==null) return null;
		
		return Integer.toString((int)value);			
	}
	
	public static String writeShort(Short value) {
		if (value==null) return null;
		
		return Short.toString(value);			
	}
	
	public static String writeInteger(Integer value) {
		if (value==null) return null;
		
		return Integer.toString(value);			
	}
	
	public static String writeLong(Long value) {
		if (value==null) return null;
		
		return Long.toString(value);			
	}
	
	public static String writeFloat(Float value) {
		if (value==null) return null;
		
		return Float.toString(value);			
	}
	
	public static String writeDouble(Double value) {
		if (value==null) return null;
		
		return Double.toString(value);			
	}

}
