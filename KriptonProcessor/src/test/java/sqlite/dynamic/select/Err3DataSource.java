package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err3DAO.class }, fileName = "person.db", log=true)
public interface Err3DataSource {

}
