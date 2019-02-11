package sqlite.feature.custombean.case3;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see BookDao
 * @see BookDaoImpl
 * @see Book
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao BookDao.
   *
   * @return dao implementation
   */
  BookDaoImpl getBookDao();
}
