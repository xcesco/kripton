package sqlite.kripton58.array;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for LongDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see LongDataSource
 * @see LongDao
 * @see LongDaoImpl
 * @see LongBean
 */
public interface BindLongDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao LongDao
   */
  LongDaoImpl getLongDao();
}
