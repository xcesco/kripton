package sqlite.feature.javadoc.delete.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDao {

	/**
	 * Update RAW with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	//@BindContentProviderEntry
	@BindSqlDelete
	int updateAllBeans(String name);
	
	@BindContentProviderEntry(path="jql")
	@BindSqlUpdate(jql="UPDATE Person SET name=${name}, surname=${surname}, student = ${student}")
	void updateAllBeansJQL(String name, String surname, boolean student);
	
	/**
	 * JQL UPDATE-FROM-SELECT can not be used as content provider method.
	 * @param bean
	 */
	@BindSqlUpdate(jql="UPDATE Person SET name=${name}, surname=${surname}, student = (select student from Person where id=${student})")
	void updateFromSelectAllBeansJQL(String name, String surname, boolean student);
	
	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="${id}")
	@BindSqlUpdate(where="id=${id}")
	int updateBean(String name, long id);
	
	@BindContentProviderEntry(path="${id}/more")
	@BindSqlUpdate(where="id=${id}")
	int updateBeanDynamic(String name, long id, @BindSqlDynamicWhere String where);
	
	@BindContentProviderEntry(path="${id}/moreAndMore")
	@BindSqlUpdate(where="id=${id}")
	int updateBeanDynamicWithArgs(String name, long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args);
	
}
