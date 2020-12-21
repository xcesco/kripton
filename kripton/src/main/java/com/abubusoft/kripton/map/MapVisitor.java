/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package com.abubusoft.kripton.map;

import java.util.List;
import java.util.Map;


/**
 * The Interface MapVisitor.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public interface MapVisitor {
	
	/**
	 * Visit.
	 *
	 * @param name the name
	 * @param map the map
	 */
	public void visit(String name, Map<String, Object> map);

	/**
	 * Visit.
	 *
	 * @param name the name
	 * @param list the list
	 */
	public void visit(String name, List<Object> list);

	/**
	 * Visit.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void visit(String name, String value);
	
}
