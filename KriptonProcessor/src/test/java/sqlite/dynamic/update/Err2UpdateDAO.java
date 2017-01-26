package sqlite.dynamic.update;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err2UpdateDAO {
	/*
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	*/
	
	@BindSqlUpdate(excludedFields={"id", "name", "surname", "birthCity" ,"birthDay"})
	void update(Person bean);
	
//	@BindSqlSelect(orderBy="name")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}