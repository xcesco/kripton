package bind.kripton81ExceptionCoverage;

import java.lang.String;

/**
 * <p>
 * Entity <code>Error16Bean</code> is associated to table <code>error16_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Error16Bean
 */
public class Error16BeanTable {
  /**
   * Costant represents typeName of table error16_bean
   */
  public static final String TABLE_NAME = "error16_bean";

  /**
   * <p>
   * DDL to create table error16_bean
   * </p>
   *
   * <pre>CREATE TABLE error16_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE error16_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT);";

  /**
   * <p>
   * DDL to drop table error16_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS error16_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS error16_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Error16Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Error16Bean#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>date</code> is associated to table column <code>date</code>. This costant represents column typeName.
   *
   *  @see Error16Bean#date
   */
  public static final String COLUMN_DATE = "date";
}
