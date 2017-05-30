package sqlite.select.scalarlist;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface Err1PersonDAO {

	@BindSqlSelect(fields={"typeName", "birthDay"}, orderBy="typeName")
	List<Date> selectAll2();	
}