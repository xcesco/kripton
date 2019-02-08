/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.abubusoft.kripton.android;

import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * Logger used in kripton library.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class Logger {

	/**
	 * generate tag.
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
	 * debug.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void debug(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.d(tag, String.format(msg, args));
			} else {
				Log.d(tag, msg);

			}
		}
	}

	/**
	 * error.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void error(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.e(tag, String.format(msg, args));
			} else {
				Log.e(tag, msg);

			}
		}
	}

	/**
	 * verbose.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void verbose(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.v(tag, String.format(msg, args));
			} else {
				Log.v(tag, msg);

			}
		}
	}

	/**
	 * warn.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void warn(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.w(tag, String.format(msg, args));
			} else {
				Log.w(tag, msg);

			}
		}
	}

	/**
	 * fatal.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void fatal(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.wtf(tag, String.format(msg, args));
			} else {
				Log.wtf(tag, msg);

			}
		}
	}

	/**
	 * info.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void info(String msg, Object... args) {
		String tag = generateTag();
		if (msg!=null && !"".equals(tag)) {
			if (args.length > 0) {
				Log.i(tag, String.format(msg, args));
			} else {
				Log.i(tag, msg);

			}
		}
	}

}
