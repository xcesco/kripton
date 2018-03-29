package sqlite.feature.javadoc.insert.raw;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for InsertRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see InsertRawPersonDataSource
 * @see InsertRawPersonDao
 * @see InsertRawPersonDaoImpl
 * @see Person
 */
public interface BindInsertRawPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao InsertRawPersonDao
   */
  InsertRawPersonDaoImpl getInsertRawPersonDao();
}
