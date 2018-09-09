package sqlite.feature.many2many;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonCirtyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyDataSource
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 * @see CityDao
 * @see CityDaoImpl
 * @see City
 * @see PersonCityDao
 * @see PersonCityDaoImpl
 * @see PersonCity
 */
public interface BindPersonCirtyDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDao.
   *
   * @return dao implementation
   */
  PersonDaoImpl getPersonDao();

  /**
   * Retrieve dao CityDao.
   *
   * @return dao implementation
   */
  CityDaoImpl getCityDao();

  /**
   * Retrieve dao PersonCityDao.
   *
   * @return dao implementation
   */
  PersonCityDaoImpl getPersonCityDao();
}
