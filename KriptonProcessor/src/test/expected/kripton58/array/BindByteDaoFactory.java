package kripton58.array;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ByteDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ByteDataSource
 * @see ByteDao
 * @see ByteDaoImpl
 * @see ByteBean
 */
public interface BindByteDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao ByteDao
   */
  ByteDaoImpl getByteDao();
}
