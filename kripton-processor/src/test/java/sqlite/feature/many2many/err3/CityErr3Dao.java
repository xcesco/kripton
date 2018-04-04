package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.City;

@BindDao(City.class)
public interface CityErr3Dao extends BaseDao<City> {

}
