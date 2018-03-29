package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonCirtyErr3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyErr3DataSource
 * @see PersonErr3Dao
 * @see PersonErr3DaoImpl
 * @see Person
 * @see CityErr3Dao
 * @see CityErr3DaoImpl
 * @see City
 * @see PersonCityErr1Dao
 * @see PersonCityErr1DaoImpl
 * @see PersonCityErr3
 */
public interface BindPersonCirtyErr3DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonErr3Dao
   */
  PersonErr3DaoImpl getPersonErr3Dao();

  /**
   *
   * retrieve dao CityErr3Dao
   */
  CityErr3DaoImpl getCityErr3Dao();

  /**
   *
   * retrieve dao PersonCityErr1Dao
   */
  PersonCityErr1DaoImpl getPersonCityErr1Dao();
}
