package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 21/02/2018.
 */
@BindDataSource(daoSet = {DeviceDao.class, UserDao.class, UserDeviceDao.class}, fileName = "app2.db")
public interface App2DataSource {
}
