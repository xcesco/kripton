package sqlite.feature.paginatedresult;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person1DataSource
 * @see Dao1Person
 * @see Dao1PersonImpl
 * @see Person
 */
public interface BindPerson1DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Dao1Person
   */
  Dao1PersonImpl getDao1Person();
}
