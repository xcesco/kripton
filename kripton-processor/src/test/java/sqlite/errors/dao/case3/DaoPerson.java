package sqlite.errors.dao.case3;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Person.class)
public interface DaoPerson {
	
	@BindSqlUpdate(where="name=:name")
	void update(String name);
	
	@BindSqlInsert
	void insert(Person bean);

	@BindSqlSelect
	List<Person> selectAll();
}
