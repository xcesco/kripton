package sqlite.feature.in.err3;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority="sqlite.feature.in.err2")
@BindDataSource(daoSet={DaoCity.class}, fileName="app.db")
public interface AppDataSource {

}
