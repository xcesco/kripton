package bind.kripton81exceptioncoverage;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean8DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean8DataSource
 * @see Bean8Dao
 * @see Bean8DaoImpl
 * @see Bean8
 */
public interface BindBean8DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao Bean8Dao.
   *
   * @return dao implementation
   */
  Bean8DaoImpl getBean8Dao();
}
