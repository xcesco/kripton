package sqlite.feature.many2many.err1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ PersonErr1Dao.class, CityErr1Dao.class, PersonCityErr1Dao.class}, fileName = "person.db")
public interface PersonCirtyErr1DataSource {

}
