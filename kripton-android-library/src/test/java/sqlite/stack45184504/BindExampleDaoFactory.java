package sqlite.stack45184504;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao FileBeanDao
   */
  FileBeanDaoImpl getFileBeanDao();
}
