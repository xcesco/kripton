package sqlite.kotlin.hierarchy;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoPerson.class}, fileName = "person.db")
public interface PersonDataSource {

}
