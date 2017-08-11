package sqlite.feature.javadoc.insert.raw;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for InsertRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see InsertRawPersonDataSource
 * @see InsertRawPersonDao
 * @see InsertRawPersonDaoImpl
 * @see sqlite.feature.javadoc.Person
 */
public interface BindInsertRawPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao InsertRawPersonDao
   */
  InsertRawPersonDaoImpl getInsertRawPersonDao();
}
