package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see CityDao
 * @see CityDaoImpl
 * @see City
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 * @see City2PersonDao
 * @see City2PersonDaoImpl
 * @see CityPerson
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao CityDao
   */
  CityDaoImpl getCityDao();

  /**
   *
   * retrieve dao PersonDao
   */
  PersonDaoImpl getPersonDao();

  /**
   *
   * retrieve dao City2PersonDao
   */
  City2PersonDaoImpl getCity2PersonDao();
}
