package sqlite.foreignKey;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy3DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy3DataSource
 * @see DaoBeanA_5
 * @see DaoBeanA_5Impl
 * @see BeanA_5
 * @see DaoBeanA_6
 * @see DaoBeanA_6Impl
 * @see BeanA_6
 */
public interface BindDummy3DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanA_5
   */
  DaoBeanA_5Impl getDaoBeanA_5();

  /**
   *
   * retrieve dao DaoBeanA_6
   */
  DaoBeanA_6Impl getDaoBeanA_6();
}
