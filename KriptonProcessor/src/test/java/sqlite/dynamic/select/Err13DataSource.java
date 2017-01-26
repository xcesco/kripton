package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err13DAO.class }, fileName = "person.db", log=true)
public interface Err13DataSource {

}
