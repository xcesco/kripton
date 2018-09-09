package sqlite.stack44633883;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SchoolLunchDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SchoolLunchDataSource
 * @see SchoolLunchDAO
 * @see SchoolLunchDAOImpl
 * @see SchoolLunch
 */
public interface BindSchoolLunchDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao SchoolLunchDAO.
   *
   * @return dao implementation
   */
  SchoolLunchDAOImpl getSchoolLunchDAO();
}
