package sqlite.feature.javadoc.insert.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface InsertBeanPersonDao {

	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry
	@BindSqlInsert
	int insertOneBean(Person bean);

	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "name")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, fields = "name")
	int insertOneBeanFieldName(Person bean);

	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "surname")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, excludedFields = "name")
	int insertOneBeanFieldSurname(Person bean);

	/**
	 * insert BEAN with INSERT-FROM-SELECT is no allowed
	 * 
	 * @param bean
	 * @return
	 */
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (name) SELECT name FROM Person WHERE name=${bean.name}")
	void insertBeanFromSelect(Person bean);

}
