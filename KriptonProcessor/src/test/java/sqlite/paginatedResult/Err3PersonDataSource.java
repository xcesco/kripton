package sqlite.paginatedResult;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err3PersonDAO.class }, fileName = "person.db", log=true)
public interface Err3PersonDataSource {

}
