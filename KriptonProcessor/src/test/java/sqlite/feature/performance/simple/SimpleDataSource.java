package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 23/09/2017.
 */
@BindDataSource(daoSet = {SimpleAddressDao.class}, fileName = "kripton.db", generateLog = false)
public interface SimpleDataSource {
}
