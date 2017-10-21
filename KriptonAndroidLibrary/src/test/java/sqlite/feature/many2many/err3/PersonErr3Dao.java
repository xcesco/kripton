package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.Person;

@BindDao(Person.class)
public interface PersonErr3Dao extends BaseDao<Person> {

}
