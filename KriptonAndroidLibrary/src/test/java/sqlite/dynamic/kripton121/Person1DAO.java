package sqlite.dynamic.kripton121;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Person1DAO {

	@BindSqlSelect
	List<Person> selectOne(@BindSqlWhere String where, @BindSqlOrderBy String orderBy);
	


	// @BindSqlSelect(orderBy="typeName")
	// void selectCursorListener(OnReadCursorListener cursorListener);
}