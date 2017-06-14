package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;

public class JQLReplacerListenerImpl implements JQLReplacerListener {

	@Override
	public String onBindParameter(String bindParameterName) {
		return null;
	}

	@Override
	public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
		return null;
	}

	@Override
	public String onTableName(String tableName) {
		return null;
	}

	@Override
	public String onColumnName(String columnName) {
		return null;
	}

	@Override
	public void onWhereStatementBegin(Where_stmtContext ctx) {

	}

	@Override
	public void onWhereStatementEnd(Where_stmtContext ctx) {

	}

}
