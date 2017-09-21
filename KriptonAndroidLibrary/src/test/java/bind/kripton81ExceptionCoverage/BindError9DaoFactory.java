package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Error9DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Error9DataSource
 * @see Error9Dao
 * @see Error9DaoImpl
 * @see Error9Bean
 */
public interface BindError9DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Error9Dao
   */
  Error9DaoImpl getError9Dao();
}
