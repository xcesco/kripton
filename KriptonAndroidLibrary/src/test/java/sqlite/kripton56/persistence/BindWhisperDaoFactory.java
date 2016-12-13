package sqlite.kripton56.persistence;

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
 * @see sqlite.kripton56.entities.MessageEntity
 */
public interface BindWhisperDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoMessage
   */
  DaoMessageImpl getDaoMessage();
}
