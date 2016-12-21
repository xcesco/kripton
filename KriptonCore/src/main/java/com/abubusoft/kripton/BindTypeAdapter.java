/**
 * 
 */
package com.abubusoft.kripton;

/**
 * Bind Type Adapter.  
 * 
 * @author xcesco
 *
 * @param <J>
 * 		field type
 * @param <D>
 * 		data format
 */
public interface BindTypeAdapter<J, D> {

	J toJava(D dataValue) throws Exception;

	D toData(J javaValue) throws Exception;
		
}
