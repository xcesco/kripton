package sqlite.select.scalar;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface Err1PersonDAO {

	@BindSqlSelect(value={"name", "birthDay"}, orderBy="name")
	Date selectAll2();	
}