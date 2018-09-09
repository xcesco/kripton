package sqlite.kripton84;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean84ADataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84ADataSource
 * @see Bean84ADao
 * @see Bean84ADaoImpl
 * @see Bean84A
 */
public interface BindBean84ADaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao Bean84ADao.
   *
   * @return dao implementation
   */
  Bean84ADaoImpl getBean84ADao();
}
