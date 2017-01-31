package sqlite.paginatedResult;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err2PersonDAO.class }, fileName = "person.db", log=true)
public interface Err2PersonDataSource {

}
