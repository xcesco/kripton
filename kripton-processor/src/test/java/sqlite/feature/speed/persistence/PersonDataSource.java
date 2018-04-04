package sqlite.feature.speed.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={PersonDao.class}, fileName = "person.db",log=false)
public interface PersonDataSource {

}
