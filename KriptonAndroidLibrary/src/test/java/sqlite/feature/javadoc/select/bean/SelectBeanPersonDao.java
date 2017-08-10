package sqlite.feature.javadoc.select.bean;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry.MultiplicityResultType;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface SelectBeanPersonDao {

	/**
	 * select BEAN with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry
	@BindSqlSelect
	List<Person> selectAllBeans();
	
	/**
	 * select BEAN with no parameters.
	 * 
	 * @param bean
	 * @return
	 */
	
	@BindSqlSelect(fields="count(*)")
	int selectAllBeansCount();
	
	/**
	 * select BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="${bean.id}", multiplicityResult=MultiplicityResultType.ONE)
	@BindSqlSelect(where="id=${bean.id}")
	Person selectOneBean(Person bean);
	
	/**
	 * select BEAN with one parameter and dynamic where
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamic/${bean.id}")
	@BindSqlSelect(where="id=${bean.id}")
	Person selectOneBeanWithDynamic(Person bean, @BindSqlDynamicWhere String where);
	
	/**
	 * select BEAN with one parameter and dynamic where and args
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicandArgs/${bean.id}")
	@BindSqlSelect(where="id=${bean.id}")
	Person selectOneBeanWithDynamicAndArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args);
	
	/**
	 * select BEAN with one parameter and dynamic order
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicOrder/${bean.id}")
	@BindSqlSelect(where="id=${bean.id}")
	Person selectOneBeanWithDynamicOrder(Person bean, @BindSqlDynamicOrderBy String order);
	
	
	/**
	 * select BEAN with one parameter and dynamic order
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicOrderAndLis/${bean.id}")
	@BindSqlSelect(where="id=${bean.id}")
	void selectOneBeanWithDynamicOrderAndListener(Person bean, @BindSqlDynamicOrderBy String order, OnReadBeanListener<Person> listener);
	
//	/**
//	 * Update BEAN with one parameter and dynamic where
//	 * 
//	 * @param bean
//	 * @return
//	 */
//	@BindContentProviderEntry(path="dynamicArgs/${bean.id}")
//	@BindSqlUpdate(where="id=${bean.id}")
//	int updateOneBeanWithDynamicAndArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String args[]);
//	
//	@BindContentProviderEntry(path="jql")
//	@BindSqlUpdate(jql="UPDATE Person SET name=${bean.name}, surname=${bean.surname}, student = ${bean.student}")
//	void updateAllBeansJQL(Person bean);
//	
//	/**
//	 * JQL UPDATE-FROM-SELECT can not be used as content provider method.
//	 * @param bean
//	 */
//	@BindSqlUpdate(jql="UPDATE Person SET name=${bean.name}, surname=${bean.surname}, student = (select student from Person where id=${bean.student})")
//	void updateFromSelectAllBeansJQL(Person bean);	
//	
//	@BindContentProviderEntry(path="${bean.id}/more")
//	@BindSqlUpdate(where="id=${bean.id}")
//	int updateBeanDynamic(Person bean, @BindSqlDynamicWhere String where);
//	
//	@BindContentProviderEntry(path="${bean.id}/moreAndMore")
//	@BindSqlUpdate(where="id=${bean.id}")
//	int updateBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereArgs String[] args);
//	
}
