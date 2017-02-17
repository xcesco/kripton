package sqlite.dynamic.update;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err1UpdateDAO {
	/*
	@BindSqlInsert
	void insertOne(String typeName, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(where="typeName like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	*/
	@BindSqlUpdate
	void update();
	
//	@BindSqlSelect(orderBy="typeName")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}