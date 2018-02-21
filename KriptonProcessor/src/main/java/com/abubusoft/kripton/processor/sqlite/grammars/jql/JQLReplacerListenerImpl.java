package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class JQLReplacerListenerImpl implements JQLReplacerListener {

	protected SQLiteModelMethod currentMethod;
	protected SQLDaoDefinition currentDaoDefinition;
	protected SQLiteDatabaseSchema currentSchema;
	protected SQLEntity currentEntity;

	public JQLReplacerListenerImpl(SQLiteModelMethod method) {
		this.currentMethod = method;
		if (method != null) {
			this.currentDaoDefinition = method.getParent();
			this.currentSchema = this.currentDaoDefinition.getParent();
			this.currentEntity = currentDaoDefinition.getEntity();
		}
	}

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
		if (currentSchema != null) {
			Finder<SQLProperty> finder = currentSchema.getEntityBySimpleName(tableName);
			AssertKripton.assertTrueOrUnknownClassInJQLException(finder != null, currentMethod, tableName);
			return finder.getTableName();
		} else {
			return null;
		}
	}

	@Override
	public String onColumnName(String columnName) {
		return null;
	}

	@Override
	public String onColumnNameToUpdate(String columnName) {
		return null;
	}

	@Override
	public void onWhereStatementBegin(Where_stmtContext ctx) {

	}

	@Override
	public void onWhereStatementEnd(Where_stmtContext ctx) {

	}

	@Override
	public void onColumnNameSetBegin(Column_name_setContext ctx) {

	}

	@Override
	public void onColumnNameSetEnd(Column_name_setContext ctx) {

	}

	@Override
	public void onColumnValueSetBegin(Column_value_setContext ctx) {

	}

	@Override
	public void onColumnValueSetEnd(Column_value_setContext ctx) {

	}

	@Override
	public String onColumnFullyQualifiedName(String tableName, String columnName) {
		return JQLReplacerListenerImpl.resolveFullyQualifiedColumnName(currentSchema, currentMethod, tableName, columnName);
	}

	/**
	 * given a fully qualified property name, it will be transformed in
	 * associated column name. If class or property does not exist, an exception
	 * will be thrown
	 * 
	 * @param schema
	 * @param method
	 * @param className
	 * @param propertyName
	 * @return resolved name ex: "person.birth_date"
	 * 
	 */
	public static String resolveFullyQualifiedColumnName(SQLiteDatabaseSchema schema, SQLiteModelMethod method, String className, String propertyName) {
		Finder<SQLProperty> currentEntity = method.getParent().getEntity();
		if (StringUtils.hasText(className)) {
			currentEntity = schema.getEntityBySimpleName(className);
			AssertKripton.assertTrueOrUnknownClassInJQLException(currentEntity != null, method, className);
		}

		SQLProperty currentProperty = currentEntity.findPropertyByName(propertyName);
		AssertKripton.assertTrueOrUnknownPropertyInJQLException(currentProperty != null, method, propertyName);

		return (StringUtils.hasText(className) ? currentEntity.getTableName() + "." : "") + currentProperty.columnName;
	}

}
