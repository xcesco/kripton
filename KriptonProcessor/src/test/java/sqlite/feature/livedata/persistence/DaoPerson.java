package sqlite.feature.livedata.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.livedata.data.Person;

@BindDao(Person.class)
public interface DaoPerson {

	@BindSqlSelect(where="name=${name}")
	List<Person> select(String name);
	
}
