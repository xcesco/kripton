package sqlite.feature.jql.err2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(BeanErr2.class)
public interface DaoErr2 {

   // select a list of students with extended JQL
  @BindSqlUpdate(jql="UPDATE BeanErr2 SET name=${name}")
  int updateStudents(@BindSqlParam("name") String firstName);

}
