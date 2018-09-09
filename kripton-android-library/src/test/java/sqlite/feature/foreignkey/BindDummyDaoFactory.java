package sqlite.feature.foreignkey;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DummyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DummyDataSource
 * @see DaoBeanA_1
 * @see DaoBeanA_1Impl
 * @see BeanA_1
 * @see DaoBeanA_2
 * @see DaoBeanA_2Impl
 * @see BeanA_2
 */
public interface BindDummyDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoBeanA_1.
   *
   * @return dao implementation
   */
  DaoBeanA_1Impl getDaoBeanA_1();

  /**
   * Retrieve dao DaoBeanA_2.
   *
   * @return dao implementation
   */
  DaoBeanA_2Impl getDaoBeanA_2();
}
