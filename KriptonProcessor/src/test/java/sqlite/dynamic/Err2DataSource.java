package sqlite.dynamic;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err2DAO.class }, fileName = "person.db", log=true)
public interface Err2DataSource {

}
