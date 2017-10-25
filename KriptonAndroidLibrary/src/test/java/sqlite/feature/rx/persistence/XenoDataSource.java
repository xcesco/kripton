package sqlite.feature.rx.persistence;


import com.abubusoft.kripton.android.annotation.BindDataSource;


@BindDataSource(daoSet = {PhoneDao.class, PrefixConfigDao.class, CountryDao.class, Person2PhoneDao.class, PersonDao.class}, fileName = "xeno.db", asyncTask = true, schema = true)
public interface XenoDataSource {
}
