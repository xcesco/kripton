package sqlite.kripton58.list;

import java.lang.String;

/**
 * <p>
 * Entity <code>IntegerBean</code> is associated to table <code>integer_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see IntegerBean
 */
public class IntegerBeanTable {
  /**
   * Costant represents name of table integer_bean
   */
  public static final String TABLE_NAME = "integer_bean";

  /**
   * <p>
   * DDL to create table integer_bean
   * </p>
   *
   * <pre>CREATE TABLE integer_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE integer_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table integer_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS integer_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS integer_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see IntegerBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see IntegerBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see IntegerBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
