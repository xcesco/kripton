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
 * @see sqlite.feature.contentprovider.kripton35.entities.Person
 * @see CityDAO
 * @see CityDAOImpl
 * @see sqlite.feature.contentprovider.kripton35.entities.City
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
