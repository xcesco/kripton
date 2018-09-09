package sqlite.feature.livedata.persistence1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for App1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see App1DataSource
 * @see DaoPerson1
 * @see DaoPerson1Impl
 * @see Person
 */
public interface BindApp1DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson1.
   *
   * @return dao implementation
   */
  DaoPerson1Impl getDaoPerson1();
}
