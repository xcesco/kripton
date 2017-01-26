package sqlite.dynamic;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err4DAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity);

	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlOrderBy int name, @BindSqlParam("nameTemp") String nameValue);
}