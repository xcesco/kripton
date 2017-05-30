package sqlite.contentprovider.kripton35.persistence;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere.PrependType;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import sqlite.contentprovider.kripton35.entities.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDAO {

	@BindContentProviderEntry
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_FAIL, includePrimaryKey = false)
	void insertOne(Person bean);
	
	@BindContentProviderEntry(path="test")
	@BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.CONFLICT_ABORT)
	void insertTwo(Date birthDay);
	
	@BindContentProviderEntry(path="test1")
	@BindSqlInsert
	void insertTwo(String name, String surname, String birthCity, Date birthDay);
	
	@BindContentProviderEntry(path="${id}/dummy/${birthCity}")
	@BindSqlDelete(where="id = ${id} and birthCity= ${birthCity}")
	void delete(long id, Date birthCity);
	
//
//	@BindContentProviderEntry(path = "#")
//	@BindSqlUpdate(where = "id=${id}")
//	int updateWhereStaticAndDynamic(@BindSqlParam("birthCity") String dummy, long id, @BindSqlWhere String where);
//
//	@BindContentProviderEntry
//	@BindSqlSelect(where = "name like ${nameTemp} || '%'", groupBy = "id", having = "id=2", orderBy = "id")
//	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlPageSize int pageSize, @BindSqlWhere(prepend = PrependType.OR) String where, @BindSqlOrderBy String orderBy);
//
//	@BindSqlSelect(orderBy = "name")
//	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);
//
//	@BindContentProviderEntry(path = "#")
//	@BindSqlDelete(where = "id = ${bean.id}")
//	void deleteBean(Person bean, @BindSqlWhere String where);

	// @BindSqlSelect(orderBy="typeName")
	// void selectCursorListener(OnReadCursorListener cursorListener);
}