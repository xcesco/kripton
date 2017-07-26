package bind.kripton81ExceptionCoverage;

import java.lang.String;

/**
 * <p>
 * Entity <code>Error10Bean</code> is associated to table <code>error10_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Error10Bean
 */
public class Error10BeanTable {
  /**
   * Costant represents typeName of table error10_bean
   */
  public static final String TABLE_NAME = "error10_bean";

  /**
   * <p>
   * DDL to create table error10_bean
   * </p>
   *
   * <pre>CREATE TABLE error10_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE error10_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);";

  /**
   * <p>
   * DDL to drop table error10_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS error10_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS error10_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Error10Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Error10Bean#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>date</code> is associated to table column <code>date</code>. This costant represents column typeName.
   *
   *  @see Error10Bean#date
   */
  public static final String COLUMN_DATE = "date";
}
