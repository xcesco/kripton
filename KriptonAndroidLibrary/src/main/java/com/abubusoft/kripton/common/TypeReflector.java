package com.abubusoft.kripton.common;

import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.exception.MappingException;

/**
 * Type reflection utils.
 * 
 * @author bulldog
 * 
 */
public class TypeReflector {

	// Cache for constructor
	private static Map<Class<?>, Constructor<?>> cache = new ConcurrentHashMap<Class<?>, Constructor<?>>();

	/**
	 * Get parameterized type of a java.util.List field, T of List<T>.
	 * 
	 * @param field
	 *            a java.lang.reflect.Field object
	 * @return parameterized Class type
	 */
	@SuppressWarnings("rawtypes")
	public static Class<?>[] getParameterizedTypeArray(Field field, GenericClass generics) {
		Class<?> paramClass[] = null; 
		Type genericType = field.getGenericType();

		if (genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericType;

			paramClass = new Class<?>[type.getActualTypeArguments().length];

			for (int i = 0; i < paramClass.length; i++) {				
				if (type.getActualTypeArguments()[i] instanceof Class) {
					paramClass[i] = (Class<?>) type.getActualTypeArguments()[i];
				} else if (type.getActualTypeArguments()[i] instanceof TypeVariable) {
					TypeVariable temp = (TypeVariable) type.getActualTypeArguments()[i]; 

					if (temp.getBounds() != null) {
						Class<?> a = (Class<?>) temp.getGenericDeclaration();
						String resolved = a.getName() + "--" + temp.getName();

						paramClass[i] = generics.getActualClass(resolved);
						if (paramClass[i] == null)
							paramClass[i] = (Class<?>) temp.getBounds()[i];
					}
				} else if (type.getActualTypeArguments()[i] instanceof ParameterizedType) {
					// could not include directly
					throw new MappingException("Can't use type " + type.getActualTypeArguments()[i] + " directly as " + " element of map " + field.getName()+". Use a wrapper class.");
				}
				
				if (paramClass[i]==Object.class)
				{
					throw new MappingException("Can't use type " + type.getActualTypeArguments()[i] + " directly as " + " element of map " + field.getName()+". Use a wrapper class.");
				}
			}
		}
		return paramClass;
	}

	/**
	 * Get parameterized type of a java.util.List field, T of List<T>.
	 * 
	 * @param field
	 *            a java.lang.reflect.Field object
	 * @return parameterized Class type
	 */
	public static Class<?> getParameterizedType(Field field, GenericClass generics) {
		Class<?> paramClass = null;
		Type genericType = field.getGenericType();

		if (genericType instanceof TypeVariable<?>) {
			TypeVariable<?> myType = (TypeVariable<?>) genericType;
			paramClass = generics.getActualClass(field.getDeclaringClass().getName() + "--" + myType.getName());
		}

		if (genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericType;
			if (type.getActualTypeArguments().length == 1) {
				if (type.getActualTypeArguments()[0] instanceof Class) {
					paramClass = (Class<?>) type.getActualTypeArguments()[0];
				} else if (type.getActualTypeArguments()[0] instanceof TypeVariable) {
					@SuppressWarnings("rawtypes")
					TypeVariable temp = (TypeVariable) type.getActualTypeArguments()[0];

					if (temp.getBounds() != null) { 
						Class<?> a = (Class<?>) temp.getGenericDeclaration();
						String resolved = a.getName() + "--" + temp.getName();

						paramClass = generics.getActualClass(resolved);
						if (paramClass == null)
							paramClass = (Class<?>) temp.getBounds()[0];
					} else {
					}

				} else if (type.getActualTypeArguments()[0] instanceof ParameterizedType) {
					// could not include directly
					throw new MappingException("Can't use type " + type.getActualTypeArguments()[0] + " directly as " + " element of map " + field.getName()+". Use a wrapper class.");
				}
			} else {
				throw new MappingException("Type of field "+ field.getName()+" is too complex for Kritpon.");
			}
		}
		return paramClass;
	}

	/**
	 * Reflect a constructor of a class.
	 * 
	 * @param type
	 *            a Class type
	 * @return a Constructor object
	 * @throws Exception
	 *             if reflection fails
	 */
	public static Constructor<?> getConstructor(Class<?> type) throws Exception {
		Constructor<?> con = cache.get(type);
		if (con != null)
			return con;
		con = type.getDeclaredConstructor();
		if (!con.isAccessible()) {
			con.setAccessible(true);
		}
		cache.put(type, con);
		return con;
	}

	/**
	 * check if a type is <code>java.util.List</code> type.
	 * 
	 * @param type
	 *            a type to be checked
	 * @return true or false
	 */
	public static boolean isList(Class<?> type) {
		return List.class.isAssignableFrom(type);
	}

	/**
	 * check if a type is <code>java.util.Collection</code> type.
	 * 
	 * @param type
	 *            a type to be checked
	 * @return true if it's a collection
	 */
	public static boolean collectionAssignable(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}

	/**
	 * check if a type is <code>java.util.Set</code> type.
	 * 
	 * @param type
	 *            a type to be checked.
	 * @return true if it's a set
	 */
	public static boolean isSet(Class<?> type) {
		return Set.class.isAssignableFrom(type);
	}

	/**
	 * check if a type is <code>java.util.Map</code> type.
	 * 
	 * @param type
	 *            a type to be checked
	 * @return true if it's a map
	 */
	public static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

}
