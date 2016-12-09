package sqlite.kripton58.array;

import java.lang.String;

/**
 * <p>
 * Entity <code>FloatBean</code> is associated to table <code>float_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see FloatBean
 */
public class FloatBeanTable {
  /**
   * Costant represents name of table float_bean
   */
  public static final String TABLE_NAME = "float_bean";

  /**
   * <p>
   * DDL to create table float_bean
   * </p>
   *
   * <pre>CREATE TABLE float_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE float_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table float_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS float_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS float_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see FloatBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see FloatBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see FloatBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
