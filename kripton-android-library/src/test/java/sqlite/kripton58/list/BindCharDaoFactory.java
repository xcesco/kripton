package sqlite.kripton58.list;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for CharDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see CharDataSource
 * @see CharDao
 * @see CharDaoImpl
 * @see CharBean
 */
public interface BindCharDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao CharDao.
   *
   * @return dao implementation
   */
  CharDaoImpl getCharDao();
}
