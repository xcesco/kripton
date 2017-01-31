package sqlite.paginatedResult;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;

@BindDao(Err4Person.class)
public interface Err4PersonDAO {
	
	@BindSqlSelect(orderBy="name", pageSize=20)
	PaginatedResult<Err4Person> selectPagedStatic1();
	
	@BindSqlSelect(where="name=${name}" ,orderBy="name", pageSize=-20)
	PaginatedResult<Err4Person> selectPagedStatic2(String name);
	
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);
/*
	@BindSqlSelect(orderBy="name")
	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${nameTemp} || '%'")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlWhere String where, @BindSqlOrderBy String orderBy);
	
	@BindSqlSelect(orderBy="name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);*/
	
}