package sqlite.test04.v1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { PersonDao.class }, fileName = "person.db")
public interface PersonDataSource {

}
