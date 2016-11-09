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
public interface OnReadBeanListener<E> {

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
