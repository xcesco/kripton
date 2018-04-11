package sqlite.feature.foreignkey;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy2DataSource
 * @see DaoBeanA_3
 * @see DaoBeanA_3Impl
 * @see BeanA_3
 * @see DaoBeanA_4
 * @see DaoBeanA_4Impl
 * @see BeanA_4
 */
public interface BindDummy2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanA_3
   */
  DaoBeanA_3Impl getDaoBeanA_3();

  /**
   *
   * retrieve dao DaoBeanA_4
   */
  DaoBeanA_4Impl getDaoBeanA_4();
}
