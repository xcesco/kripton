package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Err4DAO.class }, fileName = "person.db", log=true)
public interface Err4DataSource {

}
