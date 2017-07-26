package sqlite.feature.JQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao={DaoChild.class, DaoPerson.class}, fileName = "familiy")
public interface FamilyDataSource {

}
