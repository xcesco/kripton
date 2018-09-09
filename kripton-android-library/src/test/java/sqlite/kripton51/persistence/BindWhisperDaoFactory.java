package sqlite.kripton51.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for WhisperDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see WhisperDataSource
 * @see DaoMessage
 * @see DaoMessageImpl
 * @see MessageEntity
 */
public interface BindWhisperDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoMessage.
   *
   * @return dao implementation
   */
  DaoMessageImpl getDaoMessage();
}
