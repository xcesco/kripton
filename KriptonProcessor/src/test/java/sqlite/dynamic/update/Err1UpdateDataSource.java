package sqlite.dynamic.update;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err1UpdateDAO.class }, fileName = "person.db", log=true)
public interface Err1UpdateDataSource {

}
