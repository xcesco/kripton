package sqlite.select.scalarlist;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err1PersonDAO.class }, fileName = "person.db", log=true)
public interface Err1PersonDataSource {

}
