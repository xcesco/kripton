package sqlite.feature.globaltypeadapters.bitmap;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

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
}
