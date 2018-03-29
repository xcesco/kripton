package sqlite.feature.many2many.err;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;


@BindDao(PersonCityErr1.class)
@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public interface PersonCityErr1Dao {
	
	@BindSqlSelect(where="id=${bean.id}")
	List<PersonCityErr1> selectById(PersonCityErr1 bean);
	
}
