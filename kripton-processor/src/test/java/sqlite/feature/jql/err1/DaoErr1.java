package sqlite.feature.jql.err1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(BeanErr1.class)
public interface DaoErr1 {

   // select a list of students with extended JQL
  @BindSqlSelect(jql="select * from CollegeStudent where firstName like ${firstName} || '%' ")
  List<BeanErr1> getStudents(String firstName);

}
