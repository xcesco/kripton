package sqlite.feature.jql.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDataSource(fileName = "students.db", daoSet = {DaoErr2.class},asyncTask = true, schema = true)
public interface Err2DataSource {
}
