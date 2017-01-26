package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err5DAO.class }, fileName = "person.db", log=true)
public interface Err5DataSource {

}
