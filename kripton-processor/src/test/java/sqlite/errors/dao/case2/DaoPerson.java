package sqlite.errors.dao.case2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface DaoPerson {
	
	@BindSqlDelete
	List<String> delete(Person bean);
	
	@BindSqlInsert
	void insert(Person bean);

	@BindSqlSelect
	List<Person> selectAll();
}
