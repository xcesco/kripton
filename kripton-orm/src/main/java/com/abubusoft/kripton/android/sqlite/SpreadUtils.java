package com.abubusoft.kripton.android.sqlite;

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
	public static <E> String generateQuestion(E[] args) {
		if (args == null || args.length == 0)
			return "";

		StringBuilder buffer = new StringBuilder("?");

		for (int i = 1; i < args.length; i++) {
			buffer.append(", ?");
		}

		return buffer.toString();
	}
}
