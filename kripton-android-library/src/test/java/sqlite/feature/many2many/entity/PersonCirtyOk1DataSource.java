package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ PersonOk1Dao.class, CityOk1Dao.class, PersonCityOk1Dao.class}, fileName = "person.db")
public interface PersonCirtyOk1DataSource {

}
