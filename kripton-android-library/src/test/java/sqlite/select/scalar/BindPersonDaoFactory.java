package sqlite.select.scalar;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see PersonDAO
 * @see PersonDAOImpl
 * @see Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDAO.
   *
   * @return dao implementation
   */
  PersonDAOImpl getPersonDAO();
}
