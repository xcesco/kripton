package sqlite.dynamic;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { PersonUpdateDAO.class }, fileName = "person.db", log=true)
public interface PersonUpdateDataSource {

}
