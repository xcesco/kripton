package sqlite.kotlin.hierarchy;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface DaoPerson {

	@BindSqlSelect(where="name=:name")
	List<Person> selectAll(String name);
}
