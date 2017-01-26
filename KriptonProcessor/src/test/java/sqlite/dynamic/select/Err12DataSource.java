package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err12DAO.class }, fileName = "person.db", log=true)
public interface Err12DataSource {

}
