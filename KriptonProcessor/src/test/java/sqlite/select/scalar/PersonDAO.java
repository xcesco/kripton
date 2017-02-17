package sqlite.select.scalar;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlSelect(value="typeName", orderBy="typeName")
	String selectAll();	
	
	@BindSqlSelect(value="birthDay", orderBy="typeName")
	Date selectAll2();	
}