package sqlite.dynamic.select;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err6DAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity);
	
	@BindSqlSelect(where="typeName like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlDynamicWhere String name, @BindSqlDynamicWhere String name2, @BindSqlParam("nameTemp") String nameValue);
}