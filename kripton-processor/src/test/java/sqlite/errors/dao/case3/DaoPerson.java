package sqlite.errors.dao.case3;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Person.class)
public interface DaoPerson {
	@BindSqlSelect(where="name=${name}")
	List<Person> select1(String name);

	@BindSqlSelect(where="name=:{name}")
	List<Person> select2(String name);

	@BindSqlSelect(where="name=:name")
	List<Person> select3(String name);
	
	@BindSqlUpdate(where="name=:name")
	void update(String name);
	
	@BindSqlInsert
	void insert(Person bean);
}
