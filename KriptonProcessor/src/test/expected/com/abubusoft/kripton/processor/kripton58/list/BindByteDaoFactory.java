package com.abubusoft.kripton.processor.kripton58.list;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ByteDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ByteDataSource
 * @see ByteDao
 * @see BindByteDao
 * @see ByteBean
 */
public interface BindByteDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao ByteDao
   */
  BindByteDao getByteDao();
}
