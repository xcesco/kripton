package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "sqlite.feature.javadoc.bean")
@BindDataSource(daoSet={DeleteBeanPersonDao.class}, fileName = "person.db")
public interface DeleteBeanPersonDataSource {

}
