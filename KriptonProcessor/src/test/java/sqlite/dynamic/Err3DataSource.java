package sqlite.dynamic;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err3DAO.class }, fileName = "person.db", log=true)
public interface Err3DataSource {

}
