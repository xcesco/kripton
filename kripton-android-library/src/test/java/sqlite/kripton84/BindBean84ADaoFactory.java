package sqlite.kripton84;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao Bean84ADao
   */
  Bean84ADaoImpl getBean84ADao();
}
