package com.abubusoft.kripton.processor.sqlite;

import java.util.HashSet;
import java.util.Set;

public class SqlKeywordsHelper {
	
	private Set<String> words;
	
	private static SqlKeywordsHelper instance;
	
	public static SqlKeywordsHelper getInstance() {
		if (instance==null) {
			instance=new SqlKeywordsHelper();
		}
		
		return instance;
	}
	
	public boolean isKeyword(String value) {
		if (value==null) {
			return false;
		}
		value=value.trim().toUpperCase();
		return words.contains(value);
	}

	private SqlKeywordsHelper() {
		words=new HashSet<>();
		
		words.add("ABORT");
		words.add("ACTION");
		words.add("ADD");
		words.add("AFTER");
		words.add("ALL");
		words.add("ALTER");
		words.add("ANALYZE");
		words.add("AND");
		words.add("AS");
		words.add("ASC");
		words.add("ATTACH");
		words.add("AUTOINCREMENT");
		words.add("BEFORE");
		words.add("BEGIN");
		words.add("BETWEEN");
		words.add("BY");
		words.add("CASCADE");
		words.add("CASE");
		words.add("CAST");
		words.add("CHECK");
		words.add("COLLATE");
		words.add("COLUMN");
		words.add("COMMIT");
		words.add("CONFLICT");
		words.add("CONSTRAINT");
		words.add("CREATE");
		words.add("CROSS");
		words.add("CURRENT");
		words.add("CURRENT_DATE");
		words.add("CURRENT_TIME");
		words.add("CURRENT_TIMESTAMP");
		words.add("DATABASE");
		words.add("DEFAULT");
		words.add("DEFERRABLE");
		words.add("DEFERRED");
		words.add("DELETE");
		words.add("DESC");
		words.add("DETACH");
		words.add("DISTINCT");
		words.add("DO");
		words.add("DROP");
		words.add("EACH");
		words.add("ELSE");
		words.add("END");
		words.add("ESCAPE");
		words.add("EXCEPT");
		words.add("EXCLUDE");
		words.add("EXCLUSIVE");
		words.add("EXISTS");
		words.add("EXPLAIN");
		words.add("FAIL");
		words.add("FILTER");
		words.add("FOLLOWING");
		words.add("FOR");
		words.add("FOREIGN");
		words.add("FROM");
		words.add("FULL");
		words.add("GLOB");
		words.add("GROUP");
		words.add("GROUPS");
		words.add("HAVING");
		words.add("IF");
		words.add("IGNORE");
		words.add("IMMEDIATE");
		words.add("IN");
		words.add("INDEX");
		words.add("INDEXED");
		words.add("INITIALLY");
		words.add("INNER");
		words.add("INSERT");
		words.add("INSTEAD");
		words.add("INTERSECT");
		words.add("INTO");
		words.add("IS");
		words.add("ISNULL");
		words.add("JOIN");
		words.add("KEY");
		words.add("LEFT");
		words.add("LIKE");
		words.add("LIMIT");
		words.add("MATCH");
		words.add("NATURAL");
		words.add("NO");
		words.add("NOT");
		words.add("NOTHING");
		words.add("NOTNULL");
		words.add("NULL");
		words.add("OF");
		words.add("OFFSET");
		words.add("ON");
		words.add("OR");
		words.add("ORDER");
		words.add("OTHERS");
		words.add("OUTER");
		words.add("OVER");
		words.add("PARTITION");
		words.add("PLAN");
		words.add("PRAGMA");
		words.add("PRECEDING");
		words.add("PRIMARY");
		words.add("QUERY");
		words.add("RAISE");
		words.add("RANGE");
		words.add("RECURSIVE");
		words.add("REFERENCES");
		words.add("REGEXP");
		words.add("REINDEX");
		words.add("RELEASE");
		words.add("RENAME");
		words.add("REPLACE");
		words.add("RESTRICT");
		words.add("RIGHT");
		words.add("ROLLBACK");
		words.add("ROW");
		words.add("ROWS");
		words.add("SAVEPOINT");
		words.add("SELECT");
		words.add("SET");
		words.add("TABLE");
		words.add("TEMP");
		words.add("TEMPORARY");
		words.add("THEN");
		words.add("TIES");
		words.add("TO");
		words.add("TRANSACTION");
		words.add("TRIGGER");
		words.add("UNBOUNDED");
		words.add("UNION");
		words.add("UNIQUE");
		words.add("UPDATE");
		words.add("USING");
		words.add("VACUUM");
		words.add("VALUES");
		words.add("VIEW");
		words.add("VIRTUAL");
		words.add("WHEN");
		words.add("WHERE");
		words.add("WINDOW");
		words.add("WITH");
		words.add("WITHOUT");
	}
	
	
	
}
