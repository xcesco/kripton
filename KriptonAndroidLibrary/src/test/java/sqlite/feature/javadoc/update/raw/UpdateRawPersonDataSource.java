package sqlite.feature.javadoc.update.raw;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={UpdateRawPersonDao.class}, fileName = "person.db")
public interface UpdateRawPersonDataSource {

}
