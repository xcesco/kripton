/**
 * 
 */
package com.abubusoft.kripton;

/**
 * @author xcesco
 *
 */
public interface BindTypeAdapter<J, D> {

	D java2data(J java);
	
	J data2java(D data);
	
	Class<D> getBindType();
	
	Class<J> getJavaType();
}
