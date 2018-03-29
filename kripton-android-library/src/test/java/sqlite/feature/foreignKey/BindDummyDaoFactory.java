package sqlite.feature.foreignKey;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao DaoBeanA_1
   */
  DaoBeanA_1Impl getDaoBeanA_1();

  /**
   *
   * retrieve dao DaoBeanA_2
   */
  DaoBeanA_2Impl getDaoBeanA_2();
}
