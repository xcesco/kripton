package sqlite.feature.javadoc.insert.bean;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={InsertBeanPersonDao.class}, fileName = "person.db")
public interface InsertBeanPersonDataSource {

}
