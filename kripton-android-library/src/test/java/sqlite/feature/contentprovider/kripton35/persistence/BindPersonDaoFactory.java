package sqlite.feature.contentprovider.kripton35.persistence;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
 * @see CityDAO
 * @see CityDAOImpl
 * @see City
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDAO
   */
  PersonDAOImpl getPersonDAO();

  /**
   *
   * retrieve dao CityDAO
   */
  CityDAOImpl getCityDAO();
}
