package sqlite.feature.jql.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao={DaoChild.class, DaoPerson.class}, fileName = "familiy")
public interface FamilyDataSource {

}
