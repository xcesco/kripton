package sqlite.kripton64;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean64BDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean64BDataSource
 * @see Bean64BDao
 * @see Bean64BDaoImpl
 * @see Bean64B
 */
public interface BindBean64BDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean64BDao
   */
  Bean64BDaoImpl getBean64BDao();
}
