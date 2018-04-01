package sqlite.feature.many2many.err4;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.BaseDao;
import sqlite.feature.many2many.City;

@BindDao(City.class)
public interface CityErr4Dao extends BaseDao<City> {

}
