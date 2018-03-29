package sqlite.feature.paginatedResult;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person3DataSource
 * @see Dao3Person
 * @see Dao3PersonImpl
 * @see Person
 */
public interface BindPerson3DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Dao3Person
   */
  Dao3PersonImpl getDao3Person();
}
