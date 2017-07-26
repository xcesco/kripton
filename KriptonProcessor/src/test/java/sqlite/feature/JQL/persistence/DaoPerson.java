package sqlite.feature.JQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.JQL.entities.Person;

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
