package sqlite.feature.javadoc.delete.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface DeleteRawPersonDao {

	/**
	 * delete BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlDelete(where = "id=${id}")
	int deleteOneBean(long id);

	/**
	 * delete BEAN with some parameters
	 * 
	 * @param bean
	 */
	@BindSqlDelete(jql = "DELETE FROM Person WHERE name=${name} and surname=${surname} AND student = 0")
	boolean deleteAllBeansJQL(String name, String surname);

	/**
	 * JQL DELETE-FROM-SELECT can be used as content provider method. The
	 * important thing is params.
	 * 
	 * @param bean
	 */
	@BindContentProviderEntry(path = "a/${surname}/${name}")
	@BindSqlDelete(jql = "DELETE FROM Person WHERE surname=${surname} and student = (select student from Person where name=${name})")
	void deleteFromSelectAllBeansJQL(String name, String surname);

	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "single/${id}")
	@BindSqlDelete(where = "id=${id}")
	long deleteRaw(long id);

	@BindContentProviderEntry(path = "single2/${id}")
	@BindSqlDelete(where = "id=${id}")
	int deleteRawDynamic(long id, @BindSqlDynamicWhere String where);

	@BindContentProviderEntry(path = "${id}/moreAndMore")
	@BindSqlDelete(where = "id=${id}")
	int deleteBeanDynamicWithArgs(long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}
