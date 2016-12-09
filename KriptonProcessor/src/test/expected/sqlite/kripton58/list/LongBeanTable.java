package sqlite.kripton58.list;

import java.lang.String;

/**
 * <p>
 * Entity <code>LongBean</code> is associated to table <code>long_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see LongBean
 */
public class LongBeanTable {
  /**
   * Costant represents name of table long_bean
   */
  public static final String TABLE_NAME = "long_bean";

  /**
   * <p>
   * DDL to create table long_bean
   * </p>
   *
   * <pre>CREATE TABLE long_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE long_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table long_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS long_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS long_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see LongBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see LongBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see LongBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
