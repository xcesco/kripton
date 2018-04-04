package sqlite.feature.javadoc.update.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface UpdateBeanPersonDao {

	/**
	 * Update BEAN with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry
	@BindSqlUpdate
	int updateAllBeans(Person bean);

	/**
	 * Update BEAN with one parameter.
	 *
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBean(Person bean);

	/**
	 * Update BEAN with one parameter and dynamic where
	 *
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "dynamic/${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBeanWithDynamic(Person bean, @BindSqlDynamicWhere String where);

	//
	/**
	 * Update BEAN with one parameter and dynamic where
	 *
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "dynamicArgs/${bean.id}")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateOneBeanWithDynamicAndArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String args[]);

	@BindContentProviderEntry(path = "jql")
	@BindSqlUpdate(jql = "UPDATE Person SET personSurname=${bean.personSurname}, student = ${bean.student}")
	void updateAllBeansJQL(Person bean);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 * 
	 * @param bean
	 */
	@BindSqlUpdate(jql = "UPDATE Person SET PersonName=${bean.personName}, student = (select student from Person where id=0)")
	void updateFromSelectAllBeansJQL(Person bean);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 * 
	 * @param bean
	 */
	@BindContentProviderEntry(path = "jql/one/b/${bean.id}")
	@BindSqlUpdate(jql = "UPDATE Person SET personName=${bean.personname} where student = (select student from Person where id=${bean.id})")
	int updateFromSelectJQL(Person bean);

	@BindContentProviderEntry(path = "${bean.id}/more")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateBeanDynamic(Person bean, @BindSqlDynamicWhere String where);

	@BindContentProviderEntry(path = "${bean.id}/moreAndMore")
	@BindSqlUpdate(where = "id=${bean.id}")
	int updateBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}
