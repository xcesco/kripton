package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { CityDao.class, PersonDao.class, City2PersonDao.class }, fileName = "app.db")
public interface AppDataSource {

}
