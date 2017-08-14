package sqlite.quickstart.model;

import java.lang.String;

/**
 * <p>
 * Entity <code>Todo</code> is associated to table <code>todo</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Todo
 */
public class TodoTable {
  /**
   * Costant represents typeName of table todo
   */
  public static final String TABLE_NAME = "todo";

  /**
   * <p>
   * DDL to create table todo
   * </p>
   *
   * <pre>CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, title TEXT, completed INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, title TEXT, completed INTEGER);";

  /**
   * <p>
   * DDL to drop table todo
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS todo;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS todo;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Todo#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see Todo#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Todo#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>completed</code> is associated to table column <code>completed</code>. This costant represents column name.
   *
   *  @see Todo#completed
   */
  public static final String COLUMN_COMPLETED = "completed";
}
