package bind.feature.kotlin.err2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoRssFeed
 * @see DaoRssFeedImpl
 * @see RssFeed
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoRssFeed
   */
  DaoRssFeedImpl getDaoRssFeed();
}
