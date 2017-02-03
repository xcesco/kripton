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
 * @see sqlite.select.Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDAO
   */
  PersonDAOImpl getPersonDAO();
}
