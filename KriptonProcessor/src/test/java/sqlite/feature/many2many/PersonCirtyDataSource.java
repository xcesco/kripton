package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "com.test")
@BindDataSource(daoSet={PersonDao.class, CityDao.class, PersonCityDao.class}, fileName = "person.db")
public interface PersonCirtyDataSource {

}
