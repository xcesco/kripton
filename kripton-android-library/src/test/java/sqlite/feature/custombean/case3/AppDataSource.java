package sqlite.feature.custombean.case3;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;

//@BindContentProvider(authority = "sqlite.feature.custombean.case1")
@BindDataSourceOptions(inMemory = true)
@BindDataSource(fileName = "app.db", version = 1, daoSet = { BookDao.class})
public interface AppDataSource {

}
