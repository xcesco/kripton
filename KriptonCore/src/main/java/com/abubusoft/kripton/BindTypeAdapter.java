/**
 * 
 */
package com.abubusoft.kripton;

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
public interface BindTypeAdapter<J, D> {

	/**
	 * Convert a persisted value of type D in type J, used in a Java bean instance
	 * 
	 * @param dataValue
	 * @return
	 * @throws Exception
	 */
	J toJava(D dataValue);

	/**
	 * Convert a field value of type J in its persisted version of type D.
	 * 
	 * @param javaValue
	 * @return
	 * @throws Exception
	 */
	D toData(J javaValue);
		
}
