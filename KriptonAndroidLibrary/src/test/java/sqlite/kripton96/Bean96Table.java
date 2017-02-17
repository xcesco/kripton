package sqlite.kripton96;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean96</code> is associated to table <code>bean96</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean96
 */
public class Bean96Table {
  /**
   * Costant represents typeName of table bean96
   */
  public static final String TABLE_NAME = "bean96";

  /**
   * <p>
   * DDL to create table bean96
   * </p>
   *
   * <pre>CREATE TABLE bean96 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean96 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT);";

  /**
   * <p>
   * DDL to drop table bean96
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean96;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean96;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Bean96#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Bean96#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column typeName.
   *
   *  @see Bean96#surname
   */
  public static final String COLUMN_SURNAME = "surname";
}
