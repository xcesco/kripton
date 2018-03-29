package sqlite.kripton41;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy02DataSource
 * @see DaoBeanSelectOK
 * @see DaoBeanSelectOKImpl
 * @see Bean01
 */
public interface BindDummy02DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanSelectOK
   */
  DaoBeanSelectOKImpl getDaoBeanSelectOK();
}
