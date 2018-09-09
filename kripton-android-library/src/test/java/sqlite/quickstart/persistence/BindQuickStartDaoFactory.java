package sqlite.quickstart.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for QuickStartDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see QuickStartDataSource
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 * @see PostDao
 * @see PostDaoImpl
 * @see Post
 * @see CommentDao
 * @see CommentDaoImpl
 * @see Comment
 * @see TodoDao
 * @see TodoDaoImpl
 * @see Todo
 */
public interface BindQuickStartDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao UserDao.
   *
   * @return dao implementation
   */
  UserDaoImpl getUserDao();

  /**
   * Retrieve dao PostDao.
   *
   * @return dao implementation
   */
  PostDaoImpl getPostDao();

  /**
   * Retrieve dao CommentDao.
   *
   * @return dao implementation
   */
  CommentDaoImpl getCommentDao();

  /**
   * Retrieve dao TodoDao.
   *
   * @return dao implementation
   */
  TodoDaoImpl getTodoDao();
}
