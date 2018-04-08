package sqlite.kripton64;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean64ADataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean64ADataSource
 * @see Bean64ADao
 * @see Bean64ADaoImpl
 * @see Bean64A
 */
public interface BindBean64ADaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean64ADao
   */
  Bean64ADaoImpl getBean64ADao();
}
