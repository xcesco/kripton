package sqlite.kriptonXXX.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = {PhoneDao.class, PrefixConfigDao.class, CountryDao.class}, fileName = "xeno.db", generateSchema = false)
public interface XenoDataSource {
}
