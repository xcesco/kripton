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
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

// TODO: Auto-generated Javadoc
/**
 * The Interface JQLContext.
 */
public interface JQLContext {

	/**
	 * get context description. Usefully to contextualize error
	 *
	 * @return the context description
	 */
	String getContextDescription();
	
	String getName();
	
	String getParentName();

	Finder<SQLProperty> findEntityByName(String entityName);
}
