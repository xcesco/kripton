package com.abubusoft.kripton.database;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.exception.MappingException;

public class AbstractDatabaseSchema<Q extends Query, I extends Insert> {

	interface TableTask {
		String onTable(DatabaseTable item);
	}

	protected static final int CACHE_SIZE = 100;

	// use LRU cache to limit memory consumption.
	protected static Map<String, AbstractDatabaseSchema<?, ?>> schemaCache = Collections.synchronizedMap(new LRUCache<String, AbstractDatabaseSchema<?, ?>>(
			CACHE_SIZE));

	// use LRU cache to limit memory consumption.
	protected LinkedHashMap<String, DatabaseTable> tables = new LinkedHashMap<>();

	public LinkedHashMap<String, DatabaseTable> getTables() {
		return tables;
	}

	protected LinkedHashMap<String, MappingSchema> schemas = new LinkedHashMap<>();

	protected DatabaseHandler<Q, I> handler;

	protected LinkedHashMap<Class<?>, DatabaseTable> class2Table = new LinkedHashMap<>();

	protected AbstractDatabaseSchema(DatabaseHandler<Q, I> handler, DatabaseSchemaOptions options) {
		this.handler = handler;
		handler.init();
		buildTables(handler, options);
	}

	protected void buildTables(DatabaseHandler<Q, I> handler, DatabaseSchemaOptions options) {
		MappingSchema[] array = new MappingSchema[options.mappingSchemaSet.size()];
		array = options.mappingSchemaSet.toArray(array);
		DatabaseTable table;
		DatabaseColumn column;
		String key;
		for (MappingSchema item : array) {
			key = options.tablePrefix + options.nameConverter.convertName(item.tableInfo.name);

			table = new DatabaseTable();
			table.name = key;
			table.schema = item;
			for (ElementSchema element : item.getField2SchemaMapping().values()) {
				column = new DatabaseColumn();

				if (element.getColumnInfo().feature == ColumnType.PRIMARY_KEY) {
					element.getColumnInfo().name = "_id";
				}

				column.name = options.nameConverter.convertName(element.getColumnInfo().name);
				column.feature = element.getColumnInfo().feature;
				column.schema = element;
				column.type = handler.getColumnType(element.getFieldType());

				table.columns.add(column);
				table.field2column.put(element.getName(), column);
			}

			tables.put(key, table);
			class2Table.put(item.getType(), table);

			// create default insert, update and select
			createInsert(item.getType(), InsertOptions.build());
			createQuery(item.getType(), QueryOptions.build());
		}
	}

	public Q createQuery(Class<?> clazz, QueryOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return handler.createQuery(table, options);
	}

	public I createInsert(Class<?> clazz, InsertOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return handler.createInsert(table, options);
	}

	public I getInsert(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (I) table.inserts.get(name);
	}

	protected TableTask iteratorCreateTableSQL = new TableTask() {
		@Override
		public String onTable(DatabaseTable item) {
			return handler.createTableSQL(item);
		}
	};

	protected TableTask iteratorDropTableSQL = new TableTask() {
		@Override
		public String onTable(DatabaseTable item) {
			return handler.dropTableSQL(item);
		}
	};

	public String[] createTablesSQL() {
		return forEachTable(iteratorCreateTableSQL);
	}

	public String[] dropTablesSQL() {
		return forEachTable(iteratorDropTableSQL);
	}

	protected String[] forEachTable(TableTask task) {
		String[] result = new String[tables.size()];
		int i = 0;

		for (DatabaseTable item : tables.values()) {
			result[i] = task.onTable(item);
			i++;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <Q extends Query, I extends Insert> AbstractDatabaseSchema<Q, I> build(String name, DatabaseHandler<Q, I> handler,
			DatabaseSchemaOptions options) {
		if (schemaCache.containsKey(name)) {
			return (AbstractDatabaseSchema<Q, I>) schemaCache.get(name);
		} else {
			AbstractDatabaseSchema<Q, I> dbSchema = new AbstractDatabaseSchema<Q, I>(handler, options);
			schemaCache.put(name, dbSchema);
			return (AbstractDatabaseSchema<Q, I>) dbSchema;
		}
	}
}
