package sqlite.feature.jql.kripton163;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDataSource(fileName = "students.db", daoSet = {CollegeStudentDao.class},asyncTask = true, schema = true)
public interface CollegeStudentsDataSource {
}
