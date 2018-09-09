package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoUser
 * @see DaoUserImpl
 * @see User
 * @see DaoMessage
 * @see DaoMessageImpl
 * @see Message
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoUser.
   *
   * @return dao implementation
   */
  DaoUserImpl getDaoUser();

  /**
   * Retrieve dao DaoMessage.
   *
   * @return dao implementation
   */
  DaoMessageImpl getDaoMessage();
}
