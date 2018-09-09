package sqlite.feature.speed.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDao.
   *
   * @return dao implementation
   */
  PersonDaoImpl getPersonDao();
}
