package sqlite.stack45184504;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ExampleDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ExampleDataSource
 * @see FileBeanDao
 * @see FileBeanDaoImpl
 * @see FileBean
 */
public interface BindExampleDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao FileBeanDao.
   *
   * @return dao implementation
   */
  FileBeanDaoImpl getFileBeanDao();
}
