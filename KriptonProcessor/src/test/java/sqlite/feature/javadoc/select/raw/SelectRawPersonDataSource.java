package sqlite.feature.javadoc.select.raw;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={SelectRawPersonDao.class}, fileName = "person.db")
public interface SelectRawPersonDataSource {

}
