package sqlite.feature.many2many.err2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;


@BindDao(PersonCityErr2.class)
@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public interface PersonCityErr2Dao {	
	
}
