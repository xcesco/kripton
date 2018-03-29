package sqlite.feature.speed.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.speed.model.Person;

@BindDao(Person.class)
public interface PersonDao {

	@BindSqlSelect
	List<Person> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	Person selectById(long id);
	
	@BindSqlInsert
	Person insert(Person bean);
	
	@BindSqlUpdate(where="id=${bean.id}")
	long update(Person bean);
	
	@BindSqlDelete(where="id=${bean.id}")
	long delete(Person bean);
}
