package sqlite.feature.dynamic.select1;

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
   *
   * retrieve dao PersonDAO
   */
  PersonDAOImpl getPersonDAO();
}
