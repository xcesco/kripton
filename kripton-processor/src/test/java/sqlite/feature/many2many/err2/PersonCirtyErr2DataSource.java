package sqlite.feature.many2many.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ PersonErr2Dao.class, CityErr2Dao.class, PersonCityErr2Dao.class}, fileName = "person.db")
public interface PersonCirtyErr2DataSource {

}
