package sqlite.dynamic.update;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface PersonUpdateDAO {
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	
	@BindSqlDelete(where="id = ${nameValue}")
	void deleteRaw(String nameValue, @BindSqlWhere String where);
	
	@BindSqlDelete(where="id = ${bean.id}")
	void deleteBean(Person bean, @BindSqlWhere String where);	
	
	@BindSqlUpdate(where="id = ${nameValue}")
	void updateRaw(String name, String nameValue, @BindSqlWhere String where);
	
	@BindSqlUpdate(where="id = ${bean.id}")
	void updateBean(Person bean, @BindSqlWhere String where);
	
	
	@BindSqlSelect
	List<Person> selecAll();
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.CONFLICT_IGNORE)
	void insertOne(String name, String surname, String birthCity, Date birthDay);

}