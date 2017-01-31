package sqlite.paginatedResult;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err4PersonDAO.class }, fileName = "person.db", log=true)
public interface Err4PersonDataSource {

}
