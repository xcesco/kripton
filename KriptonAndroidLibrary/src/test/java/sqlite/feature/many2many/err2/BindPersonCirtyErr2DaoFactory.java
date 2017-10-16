package sqlite.feature.many2many.err2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonCirtyErr2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyErr2DataSource
 * @see PersonErr2Dao
 * @see PersonErr2DaoImpl
 * @see Person
 * @see CityErr2Dao
 * @see CityErr2DaoImpl
 * @see City
 * @see PersonCityErr2Dao
 * @see PersonCityErr2DaoImpl
 * @see PersonCityErr2
 */
public interface BindPersonCirtyErr2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonErr2Dao
   */
  PersonErr2DaoImpl getPersonErr2Dao();

  /**
   *
   * retrieve dao CityErr2Dao
   */
  CityErr2DaoImpl getCityErr2Dao();

  /**
   *
   * retrieve dao PersonCityErr2Dao
   */
  PersonCityErr2DaoImpl getPersonCityErr2Dao();
}
