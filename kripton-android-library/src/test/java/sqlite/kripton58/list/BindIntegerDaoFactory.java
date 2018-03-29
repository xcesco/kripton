package sqlite.kripton58.list;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for IntegerDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntegerDataSource
 * @see IntegerDao
 * @see IntegerDaoImpl
 * @see IntegerBean
 */
public interface BindIntegerDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao IntegerDao
   */
  IntegerDaoImpl getIntegerDao();
}
