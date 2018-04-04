package sqlite.kripton186.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = {PhoneDao.class, PrefixConfigDao.class, CountryDao.class}, fileName = "xeno.db", schema = false)
public interface XenoDataSource {
}
