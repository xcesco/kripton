package sqlite.feature.javadoc.insert.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface InsertRawPersonDao {

	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry
	@BindSqlInsert()
	int insertOneRaw(String name, String surname);

	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "name")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
	int insertOneRawFieldName(String name);

	/**
	 * insert RAW
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "surname")
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (name) VALUES (${name})")
	int insertOne2RawFieldName(String name);

	/**
	 * insert RAW with parameter. INSERT-FROM-SELECT is not allowed
	 * 
	 * @param bean
	 * @return
	 */
	// @BindContentProviderEntry(path="surname")
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO Person (name) SELECT name FROM Person WHERE name=${name}")
	void insertRawFromSelect(String name);

}
