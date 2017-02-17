package sqlite.select.scalar;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface Err1PersonDAO {

	@BindSqlSelect(value={"typeName", "birthDay"}, orderBy="typeName")
	Date selectAll2();	
}