package com.abubusoft.kripton.android.sqlite;

import java.util.Collection;

/**
 * Contains utilitity function for split arguments and generate ?.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SpreadUtils {
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(byte[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}

	/**
	 * @param args
	 * @return
	 */
	private static String generateInternal(int length) {
		StringBuilder buffer = new StringBuilder("?");

		for (int i = 1; i < length; i++) {
			buffer.append(", ?");
		}

		return buffer.toString();
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(char[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(short[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(int[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(long[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(float[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(double[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	

	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(E[] args) {
		if (args == null || args.length == 0)
			return "";

		return generateInternal(args.length);
	}
	
	/**
	 * For each element of array, generate a question.
	 * 
	 * @param args
	 * @return
	 */
	public static <E> String generateQuestion(Collection<E> args) {
		if (args == null || args.size() == 0)
			return "";

		return generateInternal(args.size());
	}
		
}
