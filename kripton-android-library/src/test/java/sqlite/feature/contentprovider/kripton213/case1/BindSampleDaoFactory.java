package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SampleDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SampleDataSource
 * @see CheeseDao
 * @see CheeseDaoImpl
 * @see Cheese
 */
public interface BindSampleDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao CheeseDao.
   *
   * @return dao implementation
   */
  CheeseDaoImpl getCheeseDao();
}
