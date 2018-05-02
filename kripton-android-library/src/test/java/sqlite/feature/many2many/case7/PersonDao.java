package sqlite.feature.many2many.case7;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface PersonDao {

	@BindSqlInsert
	public void insert(Person bean);
	
	@BindSqlSelect
	public List<Person> listPersons();
}
