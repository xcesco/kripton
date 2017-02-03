package sqlite.select.scalar;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlSelect(value="name", orderBy="name")
	String selectAll();	
	
	@BindSqlSelect(value="birthDay", orderBy="name")
	Date selectAll2();	
}