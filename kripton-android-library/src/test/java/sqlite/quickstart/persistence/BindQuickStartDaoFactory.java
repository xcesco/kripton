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
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();

  /**
   *
   * retrieve dao PostDao
   */
  PostDaoImpl getPostDao();

  /**
   *
   * retrieve dao CommentDao
   */
  CommentDaoImpl getCommentDao();

  /**
   *
   * retrieve dao TodoDao
   */
  TodoDaoImpl getTodoDao();
}
