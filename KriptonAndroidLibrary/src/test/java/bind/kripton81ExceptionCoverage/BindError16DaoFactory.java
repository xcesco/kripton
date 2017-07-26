package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Error16DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Error16DataSource
 * @see Error16Dao
 * @see Error16DaoImpl
 * @see Error16Bean
 */
public interface BindError16DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Error16Dao
   */
  Error16DaoImpl getError16Dao();
}
