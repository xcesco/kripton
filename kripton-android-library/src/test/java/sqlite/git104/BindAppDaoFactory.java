package sqlite.git104;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DocumentDao
 * @see DocumentDaoImpl
 * @see Document
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DocumentDao.
   *
   * @return dao implementation
   */
  DocumentDaoImpl getDocumentDao();
}
