/**
 * 
 */
package com.abubusoft.kripton;

/**
 * @author xcesco
 *
 */
public interface BindTypeAdapter<J, D> {

	J toJava(D dataValue);

	D toData(J javaValue);
		
}
