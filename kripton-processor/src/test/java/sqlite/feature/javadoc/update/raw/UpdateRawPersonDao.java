package sqlite.feature.javadoc.update.raw;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
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
	@BindSqlUpdate
	int updateAllBeans(String personName);

	@BindContentProviderEntry(path = "jql/${personSurname}")
	@BindSqlUpdate(jql = "UPDATE perSon SET student = ${student}, PersonnAme=${personName}  where personSurname=${personSurname}")
	void updateAllBeansJQL(@BindSqlParam("personName") String name, @BindSqlParam("personSurname") String surname, boolean student);

	/**
	 * this JQL UPDATE-FROM-SELECT can NOT be used as content provider method.
	 * 
	 * @param bean
	 */
	@BindSqlUpdate(jql = "UPDATE Person SET Personname=${personName}, student = (select student from Person where personSurname=${surname})")
	void updateFromSelectAllBeansJQL(String surname, @BindSqlParam("personName") String name);

	/**
	 * this JQL UPDATE-FROM-SELECT can be used as content provider method.
	 * 
	 * @param bean
	 */
	@BindContentProviderEntry(path = "jql/all/${surname}")
	@BindSqlUpdate(jql = "UPDATE Person SET personname=${personName} where student= (select student from Person where personsurname=${surname})")
	void updateFromSelectJQL(String personName, String surname);

	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "${id}")
	@BindSqlUpdate(where = "id=${id}")
	int updateBean(long id, String personName);

	@BindContentProviderEntry(path = "${id}/more")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamic(@BindSqlDynamicWhere String where, String personName, long id);

	@BindContentProviderEntry(path = "${id}/moreAndMore")
	@BindSqlUpdate(where = "id=${id}")
	int updateBeanDynamicWithArgs(String personName, long id, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}
