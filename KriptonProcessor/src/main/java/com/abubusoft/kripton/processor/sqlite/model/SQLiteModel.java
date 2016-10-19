package com.abubusoft.kripton.processor.sqlite.model;

import java.util.ArrayList;
import java.util.List;

public class SQLiteModel {

	protected List<SQLiteDatabaseSchema> schemas = new ArrayList<SQLiteDatabaseSchema>();

	public void schemaAdd(SQLiteDatabaseSchema schema) {
		schemas.add(schema);
	}

	public List<SQLiteDatabaseSchema> getSchemas() {
		return schemas;
	}

	public void schemaClear() {
		schemas.clear();
	}

	public int schemaCount() {
		return schemas.size();
	}
}
