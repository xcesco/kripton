package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Error10DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Error10DataSource
 * @see Error10Dao
 * @see Error10DaoImpl
 * @see Error10Bean
 */
public interface BindError10DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Error10Dao
   */
  Error10DaoImpl getError10Dao();
}
