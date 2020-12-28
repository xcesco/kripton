package sqlite.feature.time.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoBean
 * @see DaoBeanImpl
 * @see Bean
 * @see DaoImmutableBean
 * @see DaoImmutableBeanImpl
 * @see BeanImmutable
 * @see DaoBeanWithAccessors
 * @see DaoBeanWithAccessorsImpl
 * @see BeanWithAccessors
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoBean.
   *
   * @return dao implementation
   */
  DaoBeanImpl getDaoBean();

  /**
   * Retrieve dao DaoImmutableBean.
   *
   * @return dao implementation
   */
  DaoImmutableBeanImpl getDaoImmutableBean();

  /**
   * Retrieve dao DaoBeanWithAccessors.
   *
   * @return dao implementation
   */
  DaoBeanWithAccessorsImpl getDaoBeanWithAccessors();
}
