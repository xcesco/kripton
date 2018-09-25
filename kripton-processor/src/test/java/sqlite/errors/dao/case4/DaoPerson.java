package sqlite.errors.dao.case4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Person.class)
public interface DaoPerson {
	
	@BindSqlUpdate()
	void update(String namex);
	
	@BindSqlInsert
	void insert(Person bean);

	@BindSqlSelect
	List<Person> selectAll();
}
