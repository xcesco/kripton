package sqlite.feature.many2many.err;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.City;

@BindDao(City.class)
public interface CityErr1Dao extends BaseDao<City> {

}
