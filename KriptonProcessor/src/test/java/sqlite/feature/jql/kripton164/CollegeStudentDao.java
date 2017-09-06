package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(CollegeStudent.class)
public interface CollegeStudentDao {

   @BindSqlInsert(jql="INSERT INTO CollegeStudent (surname) VALUES (${student.surname})")
   void insert(CollegeStudent student);
   	
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO CollegeStudent (surname) SELECT surname FROM CollegeStudent WHERE surname=${bean.surname}")
	void insertBeanFromSelect(CollegeStudent bean);
}
