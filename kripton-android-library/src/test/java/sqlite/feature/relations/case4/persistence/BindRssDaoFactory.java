package sqlite.feature.relations.case4.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for RssDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see RssDataSource
 * @see DaoArticle
 * @see DaoArticleImpl
 * @see Article
 * @see DaoChannel
 * @see DaoChannelImpl
 * @see Channel
 */
public interface BindRssDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoArticle.
   *
   * @return dao implementation
   */
  DaoArticleImpl getDaoArticle();

  /**
   * Retrieve dao DaoChannel.
   *
   * @return dao implementation
   */
  DaoChannelImpl getDaoChannel();
}
