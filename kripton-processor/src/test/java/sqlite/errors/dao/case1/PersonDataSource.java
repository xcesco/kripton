package sqlite.errors.dao.case1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoPerson.class}, fileName="data.db")
public interface PersonDataSource {

}
