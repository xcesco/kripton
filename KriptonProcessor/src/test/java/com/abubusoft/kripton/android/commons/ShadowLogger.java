package com.abubusoft.kripton.android.commons;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import com.abubusoft.kripton.android.Logger;

@Implements(Logger.class)
public class ShadowLogger {
	
	
	/**
	 * debug
	 * 
	 * @param msg
	 */
	@Implementation
	public static void debug(String msg, Object... args) {
		System.out.print(String.format(msg, args));		
	}

	/**
	 * error
	 * 
	 * @param msg
	 */
	@Implementation
	public static void error(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * verbose
	 * 
	 * @param msg
	 */
	@Implementation
	public static void verbose(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * warn
	 * 
	 * @param msg
	 */
	@Implementation
	public static void warn(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * fatal
	 * 
	 * @param msg
	 */
	@Implementation
	public static void fatal(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * info
	 * 
	 * @param msg
	 */
	@Implementation
	public static void info(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

}
