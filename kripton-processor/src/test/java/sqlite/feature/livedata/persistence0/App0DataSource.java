package sqlite.feature.livedata.persistence0;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="app.db", version=1, daoSet={DaoPerson0.class}, rx=true)
public interface App0DataSource {

}
