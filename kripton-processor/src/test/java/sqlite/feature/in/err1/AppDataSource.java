package sqlite.feature.in.err1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoCity.class}, fileName="app.db")
public interface AppDataSource {

}
