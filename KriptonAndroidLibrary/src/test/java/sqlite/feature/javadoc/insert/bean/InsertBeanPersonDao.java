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
	@BindContentProviderEntry(path="name")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, fields = "name")
	int insertOneBeanFieldName(Person bean);
	
	/**
	 * insert BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="surname")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE, excludedFields = "name")
	int insertOneBeanFieldSurname(Person bean);

//	/**
//	 * delete BEAN with some parameters
//	 * 
//	 * @param bean
//	 */
//	@BindSqlDelete(jql = "DELETE FROM Person WHERE name=${bean.name} AND surname=${bean.surname} AND student = 0")
//	void deleteAllBeansJQL(Person bean);
//
//	/**
//	 * JQL DELETE-FROM-SELECT can be used as content provider method. The
//	 * important thing is params.
//	 * 
//	 * @param bean
//	 */
//	@BindContentProviderEntry(path = "${bean.surname}/${bean.name}")
//	@BindSqlDelete(jql = "DELETE FROM Person WHERE surname=${bean.surname} and student = (select student from Person where name=${bean.name})")
//	void deleteFromSelectAllBeansJQL(Person bean);
//
//	/**
//	 * Update BEAN with one parameter.
//	 * 
//	 * @param bean
//	 * @return
//	 */
//	@BindContentProviderEntry(path = "single/${bean.id}")
//	@BindSqlDelete(where = "id=${bean.id}")
//	int deleteBean(Person bean);
//
//	@BindContentProviderEntry(path = "single2/${bean.id}")
//	@BindSqlDelete(where = "id=${bean.id}")
//	int deleteBeanDynamic(Person bean, @BindSqlDynamicWhere String where);
//
//	@BindContentProviderEntry(path = "${bean.id}/moreAndMore")
//	@BindSqlDelete(where = "id=${bean.id}")
//	int deleteBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where,
//			@BindSqlDynamicWhereArgs String[] args);

}
