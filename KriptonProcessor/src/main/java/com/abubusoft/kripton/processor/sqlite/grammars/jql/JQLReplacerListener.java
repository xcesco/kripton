package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;

public interface JQLReplacerListener {
	
	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onBindParameter(String bindParameterName);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onDynamicSQL(JQLDynamicStatementType dynamicStatement);
	

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onTableName(String tableName);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onColumnName(String columnName);
	
	void onWhereStatementBegin(Where_stmtContext ctx);

	void onWhereStatementEnd(Where_stmtContext ctx);
}