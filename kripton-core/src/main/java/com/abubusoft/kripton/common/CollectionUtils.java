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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

// TODO: Auto-generated Javadoc
/**
 * Utility to work with list and array Json conversion.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class CollectionUtils {

	/**
	 * create a collection set, with initial values.
	 *
	 * @param <T> the generic type
	 * @param itemType the item type
	 * @param objects the objects
	 * @return the sets the
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> asSet(Class<T> itemType, T... objects) {
		LinkedHashSet<T> result = new LinkedHashSet<T>();

		for (T item : objects) {
			result.add(item);
		}

		return result;
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Byte>> E asList(Byte[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Byte) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	//
	public static <E extends List<Boolean>> E asList(Boolean[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Boolean) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Character>> E asList(Character[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Character) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param <T> the generic type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	@SuppressWarnings("unchecked")
	public static <E extends List<T>, T> E asList(T[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((T) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Short>> E asList(Short[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Short) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Integer>> E asList(Integer[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Integer) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Long>> List<Long> asList(Long[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Long) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Float>> List<Float> asList(Float[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Float) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Double>> List<Double> asList(Double[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((Double) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	//

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Boolean>> E asList(boolean[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((boolean) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends Collection<Character>> E asList(char[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((char) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Short>> E asList(short[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((short) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the e
	 */
	public static <E extends List<Integer>> E asList(int[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((int) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Long>> List<Long> asList(long[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((long) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Float>> List<Float> asList(float[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((float) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As list.
	 *
	 * @param <E> the element type
	 * @param array the array
	 * @param listType the list type
	 * @return the list
	 */
	public static <E extends List<Double>> List<Double> asList(double[] array, Class<E> listType) {
		E result;
		try {
			result = listType.newInstance();

			for (Object item : array) {
				result.add((double) item);
			}

			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * As boolean type array.
	 *
	 * @param input the input
	 * @return the boolean[]
	 */
	public static boolean[] asBooleanTypeArray(List<Boolean> input) {
		boolean[] result = new boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As byte type array.
	 *
	 * @param input the input
	 * @return the byte[]
	 */
	public static byte[] asByteTypeArray(List<Byte> input) {
		byte[] result = new byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As character type array.
	 *
	 * @param input the input
	 * @return the char[]
	 */
	public static char[] asCharacterTypeArray(List<Character> input) {
		char[] result = new char[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As short type array.
	 *
	 * @param input the input
	 * @return the short[]
	 */
	public static short[] asShortTypeArray(List<Short> input) {
		short[] result = new short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As integer type array.
	 *
	 * @param input the input
	 * @return the int[]
	 */
	public static int[] asIntegerTypeArray(List<Integer> input) {
		int[] result = new int[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As long type array.
	 *
	 * @param input the input
	 * @return the long[]
	 */
	public static long[] asLongTypeArray(List<Long> input) {
		long[] result = new long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As float type array.
	 *
	 * @param input the input
	 * @return the float[]
	 */
	public static float[] asFloatTypeArray(List<Float> input) {
		float[] result = new float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As double type array.
	 *
	 * @param input the input
	 * @return the double[]
	 */
	public static double[] asDoubleTypeArray(List<Double> input) {
		double[] result = new double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As string array.
	 *
	 * @param input the input
	 * @return the string[]
	 */
	public static String[] asStringArray(List<String> input) {
		String[] result = new String[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As boolean array.
	 *
	 * @param input the input
	 * @return the boolean[]
	 */
	public static Boolean[] asBooleanArray(List<Boolean> input) {
		Boolean[] result = new Boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As byte array.
	 *
	 * @param input the input
	 * @return the byte[]
	 */
	public static Byte[] asByteArray(List<Byte> input) {
		Byte[] result = new Byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As character array.
	 *
	 * @param input the input
	 * @return the character[]
	 */
	public static Character[] asCharacterArray(List<Character> input) {
		Character[] result = new Character[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As short array.
	 *
	 * @param input the input
	 * @return the short[]
	 */
	public static Short[] asShortArray(List<Short> input) {
		Short[] result = new Short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As integer array.
	 *
	 * @param input the input
	 * @return the integer[]
	 */
	public static Integer[] asIntegerArray(List<Integer> input) {
		Integer[] result = new Integer[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As long array.
	 *
	 * @param input the input
	 * @return the long[]
	 */
	public static Long[] asLongArray(List<Long> input) {
		Long[] result = new Long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As array.
	 *
	 * @param <E> the element type
	 * @param input the input
	 * @param newArray the new array
	 * @return the e[]
	 */
	public static <E> E[] asArray(List<E> input, E[] newArray) {
		return input.toArray(newArray);
	}

	/**
	 * As float array.
	 *
	 * @param input the input
	 * @return the float[]
	 */
	public static Float[] asFloatArray(List<Float> input) {
		Float[] result = new Float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * As double array.
	 *
	 * @param input the input
	 * @return the double[]
	 */
	public static Double[] asDoubleArray(List<Double> input) {
		Double[] result = new Double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	/**
	 * trim each element of lists.
	 *
	 * @param value the value
	 */
	public static void trim(List<String> value) {
		if (value==null) return;
		for (int i=0; i<value.size(); i++) {
			value.set(i, value.get(i).trim());
		}
		
	}

}
