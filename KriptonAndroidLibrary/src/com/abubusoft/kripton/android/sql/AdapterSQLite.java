package com.abubusoft.kripton.android.sql;

import java.util.Map;
import java.util.Map.Entry;

import com.abubusoft.kripton.binder.annotation.schema.AbstractSchema;
import com.abubusoft.kripton.binder.annotation.schema.MappingSchema;
import com.abubusoft.kripton.binder.annotation.schema.RootElementSchema;
import com.abubusoft.kripton.binder.exception.MappingException;

/**
 * @author xcesco
 *
 */
public class AdapterSQLite extends AbstractAdapter {

	private static final long serialVersionUID = -2710287028065338687L;
	
	public SQLSelect generateQuery(String name, Object bean, String fields) throws MappingException
	{
		MappingSchema ms = MappingSchema.fromObject(bean);
		RootElementSchema res = ms.getRootElementSchema();
		String rootName = res.getName();
		Map<String, Object> fieldMap = ms.getField2SchemaMapping();
		
		SQLSelect select=new SQLSelect();
		int columns = ms.getField2SchemaMapping().size();
		int i = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for (Entry<String, Object> item : fieldMap.entrySet()) {

			if (item.getValue() instanceof AbstractSchema) {
				AbstractSchema es = (AbstractSchema) item.getValue();				
				sb.append(" " + toDbName(es.getName())+(i < columns - 1 ? " ," : ""));
				
				select.addField(es.getName(), toDbName(es.getName()));
			}
			i++;
		}
		sb.append(" FROM ");
		sb.append(toDbName(rootName));
		sb.append(";");
		
		select.sql=sb.toString();
		
		select.build();
		
		return select;
	}

	public String generateCreateTable(Object bean) throws MappingException {
		MappingSchema ms = MappingSchema.fromObject(bean);
		RootElementSchema res = ms.getRootElementSchema();

		String rootName = res.getName();

		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		sb.append(toDbName(rootName));
		sb.append(" (");

		int columns = ms.getField2SchemaMapping().size();
		int i = 0;

		for (Entry<String, Object> item : ms.getField2SchemaMapping().entrySet()) {

			if (item.getValue() instanceof AbstractSchema) {
				AbstractSchema es = (AbstractSchema) item.getValue();
				sb.append(" " + toDbName(es.getName()));
				sb.append(" " + database.class2columnType(es.getField())+ unique(es.isUnique()) + nullable(es.isNullable()) + primaryKey(es.isPrimaryKey())
						+ (i < columns - 1 ? " ," : ""));
			}
			i++;
		}

		sb.append(" );");

		return sb.toString();
	}

	private String unique(boolean value) {
		return value ? " UNIQUE" : "";
	}

	private String nullable(boolean value) {
		return value ? "" : " NOT NULL";
	}

	private String primaryKey(boolean value) {
		return value ? " PRIMARY KEY" : "";
	}

	public String generateDropTable(Object bean) throws MappingException {
		MappingSchema ms = MappingSchema.fromObject(bean);
		RootElementSchema res = ms.getRootElementSchema();
		String rootName = res.getName();

		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE IF EXISTS ");
		sb.append(toDbName(rootName));
		sb.append(";");

		return sb.toString();

	}

}
