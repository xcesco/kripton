package sqlite.feature.paginatedresult;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person2DataSource
 * @see Dao2Person
 * @see Dao2PersonImpl
 * @see Person
 */
public interface BindPerson2DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao Dao2Person.
   *
   * @return dao implementation
   */
  Dao2PersonImpl getDao2Person();
}
