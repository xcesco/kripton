package com.abubusoft.kripton.common;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * Utility to work with list and array Json conversion.
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class CollectionUtils {
	
	@SuppressWarnings("unchecked")
	public static <L extends List<E>, E> L toList(byte[] array, Class<L> listType, Class<E> itemType) {
		  L result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((E) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Short> toList(short[] array) {
		return toList(array, ArrayList.class);
	}
	
	public static <E extends List<Short>> E toList(short[] array, Class<E> listType) {
		  E result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((Short) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> toList(int[] array) {
		return toList(array, ArrayList.class);
	}
	
	public static <E extends List<Integer>> E toList(int[] array, Class<E> listType) {
		  E result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((Integer) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	} 
	
	@SuppressWarnings("unchecked")
	public static List<Long> toList(long[] array) {
		return toList(array, ArrayList.class);
	}

	public static <E extends List<Long>> List<Long> toList(long[] array, Class<E> listType) {
		  E result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((Long) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	} 
	
	@SuppressWarnings("unchecked")
	public static List<Float> toList(float[] array) {
		return toList(array, ArrayList.class);
	}
	
	public static <E extends List<Float>> List<Float> toList(float[] array, Class<E> listType) {
		  E result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((Float) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	} 
	
	@SuppressWarnings("unchecked")
	public static List<Double> toList(double[] array) {
		return toList(array, ArrayList.class);
	}
	
	public static <E extends List<Double>> List<Double> toList(double[] array, Class<E> listType) {
		  E result;
		try {
			result = listType.newInstance();
			
			 for (Object item: array)
			 {
				 result.add((Double) item);
			 }
			 
			 return result;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e.getMessage()));
		}
	} 

}
