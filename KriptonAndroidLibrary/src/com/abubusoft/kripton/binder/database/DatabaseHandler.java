package com.abubusoft.kripton.binder.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.database.helper.FilterHelper;
import com.abubusoft.kripton.binder.database.helper.SQLHelper;
import com.abubusoft.kripton.binder.database.helper.StatementHelper;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Base for database handler.
 * 
 * @author xcesco
 * @param <D>
 *
 * @param <Q>
 * @param <I>
 */
@SuppressWarnings("rawtypes")
public abstract class DatabaseHandler<S extends DatabaseSchema, C extends Insert, R extends Query, U extends Update, D extends Delete> implements Serializable {
	private static final long serialVersionUID = 6083581270213311588L;
	protected Class<C> createClazz;
	protected Class<D> deleteClazz;
	protected HashMap<Class<?>, String> mapToType;
	protected Class<R> readClazz;
	protected Class<U> updateClazz;

	public D createDelete(DatabaseTable table, DeleteOptions options) {
		D delete = StatementHelper.createStatementAndColumns(table, deleteClazz, options.name, options.fields);		
		if (table.deletes.containsKey(delete.name)) {
			throw new MappingException("Table " + table.name + " already contains a delete named " + delete.name + ".");
		}
		delete.handler = this;
		// filter
		delete.filter = FilterHelper.buildFilter(table, delete, options.where, options.paramsClass);
		// registry delete
		table.deletes.put(delete.name, delete);
		table.lastDelete = delete;
		// launch even
		onCreateDelete(delete);
		return delete;
	}

	public C createInsert(DatabaseTable table, InsertOptions options) {
		C insert = StatementHelper.createStatementAndColumns(table, createClazz, options.name, options.fields);		
		if (table.inserts.containsKey(insert.name)) {
			throw new MappingException("Table " + table.name + " already contains an insert named " + insert.name + ".");
		}
		insert.handler = this;
		table.inserts.put(insert.name, insert);
		table.lastInsert = insert;
		onCreateInsert(insert);
		return insert;
	}

	public R createQuery(DatabaseTable table, QueryOptions options) {
		R query = StatementHelper.createStatementAndColumns(table, readClazz, options.name, options.fields);
		if (table.queries.containsKey(query.name)) {
			throw new MappingException("Table " + table.name + " already contains a query named " + query.name + ".");
		}
		query.handler = this;
		// filter
		query.filter = FilterHelper.buildFilter(table, query, options.where, options.paramsClass);
		// orderPart
		LinkedHashMap<String, DatabaseColumn> field2ColumnMap = table.field2column;
		Filter tempOrder = SQLHelper.createFilterAndFieldNames(options.order);
		if (tempOrder.fieldNames.length > 0)
			throw new MappingException("No param is allowed in in order statement of the query " + query.name + ".");
		query.order = tempOrder.sql;
		query.order = SQLHelper.createSql(query.order, field2ColumnMap);
		// registry query
		table.queries.put(query.name, query);
		table.lastQuery = query;
		// launch event
		onCreateQuery(query);

		return query;
	}

	protected abstract String onDefineCreateTableSQL(S schema, DatabaseTable table);

	public U createUpdate(DatabaseTable table, UpdateOptions options) {
		U update = StatementHelper.createStatementAndColumns(table, updateClazz, options.name, options.fields);
		if (table.updates.containsKey(update.name)) {
			throw new MappingException("Table " + table.name + " already contains an update named " + update.name + ".");
		}
		update.handler = this;
		// create filter part
		update.filter = FilterHelper.buildFilter(table, update, options.where, options.paramsClass);
		// registry udpate
		table.updates.put(update.name, update);
		table.lastUpdate = update;
		// launch event
		onCreateUpdate(update);

		return update;
	}

	protected abstract String onDefineDropTableSQL(S schema, DatabaseTable table);

	@SuppressWarnings("unchecked")
	public D getDelete(DatabaseTable table, String name) {
		return (D) table.deletes.get(name);
	}

	@SuppressWarnings("unchecked")
	public C getInsert(DatabaseTable table, String name) {
		return (C) table.inserts.get(name);
	}

	@SuppressWarnings("unchecked")
	public R getQuery(DatabaseTable table, String name) {
		return (R) table.queries.get(name);
	}

	@SuppressWarnings("unchecked")
	public U getUpdate(DatabaseTable table, String name) {
		return (U) table.updates.get(name);
	}

	public void init(Class<C> createClazz, Class<R> readClazz, Class<U> updateClazz, Class<D> deleteDelete) {
		this.createClazz = createClazz;
		this.readClazz = readClazz;
		this.updateClazz = updateClazz;
		this.deleteClazz = deleteDelete;

		this.mapToType = onDefineFieldToColumnTypeMap(this.mapToType);
	}

	protected abstract String onDefineColumnType(Class<?> fieldType);

	protected abstract HashMap<Class<?>, String> onDefineFieldToColumnTypeMap(HashMap<Class<?>, String> map);

	protected abstract void onCreateDelete(D delete);

	protected abstract void onCreateInsert(C insert);

	protected abstract void onCreateQuery(R query);

	protected abstract void onCreateUpdate(U update);

}
