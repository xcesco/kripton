package sqlite.feature.livedata.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="app.db", version=1, daoSet={DaoPerson.class})
public interface AppDataSource {

}
