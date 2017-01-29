package sqlite.example02;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlUpdate(where="name=${name}")
	void updateOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlUpdate
	long updateTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	@BindSqlUpdate(excludedFields={"surname"},where="id=${bean.id} and name=${bean.name}")
	void updateThreeExclude(Person bean);
	
	@BindSqlUpdate(where="id=${bean.id} and name=${bean.name}")
	void updateThreeInclude(Person bean);
	
	@BindSqlUpdate(value={"surname"},where="id=${bean.id} and name=${bean.name}")
	void updateThreeIncludeOK(Person bean);
	
	@BindSqlSelect(value={"name"},where="id=${bean.id} and name=${bean.name}")
	List<Person> selectThreeIncludeERR(Person bean);
	
	@BindSqlUpdate(value={"name"},where="id=${bean.id} and name=${bean.name}")
	void updateThreeIncludeERR(Person bean);
	
	@BindSqlUpdate(where="surname=${surname} and name=${nameValue}")
	void updateThreeIncludeERR(String name, String surname, String nameValue);
	
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlInsert
	long insertTwo(String name, String surname, String birthCity, @BindSqlParam("birthDay") Date date);

	@BindSqlInsert(excludedFields = { "name", "surname" })
	void insertThree(Person bean);

	@BindSqlDelete(where = "name=${name} and surname=${surname}")
	void deleteOne(String name, @BindSqlParam("surname") String temp);

	@BindSqlDelete(where = "name=${name} and surname=${surname}")
	long deleteTwo(String name, @BindSqlParam("surname") String temp);

	@BindSqlDelete(where = " id = ${bean.id} ")
	void deleteThree(Person bean);
	
	@BindSqlSelect(orderBy="name")
	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${name} || '%%' ", orderBy="name")
	Set<Person> selectAll(String name);
	
	@BindSqlSelect(orderBy="name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener);
	
	@BindSqlSelect(orderBy="name")
	void selectCursorListener(OnReadCursorListener cursorListener);
	
}