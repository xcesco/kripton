package sqlite.feature.jql.kripton163;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(CollegeStudent.class)
public interface CollegeStudentDao {

   // select a list of students with extended JQL
  @BindSqlSelect(jql="select * from CollegeStudent where firstName like ${firstName} || '%' ")
  List<CollegeStudent> getStudents(String firstName);

   // select a list of students with extended JQL
   @BindSqlSelect(where="firstName like ${firstName} || '%' ")
   List<CollegeStudent> getStudentsRaw(String firstName);

}
