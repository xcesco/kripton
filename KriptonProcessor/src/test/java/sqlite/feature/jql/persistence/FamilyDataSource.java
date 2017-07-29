package sqlite.feature.jql.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoChild.class, DaoPerson.class}, fileName = "familiy")
public interface FamilyDataSource {

}
