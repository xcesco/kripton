/**
 * 
 */
package com.abubusoft.kripton.android;

import android.util.Log;

/**
 * Logger used in kripton library
 * 
 * @author Francesco Benincasa
 * 
 */
public class Logger {

	/**
	 * generate tag
	 * 
	 * @return tag of logger
	 */
	protected static String generateTag() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		String currentPath = elements[4].getClassName();
		String method = elements[4].getMethodName();
		int line = elements[4].getLineNumber();

		String tag = currentPath + ", " + method + " (line " + line + ")";
		tag = tag.substring(tag.lastIndexOf(".") + 1);

		return tag;
	}

	/**
	 * debug
	 * 
	 * @param msg
	 */
	public static void debug(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.d(tag, String.format(msg, args));
			} else {
				Log.d(tag, msg);

			}
		}
	}

	/**
	 * error
	 * 
	 * @param msg
	 */
	public static void error(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.e(tag, String.format(msg, args));
			} else {
				Log.e(tag, msg);

			}
		}
	}

	/**
	 * verbose
	 * 
	 * @param msg
	 */
	public static void verbose(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.v(tag, String.format(msg, args));
			} else {
				Log.v(tag, msg);

			}
		}
	}

	/**
	 * warn
	 * 
	 * @param msg
	 */
	public static void warn(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.w(tag, String.format(msg, args));
			} else {
				Log.w(tag, msg);

			}
		}
	}

	/**
	 * fatal
	 * 
	 * @param msg
	 */
	public static void fatal(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.wtf(tag, String.format(msg, args));
			} else {
				Log.wtf(tag, msg);

			}
		}
	}

	/**
	 * info
	 * 
	 * @param msg
	 */
	public static void info(String msg, Object... args) {
		String tag = generateTag();
		if (!"".equals(tag)) {
			if (args.length > 0) {
				Log.i(tag, String.format(msg, args));
			} else {
				Log.i(tag, msg);

			}
		}
	}

}
