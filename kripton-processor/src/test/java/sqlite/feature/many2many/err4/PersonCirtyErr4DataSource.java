package sqlite.feature.many2many.err4;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ PersonErr4Dao.class, CityErr4Dao.class, PersonCityErr4Dao.class}, fileName = "person.db")
public interface PersonCirtyErr4DataSource {

}
