package sqlite.feature.in;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoCity.class, DaoPerson.class}, fileName="app.db")
public interface AppDataSource {

}
