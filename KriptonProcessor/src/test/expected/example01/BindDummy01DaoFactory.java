package example01;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy01DataSource
 * @see DaoChannel
 * @see DaoChannelImpl
 * @see Channel
 */
public interface BindDummy01DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoChannel
   */
  DaoChannelImpl getDaoChannel();
}
