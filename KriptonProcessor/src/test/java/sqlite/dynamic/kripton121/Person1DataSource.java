package sqlite.dynamic.kripton121;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao = { Person1DAO.class }, fileName = "person.db", log=true)
public interface Person1DataSource {

}
