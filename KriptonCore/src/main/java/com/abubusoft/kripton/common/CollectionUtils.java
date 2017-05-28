package com.abubusoft.kripton.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * Utility to work with list and array Json conversion.
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class CollectionUtils {

	/**
	 * create a string set, with initial values.
	 * 
	 * @param objects
	 * @return
	 */
	public static HashSet<String> newSet(String... objects) {
		HashSet<String> result = new HashSet<String>();

		for (String item : objects) {
			result.add(item);
		}

		return result;
	}

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

	public static boolean[] asBooleanTypeArray(List<Boolean> input) {
		boolean[] result = new boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static byte[] asByteTypeArray(List<Byte> input) {
		byte[] result = new byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static char[] asCharacterTypeArray(List<Character> input) {
		char[] result = new char[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static short[] asShortTypeArray(List<Short> input) {
		short[] result = new short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static int[] asIntegerTypeArray(List<Integer> input) {
		int[] result = new int[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static long[] asLongTypeArray(List<Long> input) {
		long[] result = new long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static float[] asFloatTypeArray(List<Float> input) {
		float[] result = new float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static double[] asDoubleTypeArray(List<Double> input) {
		double[] result = new double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static String[] asStringArray(List<String> input) {
		String[] result = new String[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Boolean[] asBooleanArray(List<Boolean> input) {
		Boolean[] result = new Boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Byte[] asByteArray(List<Byte> input) {
		Byte[] result = new Byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Character[] asCharacterArray(List<Character> input) {
		Character[] result = new Character[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Short[] asShortArray(List<Short> input) {
		Short[] result = new Short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Integer[] asIntegerArray(List<Integer> input) {
		Integer[] result = new Integer[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Long[] asLongArray(List<Long> input) {
		Long[] result = new Long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static <E> E[] asArray(List<E> input, E[] newArray) {
		return input.toArray(newArray);
	}

	public static Float[] asFloatArray(List<Float> input) {
		Float[] result = new Float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Double[] asDoubleArray(List<Double> input) {
		Double[] result = new Double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

}
