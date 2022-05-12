/**
 * 
 */
package com.abubusoft.kripton;


import androidx.annotation.Nullable;

/**
 * Allows to manage a field of type J as a field of type D. It's usefully for unsupported type.   
 *  
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 * @param <J>
 * 		field type
 * @param <D>
 * 		data format
 */
public interface TypeAdapter<J, D> {

	/**
	 * Convert a persisted value of type D in type J, used in a Java bean instance.
	 *
	 * @param dataValue the data value
	 * @return the j
	 */
	@Nullable J toJava(@Nullable D dataValue);

	/**
	 * Convert a field value of type J in its persisted version of type D.
	 *
	 * @param javaValue the java value
	 * @return the d
	 */
	@Nullable D toData(@Nullable J javaValue);
		
}
