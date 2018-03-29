package sqlite.feature.javadoc.insert.raw;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={InsertRawPersonDao.class}, fileName = "person.db")
public interface InsertRawPersonDataSource {

}
