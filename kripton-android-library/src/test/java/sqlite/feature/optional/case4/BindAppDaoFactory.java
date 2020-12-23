package sqlite.feature.optional.case4;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoArticle
 * @see DaoArticleImpl
 * @see Article
 * @see DaoArtist
 * @see DaoArtistImpl
 * @see Artist
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoArticle.
   *
   * @return dao implementation
   */
  DaoArticleImpl getDaoArticle();

  /**
   * Retrieve dao DaoArtist.
   *
   * @return dao implementation
   */
  DaoArtistImpl getDaoArtist();
}
