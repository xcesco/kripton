package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(ErrCollegeStudent.class)
public interface ErrCollegeStudentDao {
   	
	@BindSqlInsert(jql = "INSERT OR REPLACE INTO ErrCollegeStudent (surname) SELECT surname FROM CollegeStudent WHERE surname=${bean.surname}")
	void insertBeanFromSelect(CollegeStudent bean);
}
