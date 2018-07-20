package sqlite.feature.in.case2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoCity.class}, fileName="app.db")
public interface AppDataSource {

}
