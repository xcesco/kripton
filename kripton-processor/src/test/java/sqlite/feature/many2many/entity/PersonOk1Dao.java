package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.Person;

@BindDao(Person.class)
public interface PersonOk1Dao extends BaseDao<Person> {

}
