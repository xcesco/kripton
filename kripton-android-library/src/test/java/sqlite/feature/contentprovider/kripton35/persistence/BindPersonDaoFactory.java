package sqlite.feature.contentprovider.kripton35.persistence;

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
 * @see CityDAO
 * @see CityDAOImpl
 * @see City
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDAO.
   *
   * @return dao implementation
   */
  PersonDAOImpl getPersonDAO();

  /**
   * Retrieve dao CityDAO.
   *
   * @return dao implementation
   */
  CityDAOImpl getCityDAO();
}
