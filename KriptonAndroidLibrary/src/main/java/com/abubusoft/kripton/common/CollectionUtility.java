package com.abubusoft.kripton.common;

import java.util.List;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * Utility to work with list and array Json conversion.
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class CollectionUtility {

	public static <E extends List<Byte>> E toList(Byte[] array, Class<E> listType) {
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
	public static <E extends List<Boolean>> E toList(Boolean[] array, Class<E> listType) {
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

	public static <E extends List<Character>> E toList(Character[] array, Class<E> listType) {
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
	public static <E extends List<T>, T> E toList(T[] array, Class<E> listType) {
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

	public static <E extends List<Short>> E toList(Short[] array, Class<E> listType) {
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

	public static <E extends List<Integer>> E toList(Integer[] array, Class<E> listType) {
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

	public static <E extends List<Long>> List<Long> toList(Long[] array, Class<E> listType) {
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

	public static <E extends List<Float>> List<Float> toList(Float[] array, Class<E> listType) {
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

	public static <E extends List<Double>> List<Double> toList(Double[] array, Class<E> listType) {
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

	public static <E extends List<Boolean>> E toList(boolean[] array, Class<E> listType) {
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

	public static <E extends List<Character>> E toList(char[] array, Class<E> listType) {
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

	public static <E extends List<Short>> E toList(short[] array, Class<E> listType) {
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

	public static <E extends List<Integer>> E toList(int[] array, Class<E> listType) {
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

	public static <E extends List<Long>> List<Long> toList(long[] array, Class<E> listType) {
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

	public static <E extends List<Float>> List<Float> toList(float[] array, Class<E> listType) {
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

	public static <E extends List<Double>> List<Double> toList(double[] array, Class<E> listType) {
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

	public static boolean[] toBooleanTypeArray(List<Boolean> input) {
		boolean[] result = new boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static byte[] toByteTypeArray(List<Byte> input) {
		byte[] result = new byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static char[] toCharacterTypeArray(List<Character> input) {
		char[] result = new char[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static short[] toShortTypeArray(List<Short> input) {
		short[] result = new short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static int[] toIntegerTypeArray(List<Integer> input) {
		int[] result = new int[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static long[] toLongTypeArray(List<Long> input) {
		long[] result = new long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static float[] toFloatTypeArray(List<Float> input) {
		float[] result = new float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static double[] toDoubleTypeArray(List<Double> input) {
		double[] result = new double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static String[] toStringArray(List<String> input) {
		String[] result = new String[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Boolean[] toBooleanArray(List<Boolean> input) {
		Boolean[] result = new Boolean[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Byte[] toByteArray(List<Byte> input) {
		Byte[] result = new Byte[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Character[] toCharacterArray(List<Character> input) {
		Character[] result = new Character[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Short[] toShortArray(List<Short> input) {
		Short[] result = new Short[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Integer[] toIntegerArray(List<Integer> input) {
		Integer[] result = new Integer[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Long[] toLongArray(List<Long> input) {
		Long[] result = new Long[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <E>  E[] toArray(List<E> input) {
		return (E[]) input.toArray();
	}
	
	public static Float[] toFloatArray(List<Float> input) {
		Float[] result = new Float[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

	public static Double[] toDoubleArray(List<Double> input) {
		Double[] result = new Double[input.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = input.get(i);
		}

		return result;
	}

}
