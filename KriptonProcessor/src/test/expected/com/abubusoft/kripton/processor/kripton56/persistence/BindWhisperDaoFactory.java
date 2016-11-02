package com.abubusoft.kripton.processor.kripton56.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for WhisperDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see WhisperDataSource
 * @see DaoMessage
 * @see BindDaoMessage
 * @see com.abubusoft.kripton.processor.kripton56.entities.MessageEntity
 */
public interface BindWhisperDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoMessage
   */
  BindDaoMessage getDaoMessage();
}
