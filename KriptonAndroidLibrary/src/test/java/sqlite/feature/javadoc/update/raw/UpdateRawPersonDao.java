package sqlite.feature.javadoc.update.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface UpdateRawPersonDao {

	/**
	 * Update RAW with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	// @BindContentProviderEntry
	@BindSqlUpdate
	int updateAllBeans(String name);

	@BindContentProviderEntry(path = "jql/${surname}")
	@BindSqlUpdate(jql = "UPDATE perSon SET student = ${student}, nAme=${name}  where surname=${surname}")
	void updateAllBeansJQL(String name, String surname, boolean student);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 * 
	 * @param bean
	 */
	// @BindContentProviderEntry(path = "jql/b/${surname}")
	@BindSqlUpdate(jql = "UPDATE Person SET name=${name}, student = (select student from Person where surname=${surname})")
	void updateFromSelectAllBeansJQL(String name, String surname);

	/**
	 * this JQL UPDATE-FROM-SELECT can be used as content provider method.
	 * 
	 * @param bean
	 */
	@BindContentProviderEntry(path = "jql/all/${surname}")
	@BindSqlUpdate(jql = "UPDATE Person SET name=${name} where student= (select student from Person where surname=${surname})")
	void updateFromSelectJQL(String name, String surname);

	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateBean(String name, long id);

	@BindContentProviderEntry(path = "${id}/more")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamic(String name, long id, @BindSqlDynamicWhere String where);

	@BindContentProviderEntry(path = "${id}/moreAndMore")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamicWithArgs(String name, long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}
