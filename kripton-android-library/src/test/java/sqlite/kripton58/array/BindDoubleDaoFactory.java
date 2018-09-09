package sqlite.kripton58.array;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DoubleDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DoubleDataSource
 * @see DoubleDao
 * @see DoubleDaoImpl
 * @see DoubleBean
 */
public interface BindDoubleDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DoubleDao.
   *
   * @return dao implementation
   */
  DoubleDaoImpl getDoubleDao();
}
