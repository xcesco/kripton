package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.City;

@BindDao(City.class)
public interface CityOk1Dao extends BaseDao<City> {

}
