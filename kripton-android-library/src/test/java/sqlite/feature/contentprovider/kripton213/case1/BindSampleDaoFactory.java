package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao CheeseDao
   */
  CheeseDaoImpl getCheeseDao();
}
