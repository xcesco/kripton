package sqlite.adapter.example01;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlSelect(where="birthDay=${birthDay}")
	public List<Person> selectByBirthday(Date birthDay);
}
