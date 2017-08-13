package sqlite.feature.javadoc.delete.raw;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={DeleteRawPersonDao.class}, fileName = "person.db")
public interface DeleteRawPersonDataSource {

}
