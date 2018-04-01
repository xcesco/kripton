package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 20/02/2018.
 */
@BindDataSource(daoSet = {BookDao.class, UserDao.class, LoanDao.class}, fileName = "library.db")
public interface AppDataSource {
}
