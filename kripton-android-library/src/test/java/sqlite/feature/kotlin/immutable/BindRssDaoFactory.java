package sqlite.feature.kotlin.immutable;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for RssDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see RssDataSource
 * @see DaoRss
 * @see DaoRssImpl
 * @see RssFeed
 * @see DaoArticle
 * @see DaoArticleImpl
 * @see Article
 * @see DaoChannel
 * @see DaoChannelImpl
 * @see Channel
 */
public interface BindRssDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoRss
   */
  DaoRssImpl getDaoRss();

  /**
   *
   * retrieve dao DaoArticle
   */
  DaoArticleImpl getDaoArticle();

  /**
   *
   * retrieve dao DaoChannel
   */
  DaoChannelImpl getDaoChannel();
}
