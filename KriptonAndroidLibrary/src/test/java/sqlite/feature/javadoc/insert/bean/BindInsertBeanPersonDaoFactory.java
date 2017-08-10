package sqlite.feature.javadoc.insert.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for InsertBeanPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see InsertBeanPersonDataSource
 * @see InsertBeanPersonDao
 * @see InsertBeanPersonDaoImpl
 * @see sqlite.feature.javadoc.Person
 */
public interface BindInsertBeanPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao InsertBeanPersonDao
   */
  InsertBeanPersonDaoImpl getInsertBeanPersonDao();
}
