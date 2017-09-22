package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={PersonDao.class, CityDao.class, PersonCityDao.class}, fileName = "person.db")
public interface PersonCirtyDataSource {

}
