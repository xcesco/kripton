package sqlite.select.scalarlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.select.Person;

@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlSelect(value="typeName", orderBy="typeName")
	Set<String> selectAll();	
	
	@BindSqlSelect(value="birthDay", orderBy="typeName")
	ArrayList<Date> selectAll2();	
}