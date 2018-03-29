package sqlite.feature.javadoc.select.raw;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry.MultiplicityResultType;
import com.abubusoft.kripton.android.orm.OnReadBeanListener;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface SelectRawPersonDao {

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
	@BindContentProviderEntry(path="a")
	@BindSqlSelect(fields="count(*)")
	int selectAllBeansCount();
	
	/**
	 * select BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="${id}", multiplicityResult=MultiplicityResultType.ONE)
	@BindSqlSelect(where="id=${id}")
	Person selectOneBean(@BindSqlParam("id") long id);
	
	/**
	 * select BEAN with one parameter and dynamic where
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamic/${id}")
	@BindSqlSelect(fields="personName",where="id=${id}")
	Person selectOneBeanWithDynamic(long id, @BindSqlDynamicWhere String where);
	
	/**
	 * select BEAN with one parameter and dynamic where and args
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicandArgs/${id}/${name}")
	@BindSqlSelect(where="id=${id} and personName=${name}")
	Person selectOneBeanWithDynamicAndArgs(long id, String name, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);
	
	/**
	 * select BEAN with one parameter and dynamic order
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicOrder/${id}")
	@BindSqlSelect(where="id=${id}")
	Person selectOneBeanWithDynamicOrder(long id, @BindSqlDynamicOrderBy String order);
	
	
	/**
	 * select BEAN with one parameter and dynamic order
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="dynamicOrderAndLis/${surname}")
	@BindSqlSelect(where="personSurname=${surname}")
	void selectOneBeanWithDynamicOrderAndListener(String surname, @BindSqlDynamicOrderBy String order, OnReadBeanListener<Person> listener);
	
	/**
	 * select BEAN with one parameter and dynamic order
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path="jql/${id}")
	@BindSqlSelect(jql="select * from Person where id=${id}")
	Person selectWithJQL(long id);
	
}
