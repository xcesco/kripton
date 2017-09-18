/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.BindTypeAdapter;

public interface BindSQLTypeAdapter<J, D> extends BindTypeAdapter<J, D> {

	/**
	 * Convert a field value to its string representation. Usually it's done when you use bean property in where conditions.
	 * 
	 * @param javaValue
	 * @return
	 */
	String toString(J javaValue);
	
}
