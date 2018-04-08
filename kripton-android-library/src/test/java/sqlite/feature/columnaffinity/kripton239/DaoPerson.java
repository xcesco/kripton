package sqlite.feature.columnaffinity.kripton239;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface DaoPerson {

	@BindSqlSelect
	List<Person> selectAll();
}
