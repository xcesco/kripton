package sqlite.feature.immutable.adapter;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see PersonDAO
 * @see PersonDAOImpl
 * @see Person
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDAO.
   *
   * @return dao implementation
   */
  PersonDAOImpl getPersonDAO();
}
