package sqlite.feature.multiple.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import java.util.concurrent.Future;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson.
   *
   * @return dao implementation
   */
  DaoPersonImpl getDaoPerson();

  /**
   * Executes method {@link execute}
   *
   * @return <code>true</code> if transaction was done succefull.
   */
  boolean execute(String name);

  /**
   * Executes method {@link execute} in async mode
   *
   * @return a <code>Future</code> with true if transaction was done succefull.
   */
  Future<Boolean> executeAsync(String name);
}
