package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ PersonErr3Dao.class, CityErr3Dao.class, PersonCityErr1Dao.class}, fileName = "person.db")
public interface PersonCirtyErr3DataSource {

}
