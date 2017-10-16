package sqlite.feature.many2many.err1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonCirtyErr1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyErr1DataSource
 * @see PersonErr1Dao
 * @see PersonErr1DaoImpl
 * @see Person
 * @see CityErr1Dao
 * @see CityErr1DaoImpl
 * @see City
 * @see PersonCityErr1Dao
 * @see PersonCityErr1DaoImpl
 * @see PersonCityErr1
 */
public interface BindPersonCirtyErr1DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonErr1Dao
   */
  PersonErr1DaoImpl getPersonErr1Dao();

  /**
   *
   * retrieve dao CityErr1Dao
   */
  CityErr1DaoImpl getCityErr1Dao();

  /**
   *
   * retrieve dao PersonCityErr1Dao
   */
  PersonCityErr1DaoImpl getPersonCityErr1Dao();
}
