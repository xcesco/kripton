package bind.kripton81ExceptionCoverage;

import java.lang.String;

/**
 * <p>
 * Entity <code>Error9Bean</code> is associated to table <code>error9_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Error9Bean
 */
public class Error9BeanTable {
  /**
   * Costant represents typeName of table error9_bean
   */
  public static final String TABLE_NAME = "error9_bean";

  /**
   * <p>
   * DDL to create table error9_bean
   * </p>
   *
   * <pre>CREATE TABLE error9_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE error9_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);";

  /**
   * <p>
   * DDL to drop table error9_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS error9_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS error9_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Error9Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Error9Bean#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>date</code> is associated to table column <code>date</code>. This costant represents column name.
   *
   *  @see Error9Bean#date
   */
  public static final String COLUMN_DATE = "date";
}
