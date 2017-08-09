package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDao {

	/**
	 * delete BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
//	@BindContentProviderEntry(path="${bean.id}")
//	@BindSqlDelete(where="id=${bean.id}")
//	int deleteAllBeans(Person bean);
	
	/**
	 * delete BEAN with some parameters
	 * @param bean
	 */
//	@BindSqlDelete(jql="DELETE FROM Person WHERE name=${bean.name} AND surname=${bean.surname} AND student = 0")
//	void deleteAllBeansJQL(Person bean);
	
	/**
	 * JQL DELETE-FROM-SELECT can not be used as content provider method.
	 * @param bean
	 */
	@BindContentProviderEntry(path="${bean.name}")
	@BindSqlDelete(jql="DELETE FROM Person WHERE name=${bean.name} and surname=${bean.surname} and student = (select student from Person where id=${bean.student})")
	void deleteFromSelectAllBeansJQL(Person bean);
	
	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
//	@BindSqlDelete(where="id=${bean.id}")
//	int deleteBean(Person bean);
//	
//	@BindSqlDelete(where="id=${bean.id}")
//	int deleteBeanDynamic(Person bean, @BindSqlDynamicWhere String where);
//	
//	@BindContentProviderEntry(path="${bean.id}/moreAndMore")
//	@BindSqlDelete(where="id=${bean.id}")
//	int deleteBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args);
	
}
