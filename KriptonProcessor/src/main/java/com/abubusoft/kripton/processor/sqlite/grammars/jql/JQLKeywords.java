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
