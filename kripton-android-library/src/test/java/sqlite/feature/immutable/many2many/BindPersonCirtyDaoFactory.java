package sqlite.feature.immutable.many2many;

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
   *
   * retrieve dao PersonDao
   */
  PersonDaoImpl getPersonDao();

  /**
   *
   * retrieve dao CityDao
   */
  CityDaoImpl getCityDao();

  /**
   *
   * retrieve dao PersonCityDao
   */
  PersonCityDaoImpl getPersonCityDao();
}
