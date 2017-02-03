package sqlite.select.scalar;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { PersonDAO.class }, fileName = "person.db", log=true)
public interface PersonDataSource {

}
