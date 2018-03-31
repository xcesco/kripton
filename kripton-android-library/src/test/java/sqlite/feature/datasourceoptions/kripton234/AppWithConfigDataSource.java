package sqlite.feature.datasourceoptions.kripton234;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;

@BindDataSourceOptions(logEnabled=false)
@BindDataSource(daoSet = { DaoPerson.class }, fileName = "app.db")
public interface AppWithConfigDataSource {

}
