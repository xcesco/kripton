package sqlite.kripton41;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy06DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy06DataSource
 * @see DaoBeanUpdateOK
 * @see DaoBeanUpdateOKImpl
 * @see Bean01
 */
public interface BindDummy06DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanUpdateOK
   */
  DaoBeanUpdateOKImpl getDaoBeanUpdateOK();
}
