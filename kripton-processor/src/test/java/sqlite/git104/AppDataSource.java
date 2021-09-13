package sqlite.git104;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { DocumentDao.class, }, fileName = "fasa.db", version = 3, schema = true)
public interface AppDataSource {

}
