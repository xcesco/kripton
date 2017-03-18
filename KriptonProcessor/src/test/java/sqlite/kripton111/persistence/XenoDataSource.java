package sqlite.kripton111.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = {PhoneDao.class, PrefixConfigDao.class, CountryDao.class}, fileName = "xeno.db")
public interface XenoDataSource {
}
