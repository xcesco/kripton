package sqlite.pagedResult;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.PagedResult;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlSelect(orderBy="name", pageSize=20)
	PagedResult<Person> selectPagedStatic1();
	
	@BindSqlSelect(orderBy="name")
	PagedResult<Person> selectPagedStatic2(@BindSqlPageSize int pageSize);
	
	/*
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(orderBy="name")
	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${nameTemp} || '%'")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlWhere String where, @BindSqlOrderBy String orderBy);
	
	@BindSqlSelect(orderBy="name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);*/
	
}