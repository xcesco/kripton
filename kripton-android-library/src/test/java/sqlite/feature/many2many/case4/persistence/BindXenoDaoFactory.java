package sqlite.feature.many2many.case4.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public interface BindXenoDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PhoneDao.
   *
   * @return dao implementation
   */
  PhoneDaoImpl getPhoneDao();

  /**
   * Retrieve dao PrefixConfigDao.
   *
   * @return dao implementation
   */
  PrefixConfigDaoImpl getPrefixConfigDao();

  /**
   * Retrieve dao CountryDao.
   *
   * @return dao implementation
   */
  CountryDaoImpl getCountryDao();

  /**
   * Retrieve dao Person2PhoneDao.
   *
   * @return dao implementation
   */
  Person2PhoneDaoImpl getPerson2PhoneDao();

  /**
   * Retrieve dao PersonDao.
   *
   * @return dao implementation
   */
  PersonDaoImpl getPersonDao();
}
