package com.abubusoft.kripton.common;

import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	public static Class<?> getParameterizedType(Field field, GenericClass generics) {
		Class<?> paramClass = null;
		Type genericType = field.getGenericType();

		if (genericType instanceof TypeVariable<?>) {
			TypeVariable<?> myType = (TypeVariable<?>)genericType;
			paramClass = generics.getActualClass(field.getDeclaringClass().getName()+"--"+myType.getName());
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

				}
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
		return List.class == type;
	}

	/**
	 * check if a type is <code>java.util.ArrayList</code> type.
	 * 
	 * @param type
	 *            a type to be checked
	 * @return true or false
	 */
	public static boolean isArrayList(Class<?> type) {
		return ArrayList.class == type;
	}

	/**
	 * check if a type is <code>java.util.Collection</code> type.
	 * 
	 * @param type
	 *            a type to be checked
	 * @return true or false
	 */
	public static boolean collectionAssignable(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}

}
