package com.abubusoft.kripton.processor.sqlite;

import com.abubusoft.kripton.processor.utils.StringUtility;

public class SelectStatementBuilder {

	public static SelectStatementBuilder create()
	{
		return new SelectStatementBuilder();
	}
	
	protected boolean distinct;
	
	protected String where;
	
	protected String fields;
	
	protected String table;
	
	protected String having;
	
	protected String groupBy;
	
	protected String orderBy;
	
	public SelectStatementBuilder distinct(boolean value)
	{
		distinct=value;
		return this;
	}
	
	public SelectStatementBuilder table(String value)
	{
		table=value;
		return this;
	}
	
	public SelectStatementBuilder where(String value)
	{
		where=value;
		return this;
	}
	
	public SelectStatementBuilder fields(String value)
	{
		fields=value;
		return this;
	}
	
	public SelectStatementBuilder having(String value)
	{
		having=value;
		return this;
	}
	
	public SelectStatementBuilder groupBy(String value)
	{
		groupBy=value;
		return this;
	}
	
	public SelectStatementBuilder orderBy(String value)
	{
		groupBy=value;
		return this;
	}
	
	public String build()
	{
		StringBuilder buffer=new StringBuilder();
		
		buffer.append("select ");
		if (distinct) buffer.append("distinct ");
		buffer.append(fields);
		buffer.append(" from "+table);
		if (StringUtility.hasText(where)) buffer.append(" where "+where);
		if (StringUtility.hasText(having)) buffer.append(" having "+having);
		if (StringUtility.hasText(groupBy)) buffer.append(" groupBy "+groupBy);
		if (StringUtility.hasText(orderBy)) buffer.append(" orderBy "+orderBy);
		
		return buffer.toString();
	}
}
