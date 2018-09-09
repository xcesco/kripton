package sqlite.feature.paginatedresult;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

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
   * Retrieve dao Dao4Person.
   *
   * @return dao implementation
   */
  Dao4PersonImpl getDao4Person();
}
