package sqlite.dynamic;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err6DAO.class }, fileName = "person.db", log=true)
public interface Err6DataSource {

}
