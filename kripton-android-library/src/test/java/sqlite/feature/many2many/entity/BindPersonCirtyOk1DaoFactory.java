package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonCirtyOk1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyOk1DataSource
 * @see PersonOk1Dao
 * @see PersonOk1DaoImpl
 * @see Person
 * @see CityOk1Dao
 * @see CityOk1DaoImpl
 * @see City
 * @see PersonCityOk1Dao
 * @see PersonCityOk1DaoImpl
 * @see PersonCityOk1
 */
public interface BindPersonCirtyOk1DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonOk1Dao
   */
  PersonOk1DaoImpl getPersonOk1Dao();

  /**
   *
   * retrieve dao CityOk1Dao
   */
  CityOk1DaoImpl getCityOk1Dao();

  /**
   *
   * retrieve dao PersonCityOk1Dao
   */
  PersonCityOk1DaoImpl getPersonCityOk1Dao();
}
