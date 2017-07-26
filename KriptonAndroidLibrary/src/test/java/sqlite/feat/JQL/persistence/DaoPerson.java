package sqlite.feat.JQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

import sqlite.feat.JQL.entities.Person;

@BindDao(Person.class)
public interface DaoPerson extends DaoBean<Person> {

	//@BindSqlInsert
	//int insert(Person bean);
	//
	// @BindSqlUpdate
	// int update(Person bean);
	//
	// @BindSqlInsert
	// int insert0(byte[] image);
}
