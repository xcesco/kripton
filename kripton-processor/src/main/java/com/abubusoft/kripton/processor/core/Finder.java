/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.core;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface Finder.
 *
 * @param <T> the generic type
 */
public interface Finder<T> {

	/**
	 * Find property by name.
	 *
	 * @param name the name
	 * @return the t
	 */
	T findPropertyByName(String name);

	/**
	 * Gets the collection.
	 *
	 * @return the collection
	 */
	List<T> getCollection();

	/**
	 * Gets the simple name.
	 *
	 * @return the simple name
	 */
	String getSimpleName();
	
	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	String getTableName();
}
