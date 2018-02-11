package sqlite.feature.jql.err2;

/**
 * <p>
 * Entity <code>BeanErr2</code> is associated to table <code>bean_err2</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanErr2
 */
public class BeanErr2Table {
  /**
   * Costant represents typeName of table bean_err2
   */
  public static final String TABLE_NAME = "bean_err2";

  /**
   * <p>
   * DDL to create table bean_err2
   * </p>
   *
   * <pre>CREATE TABLE bean_err2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_err2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";

  /**
   * <p>
   * DDL to drop table bean_err2
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_err2;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_err2;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>_id</code>. This costant represents column name.
   *
   *  @see BeanErr2#id
   */
  public static final String COLUMN_ID = "_id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see BeanErr2#name
   */
  public static final String COLUMN_NAME = "name";
}
