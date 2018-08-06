package sqlite.feature.pkstring.rx;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for UserDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see UserDataSource
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 */
public interface BindUserDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();
}
