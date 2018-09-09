package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

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
   * Retrieve dao CollegeStudentDao.
   *
   * @return dao implementation
   */
  CollegeStudentDaoImpl getCollegeStudentDao();
}
