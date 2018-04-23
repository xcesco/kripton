package sqlite.kripton58.array;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for FloatDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FloatDataSource
 * @see FloatDao
 * @see FloatDaoImpl
 * @see FloatBean
 */
public interface BindFloatDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao FloatDao
   */
  FloatDaoImpl getFloatDao();
}
