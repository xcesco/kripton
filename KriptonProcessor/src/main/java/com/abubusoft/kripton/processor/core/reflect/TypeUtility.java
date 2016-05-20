package com.abubusoft.kripton.processor.core.reflect;

import java.lang.reflect.Type;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.processor.core.ModelClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class TypeUtility {
	public static boolean isTypeIncludedIn(TypeName value, Type ... types) {
		for (Type item : types) {
			if (value.equals(typeName(item))) {
				return true;
			}
		}

		return false;
	}
	
	public static boolean isTypePrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE);
	}
	
	public static boolean isTypeWrappedPrimitive(TypeName value) {
		return isTypeIncludedIn(value, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
	}
	
	public static boolean isSameType(TypeName value, String className) {
		return value.toString().equals(className);		
	}
	
	/**
	 * Check if its type is byte[]
	 * 
	 * @param value
	 * @return true if it is byte[]
	 */
	public static boolean isByteArray(TypeName value) {
		return value.toString().equals(Byte.TYPE.getCanonicalName()+"[]");		
	}
	
	/**
	 * Check if its type is String
	 * 
	 * @param value
	 * @return true if it is String
	 */
	public static boolean isString(TypeName value) {
		return value.toString().equals(String.class.getCanonicalName());		
	}
	
	public static boolean isNullable(TypeName value) {
		return !isTypePrimitive(value);		
	}
	
	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param entity
	 * @return true if value is equals to className.
	 */
	public static boolean isEquals(TypeName value, ModelClass<?> entity) {
		return isSameType(value, entity.getName()); 		
	}
	
	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param rawType
	 * @param generic
	 * @return true if value is equals to className.
	 */
	public static boolean isEqualsToGeneric(TypeName value, TypeName rawType, TypeName generic) {
		return isSameType(value, rawType+"<"+generic.toString()+">");		
	}
	
	//com.abubusoft.kripton.android.sqlite.ReadBeanListener<com.abubusoft.kripton.processor.test03.Bean01>
	
	/**
	 * Check if class that is rapresented by value has same name of entity parameter.
	 * 
	 * @param value
	 * @param kindOfParameter
	 * @return true if value is equals to className.
	 */
	public static boolean isEquals(TypeName value, TypeName kindOfParameter) {
		return value.toString().equals(kindOfParameter.toString()); 		
	}
	
	/**
	 * Generate class name
	 * @param packageName
	 * @param className
	 * @return
	 * 		class name generated
	 */
	public static ClassName className(String packageName, String className) {
		return ClassName.get(packageName, className);
	}
	
	/**
	 * Generate class name
	 * @param className
	 * @return
	 * 		class name generated
	 */
	public static ClassName className(String className) {
		return ClassName.get("", className);
	}

	
	/**
	 * Convert a type in a typeName
	 * 
	 * @param type
	 * @return
	 * 		typeName
	 */
	public static TypeName typeName(Type type)
	{
		return TypeName.get(type);
	}
	
	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param typeMirror
	 * @return
	 * 		typeName
	 */
	public static TypeName typeName(TypeMirror typeMirror)
	{
		return TypeName.get(typeMirror);
	}
	
	/**
	 * Convert a TypeMirror in a typeName
	 * 
	 * @param element
	 * @return
	 * 		typeName
	 */
	public static TypeName typeName(Element element)
	{		
		return TypeName.get(element.asType());
	}

	public static boolean isTypeIncludedIn(String clazzName, Class<?> ... set) {
		String a;
		for (Class<?> item: set)
		{
			a=item.getCanonicalName();
			
			if (clazzName.equals(a)) return true;
		}
		
		return false;
		
	}

}
