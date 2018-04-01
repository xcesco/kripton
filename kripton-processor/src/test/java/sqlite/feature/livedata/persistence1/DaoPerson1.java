package sqlite.feature.livedata.persistence1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import android.arch.lifecycle.LiveData;
import sqlite.feature.livedata.data.Person;

@BindContentProviderPath(path="persons")
@BindDao(Person.class)
public interface DaoPerson1 {

	@BindContentProviderEntry
	@BindSqlSelect(where="name=${name}")
	//List<Person> select(String name);
	LiveData<List<Person>> select(String name);
	
	@BindContentProviderEntry
	@BindSqlInsert
	void insert(Person bean);
	
	@BindContentProviderEntry
	@BindSqlUpdate(where ="id=${bean.id}")
	void update(Person bean);
	
}
