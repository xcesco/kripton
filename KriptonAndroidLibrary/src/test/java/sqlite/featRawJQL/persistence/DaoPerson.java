package sqlite.featRawJQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.featRawJQL.entities.Person;

@BindDao(Person.class)
public interface DaoPerson extends DaoBean<Person> {
	
	@BindSqlInsert
	int insert(Person bean);
	
	@BindSqlUpdate
	int update(Person bean);
	
	@BindSqlInsert
	int insert0(byte[] image);
}
