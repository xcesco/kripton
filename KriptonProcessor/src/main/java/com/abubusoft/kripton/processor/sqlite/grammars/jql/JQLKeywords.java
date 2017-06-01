/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import java.util.LinkedHashSet;
import java.util.Set;

public class JQLKeywords {

	static final String DELETE_KEYWORD = "DELETE";
	static final String DISTINCT_KEYWORD ="DISTINCT";
	static final String FROM_KEYWORD ="FROM";
	static final String GROUP_BY_KEYWORD ="GROUP BY";
	static final String HAVING_KEYWORD ="HAVING";
	static final String INSERT_KEYWORD = "INSERT";
	static final String INTO_KEYWORD = "INTO";
	static final String LIMIT_KEYWORD ="LIMIT";
	static final String OFFSET_KEYWORD ="OFFSET";
	static final String ORDER_BY_KEYWORD ="ORDER BY";	
	static final String SELECT_KEYWORD = "SELECT";
	static final String SET_KEYWORD = "SET";
	static final String UPDATE_KEYWORD = "UPDATE";
	static final String VALUES_KEYWORD = "VALUES";
	static final String WHERE_KEYWORD = "WHERE";
	
	static final Set<String> keywords=new LinkedHashSet<>();
	
	{
		keywords.add(DELETE_KEYWORD);
		keywords.add(DISTINCT_KEYWORD);
		keywords.add(FROM_KEYWORD);
		keywords.add(GROUP_BY_KEYWORD);
		keywords.add(HAVING_KEYWORD);
		keywords.add(INSERT_KEYWORD);
		keywords.add(INTO_KEYWORD);
		keywords.add(LIMIT_KEYWORD);
		keywords.add(OFFSET_KEYWORD);
		keywords.add(ORDER_BY_KEYWORD);	
		keywords.add(SELECT_KEYWORD);
		keywords.add(SET_KEYWORD);
		keywords.add(UPDATE_KEYWORD);
		keywords.add(VALUES_KEYWORD);
		keywords.add(WHERE_KEYWORD);				
	}
	

}
