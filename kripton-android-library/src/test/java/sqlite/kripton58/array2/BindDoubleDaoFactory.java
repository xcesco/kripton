package sqlite.kripton58.array2;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao DoubleDao
   */
  DoubleDaoImpl getDoubleDao();
}
