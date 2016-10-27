package com.abubusoft.kripton.processor.kripton58.array;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for StringDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see StringDataSource
 * @see StringDao
 * @see BindStringDao
 * @see StringBean
 */
public interface BindStringDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao StringDao
   */
  BindStringDao getStringDao();
}
