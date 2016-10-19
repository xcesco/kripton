package com.abubusoft.kripton.android;

import android.content.Context;

/**
 * Refererrer of android context.
 * 
 * @author xcesco
 *
 */
public class KriptonLibrary {

	public static Context context() { return context; };
	
	private static Context context;

	/**
	 * Method to invoke during application initialization
	 * 
	 * @param contextValue
	 */
	public static void init(Context contextValue) {
		context = contextValue;
	}
	
}
