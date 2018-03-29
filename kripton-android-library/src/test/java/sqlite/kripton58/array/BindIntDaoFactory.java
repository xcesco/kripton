package sqlite.kripton58.array;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for IntDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntDataSource
 * @see IntDao
 * @see IntDaoImpl
 * @see IntBean
 */
public interface BindIntDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao IntDao
   */
  IntDaoImpl getIntDao();
}
