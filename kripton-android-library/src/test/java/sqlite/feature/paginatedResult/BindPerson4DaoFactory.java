package sqlite.feature.paginatedResult;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person4DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person4DataSource
 * @see Dao4Person
 * @see Dao4PersonImpl
 * @see Person
 */
public interface BindPerson4DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Dao4Person
   */
  Dao4PersonImpl getDao4Person();
}
