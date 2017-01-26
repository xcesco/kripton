package sqlite.dynamic;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;

@BindDao(Person.class)
public interface PersonUpdateDAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue);
	
	@BindSqlUpdate
	void update();
	
//	@BindSqlSelect(orderBy="name")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}