package sqlite.errors.dao.case1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

@BindDao(Person.class)
public interface DaoPerson {
	
	@BindSqlInsert
	void insert(Person bean);

	List<Person> selectAll();
}
