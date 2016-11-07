package com.abubusoft.kripton.tutorial;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SecurityDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SecurityDataSource
 * @see DaoUser
 * @see DaoUserImpl
 * @see User
 */
public interface BindSecurityDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoUser
   */
  DaoUserImpl getDaoUser();
}
