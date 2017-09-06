package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDataSource(fileName = "students.db", daoSet = {CollegeStudentDao.class},generateAsyncTask = true, generateSchema = true)
public interface CollegeStudentsDataSource {
}
