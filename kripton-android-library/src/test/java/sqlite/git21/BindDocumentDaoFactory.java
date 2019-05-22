package sqlite.git21;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DocumentDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DocumentDataSource
 * @see DaoDocument
 * @see DaoDocumentImpl
 * @see Document
 */
public interface BindDocumentDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoDocument.
   *
   * @return dao implementation
   */
  DaoDocumentImpl getDaoDocument();
}
