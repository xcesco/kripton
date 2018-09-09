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
   * Retrieve dao CityDao.
   *
   * @return dao implementation
   */
  CityDaoImpl getCityDao();

  /**
   * Retrieve dao PersonDao.
   *
   * @return dao implementation
   */
  PersonDaoImpl getPersonDao();

  /**
   * Retrieve dao City2PersonDao.
   *
   * @return dao implementation
   */
  City2PersonDaoImpl getCity2PersonDao();
}
