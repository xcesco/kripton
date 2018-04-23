package sqlite.feature.relations.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoAlbum
 * @see DaoAlbumImpl
 * @see Album
 * @see DaoSong
 * @see DaoSongImpl
 * @see Song
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoAlbum
   */
  DaoAlbumImpl getDaoAlbum();

  /**
   *
   * retrieve dao DaoSong
   */
  DaoSongImpl getDaoSong();
}
