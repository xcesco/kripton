package sqlite.feature.pkstring.rx;
import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * The Room database that contains the Users table
 */
@BindContentProvider(authority="sqlite.feature.pkstring.rx")
@BindDataSource(daoSet = {UserDao.class}, version = 1, fileName = "sample.db", rx = true)
public interface UserDataSource {

}
