package sqlite.feature.livedata.persistence0;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import android.arch.lifecycle.LiveData;
import sqlite.feature.livedata.data.Person;

@BindDao(Person.class)
public interface DaoPerson0 {

	@BindSqlSelect(where="name=${name}")
	//List<Person> select(String name);
	LiveData<List<Person>> select(String name);
	
	@BindSqlInsert
	void insert(Person bean);
	
	@BindSqlUpdate(where ="id=${bean.id}")
	void update(Person bean);
	
}
