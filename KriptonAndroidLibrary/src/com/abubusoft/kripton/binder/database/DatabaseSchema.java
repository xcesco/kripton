package com.abubusoft.kripton.binder.database;

import java.util.LinkedHashMap;

import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;

@SuppressWarnings("rawtypes")
public abstract class DatabaseSchema<H extends DatabaseHandler, C extends Insert, R extends Query, U extends Update, D extends Delete> {

	protected Class<C> clazzInsert;
	protected Class<R> clazzQuery;
	protected Class<U> clazzUpdate;
	protected Class<D> clazzDelete;

	interface TableTask {
		String onTable(DatabaseTable item);
	}

	protected LinkedHashMap<String, DatabaseTable> tables = new LinkedHashMap<>();

	public LinkedHashMap<String, DatabaseTable> getTables() {
		return tables;
	}

	public DatabaseTable getTableFromBeanClass(Class<?> clazz) {
		return class2Table.get(clazz);
	}

	protected H handler;

	protected LinkedHashMap<Class<?>, DatabaseTable> class2Table = new LinkedHashMap<>();

	@SuppressWarnings("unchecked")
	public void build(DatabaseSchemaOptions options) {
		clazzInsert = onDefineInsertClass();
		clazzQuery = onDefineQueryClass();
		clazzUpdate = onDefineUpdateClass();
		clazzDelete = onDefineDeleteClass();

		handler = onDefineHandler(options);
		handler.init(this.clazzInsert, this.clazzQuery, this.clazzUpdate, this.clazzDelete);
		buildTables(options);
	}

	protected abstract H onDefineHandler(DatabaseSchemaOptions options);

	protected abstract Class<C> onDefineInsertClass();

	protected abstract Class<R> onDefineQueryClass();

	protected abstract Class<U> onDefineUpdateClass();

	protected abstract Class<D> onDefineDeleteClass();

	protected void buildTables(DatabaseSchemaOptions options) {
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
			table.clazz = item.getType();

			for (ElementSchema element : item.getField2SchemaMapping().values()) {
				column = onDefineColumn(element, options);

				table.columns.add(column);
				table.field2column.put(element.getName(), column);

				// look for pk
				if (column.feature == ColumnType.PRIMARY_KEY) {
					table.primaryKey = column;
				}
			}

			tables.put(key, table);
			class2Table.put(item.getType(), table);

			// create default insert, update and select
			createInsert(item.getType(), InsertOptions.build());
			createQuery(item.getType(), QueryOptions.build().name(Query.QUERY_ALL));

			// only for table with primary key
			if (table.primaryKey != null) {
				String whereById = table.primaryKey.schema.getName() + "=#{" + table.primaryKey.schema.getName() + "}";

				createQuery(item.getType(), QueryOptions.build().name(Query.DEFAULT_BY_ID).where(whereById).paramsClass(table.primaryKey.schema.getFieldType()));
				createUpdate(item.getType(), UpdateOptions.build().name(Update.DEFAULT_BY_ID).where(whereById));
				createDelete(item.getType(),
						DeleteOptions.build().name(Delete.DEFAULT_BY_ID).where(whereById).paramsClass(table.primaryKey.schema.getFieldType()));
			}
		}
	}

	protected abstract DatabaseColumn onDefineColumn(ElementSchema element, DatabaseSchemaOptions options);

	public DatabaseTable getTableFromClass(Class<?> clazz) {
		return class2Table.get(clazz);
	}

	@SuppressWarnings("unchecked")
	public R createQuery(Class<?> clazz, QueryOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (R) handler.createQuery(table, options);
	}

	@SuppressWarnings("unchecked")
	public C createInsert(Class<?> clazz, InsertOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (C) handler.createInsert(table, options);
	}

	@SuppressWarnings("unchecked")
	public U createUpdate(Class<?> clazz, UpdateOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (U) handler.createUpdate(table, options);
	}

	@SuppressWarnings("unchecked")
	public D createDelete(Class<?> clazz, DeleteOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		D delete=(D) handler.createDelete(table, options);
		
		return delete;
	}
	
	/**
	 * Retrieve an insert already defined.
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <I extends Insert> I getInsert(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (I) table.inserts.get(name);
	}

	@SuppressWarnings("unchecked")
	public <I extends Insert> I getInsert(Class<?> clazz) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");

		if (table.inserts.size() != 1) {
			throw new MappingException("Table for class " + clazz.getName() + " does not have one insert. Check insert definitions.");
		}
		return (I) table.lastInsert;
	}

	/**
	 * Retrieve a query already defined.
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <Q extends Query> Q getQuery(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (Q) table.queries.get(name);
	}

	@SuppressWarnings("unchecked")
	public <Q extends Query> Q getQuery(Class<?> clazz) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");

		if (table.queries.size() != 1) {
			throw new MappingException("Table for class " + clazz.getName() + " does not have one query. Check query definitions.");
		}
		return (Q) table.lastQuery;
	}

	/**
	 * Retrieve a query already defined.
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public D getDelete(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (D) table.deletes.get(name);
	}

	@SuppressWarnings("unchecked")
	public D getDelete(Class<?> clazz) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");

		if (table.deletes.size() != 1) {
			throw new MappingException("Table for class " + clazz.getName() + " does not have one delete. Check delete definitions.");
		}
		return (D) table.lastDelete;
	}

	@SuppressWarnings("unchecked")
	public U getUpdate(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (U) table.updates.get(name);
	}

	@SuppressWarnings("unchecked")
	public U getUpdate(Class<?> clazz) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");

		if (table.updates.size() != 1) {
			throw new MappingException("Table for class " + clazz.getName() + " does not have one update. Check update definitions.");
		}
		return (U) table.lastUpdate;
	}

	/**
	 * Create sql for tables
	 * 
	 * @return arrays of sql
	 */
	public String[] createTablesSQL() {
		TableTask iteratorCreateTableSQL = new TableTask() {
			@SuppressWarnings("unchecked")
			@Override
			public String onTable(DatabaseTable item) {
				return handler.onDefineCreateTableSQL(DatabaseSchema.this, item);
			}
		};

		return forEachTable(iteratorCreateTableSQL);
	}

	public String[] dropTablesSQL() {
		TableTask iteratorDropTableSQL = new TableTask() {
			@SuppressWarnings("unchecked")
			@Override
			public String onTable(DatabaseTable item) {
				return handler.onDefineDropTableSQL(DatabaseSchema.this, item);
			}
		};

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

}
