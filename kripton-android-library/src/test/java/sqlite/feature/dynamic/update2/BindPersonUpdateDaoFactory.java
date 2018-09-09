package sqlite.feature.dynamic.update2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonUpdateDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonUpdateDataSource
 * @see PersonUpdateDAO
 * @see PersonUpdateDAOImpl
 * @see Person
 */
public interface BindPersonUpdateDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonUpdateDAO.
   *
   * @return dao implementation
   */
  PersonUpdateDAOImpl getPersonUpdateDAO();
}
