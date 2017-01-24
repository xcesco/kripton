package sqlite.example02;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { PersonDAO.class }, fileName = "person.db")
public interface PersonDataSource {

}
