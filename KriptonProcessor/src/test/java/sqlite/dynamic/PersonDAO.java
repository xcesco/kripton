package sqlite.dynamic;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

//	@BindSqlSelect(orderBy="name")
//	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlWhere String name, @BindSqlParam("nameTemp") String nameValue);
	
//	@BindSqlSelect(orderBy="name")
//	void selectBeanListener(OnReadBeanListener<Person> beanListener);
	
//	@BindSqlSelect(orderBy="name")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}