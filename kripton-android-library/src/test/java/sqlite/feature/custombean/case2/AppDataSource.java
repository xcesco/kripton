package sqlite.feature.custombean.case2;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.sqlite.adapters.Date2LongTypeAdapter;

@BindDataSourceOptions(inMemory = true, populator = DataSourcePopulator.class)
@BindDataSource(fileName = "app.db", version = 1, daoSet = { BookDao.class, LoanDao.class, UserDao.class }, typeAdapters = {
		@BindSqlAdapter(adapter = Date2LongTypeAdapter.class) }, asyncTask = true)
public interface AppDataSource {

}
