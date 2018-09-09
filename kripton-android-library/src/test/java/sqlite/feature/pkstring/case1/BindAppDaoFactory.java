package sqlite.feature.pkstring.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoZArtist
 * @see DaoZArtistImpl
 * @see ZArtist
 * @see DaoSong
 * @see DaoSongImpl
 * @see Song
 * @see DaoAlbum
 * @see DaoAlbumImpl
 * @see Album
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoZArtist.
   *
   * @return dao implementation
   */
  DaoZArtistImpl getDaoZArtist();

  /**
   * Retrieve dao DaoSong.
   *
   * @return dao implementation
   */
  DaoSongImpl getDaoSong();

  /**
   * Retrieve dao DaoAlbum.
   *
   * @return dao implementation
   */
  DaoAlbumImpl getDaoAlbum();
}
