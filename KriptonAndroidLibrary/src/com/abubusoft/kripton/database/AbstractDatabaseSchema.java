package com.abubusoft.kripton.database;

import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;

public abstract class AbstractDatabaseSchema<Q extends Query, I extends Insert, U extends Update, D extends Delete> {

	interface TableTask {
		String onTable(DatabaseTable item);
	}

	// use LRU cache to limit memory consumption.
	protected LinkedHashMap<String, DatabaseTable> tables = new LinkedHashMap<>();

	public LinkedHashMap<String, DatabaseTable> getTables() {
		return tables;
	}

	protected DatabaseHandler<Q, I, U, D> handler;

	protected LinkedHashMap<Class<?>, DatabaseTable> class2Table = new LinkedHashMap<>();

	public void build(DatabaseSchemaOptions options) {
		handler = createHandler(options) ;
		handler.init();
		buildTables(handler, options);
	}

	@SuppressWarnings("rawtypes")
	protected void buildTables(DatabaseHandler handler, DatabaseSchemaOptions options) {
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
			table.clazz=item.getType();
			
			for (ElementSchema element : item.getField2SchemaMapping().values()) {
				column=createColumn(element, options);				
				
				table.columns.add(column);
				table.field2column.put(element.getName(), column);
				
				// look for pk
				if (column.feature==ColumnType.PRIMARY_KEY)
				{
					table.primaryKey=column;
				}
			}

			tables.put(key, table);
			class2Table.put(item.getType(), table);
			

			// create default insert, update and select
			createInsert(item.getType(), InsertOptions.build());
			createQuery(item.getType(), QueryOptions.build());
			
			// only for table with primary key
			if (table.primaryKey!=null)
			{
				createUpdate(item.getType(), UpdateOptions.build().where(table.primaryKey.schema.getName()+"=#{"+table.primaryKey.schema.getName()+"}"));
				//createUpdate(item.getType(), UpdateOptions.build().where());	
			}
		}
	}

	protected abstract DatabaseColumn createColumn(ElementSchema element, DatabaseSchemaOptions options);
	
	protected abstract DatabaseHandler<Q, I, U, D> createHandler(DatabaseSchemaOptions options);

	public DatabaseTable getTableFromClass(Class<?> clazz)
	{
		return class2Table.get(clazz);
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
	
	public U createUpdate(Class<?> clazz, UpdateOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return handler.createUpdate(table, options);
	}
	
	public D createDelete(Class<?> clazz, DeleteOptions options) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return handler.createDelete(table, options);
	}

	/**
	 * Retrieve an insert already defined. 
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public I getInsert(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (I) table.inserts.get(name);
	}

	@SuppressWarnings("unchecked")
	public U getUpdate(Class<?> clazz, String name) {
		DatabaseTable table = this.class2Table.get(clazz);

		if (table == null)
			throw new MappingException("Table for class " + clazz.getName() + " does not exists. Have you included it in db definition?");
		return (U) table.updates.get(name);
	}

	public String[] createTablesSQL() {
		TableTask iteratorCreateTableSQL = new TableTask() {
			@Override
			public String onTable(DatabaseTable item) {
				return handler.createTableSQL(item);
			}
		};
		
		return forEachTable(iteratorCreateTableSQL);
	}

	public String[] dropTablesSQL() {
		TableTask iteratorDropTableSQL = new TableTask() {
			@Override
			public String onTable(DatabaseTable item) {
				return handler.dropTableSQL(item);
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
