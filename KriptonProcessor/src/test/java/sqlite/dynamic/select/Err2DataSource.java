package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err2DAO.class }, fileName = "person.db", log=true)
public interface Err2DataSource {

}
