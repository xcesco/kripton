package sqlite.feature.livedata.persistence0;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for App0DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see App0DataSource
 * @see DaoPerson0
 * @see DaoPerson0Impl
 * @see Person
 */
public interface BindApp0DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoPerson0
   */
  DaoPerson0Impl getDaoPerson0();
}
