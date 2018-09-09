package sqlite.kripton84;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean84BDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84BDataSource
 * @see Bean84BDao
 * @see Bean84BDaoImpl
 * @see Bean84B
 */
public interface BindBean84BDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao Bean84BDao.
   *
   * @return dao implementation
   */
  Bean84BDaoImpl getBean84BDao();
}
