package sqlite.select.scalarlist;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface Err1PersonDAO {

	@BindSqlSelect(value={"name", "birthDay"}, orderBy="name")
	List<Date> selectAll2();	
}