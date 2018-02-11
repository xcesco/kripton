package sqlite.feature.many2many.err4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;


@BindDao(PersonCityErr4.class)
@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public class PersonCityErr4Dao {
	
	@BindSqlSelect(where="id=${id}")
	List<PersonCityErr4> selectById(long id) {
		return null;
	}
	
}
