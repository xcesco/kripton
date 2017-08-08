package sqlite.feature.javadoc.select.bean;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

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
	@BindContentProviderEntry
	@BindSqlSelect
	List<Person> selectAllBeans();
	
	@BindContentProviderEntry(path="jql/${bean.name}/${bean.surname}")
	@BindSqlSelect(jql="select * FROM Person WHERE name=${bean.name} and surname=${bean.surname} and student = 0")
	List<Person> selectAllBeansJQL(Person bean);
	
	/**
	 * JQL SELECT-FROM-SELECT can not be used as content provider method.
	 * @param bean
	 */
	@BindSqlSelect(jql="select * FROM Person WHERE name=${bean.name} and surname=${bean.surname} and student = (select student from Person where id=${bean.student})")
	List<Person> selectFromSelectAllBeansJQL(Person bean);
	
	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="${bean.id}")
	@BindSqlSelect(where="id=${bean.id}")
	List<Person> selectBean(Person bean);
	
	@BindContentProviderEntry(path="${bean.id}/more")
	@BindSqlSelect(where="id=${bean.id}")
	List<Person> selectBeanDynamic(Person bean, @BindSqlDynamicWhere String where);
	
	@BindContentProviderEntry(path="${bean.id}/moreAndMore")
	@BindSqlSelect(where="id=${bean.id}")
	List<Person> selectBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args);
	
}
