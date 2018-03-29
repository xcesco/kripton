package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for CollegeStudentsDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see CollegeStudentsDataSource
 * @see CollegeStudentDao
 * @see CollegeStudentDaoImpl
 * @see CollegeStudent
 */
public interface BindCollegeStudentsDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao CollegeStudentDao
   */
  CollegeStudentDaoImpl getCollegeStudentDao();
}
