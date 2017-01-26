package sqlite.dynamic.update;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err2UpdateDAO.class }, fileName = "person.db", log=true)
public interface Err2UpdateDataSource {

}
