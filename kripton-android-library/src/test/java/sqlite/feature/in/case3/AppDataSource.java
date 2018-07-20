package sqlite.feature.in.case3;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoCity.class}, fileName="app.db")
public interface AppDataSource {

}
