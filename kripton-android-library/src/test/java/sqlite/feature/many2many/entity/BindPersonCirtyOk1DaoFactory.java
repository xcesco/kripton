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
   * Retrieve dao PersonOk1Dao.
   *
   * @return dao implementation
   */
  PersonOk1DaoImpl getPersonOk1Dao();

  /**
   * Retrieve dao CityOk1Dao.
   *
   * @return dao implementation
   */
  CityOk1DaoImpl getCityOk1Dao();

  /**
   * Retrieve dao PersonCityOk1Dao.
   *
   * @return dao implementation
   */
  PersonCityOk1DaoImpl getPersonCityOk1Dao();
}
