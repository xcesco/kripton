package sqlite.indexes;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

@BindDao(Err1Person.class)
public interface Err1PersonDAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(orderBy="typeName")
	List<Person> selectAll();
	
	@BindSqlSelect(where="typeName like ${nameTemp} || '%'")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlDynamicWhere String where, @BindSqlOrderBy String orderBy);
	
	@BindSqlSelect(orderBy="typeName")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);
	
//	@BindSqlSelect(orderBy="typeName")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}