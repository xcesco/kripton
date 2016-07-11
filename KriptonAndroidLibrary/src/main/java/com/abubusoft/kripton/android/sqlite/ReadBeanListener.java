/**
 * 
 */
package com.abubusoft.kripton.android.sqlite;


/**
 * <p></p>
 * 
 * @author xcesco
 * @param <E>
 *
 *
 * @since 17/mag/2016
 */
public interface ReadBeanListener<E> {

	/**
	 * This event is fired for each row of resultset after cursor values are converted in a bean. The bean is reused.
	 * 
	 * @param bean
	 * 		bean read from database
	 * @param row
	 * 		index of current row 
	 * @param rowCount
	 * 		number of found rows
	 */
	void onRead(E bean, int row, int rowCount);
		
}
