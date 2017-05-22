package sqlite.contentprovider.kripton35.persistence;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import sqlite.contentprovider.kripton35.entities.Person;


@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.CONFLICT_FAIL, includePrimaryKey=true)
	//void insertOne(String name, String surname, String birthCity, Date birthDay);
	void insertOne(Person bean);

	@BindSqlSelect(orderBy = "name")
	List<Person> selectAll();

	@BindSqlSelect(where = "name like ${nameTemp} || '%' and #{where}")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlWhere String where, @BindSqlOrderBy String orderBy);

	@BindSqlSelect(orderBy = "name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);

	// @BindSqlSelect(orderBy="typeName")
	// void selectCursorListener(OnReadCursorListener cursorListener);
}