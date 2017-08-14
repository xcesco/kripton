package sqlite.feature.foreignKey;

import java.lang.String;

/**
 * <p>
 * Entity <code>BeanA_2</code> is associated to table <code>bean_a_2</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanA_2
 */
public class BeanA_2Table {
  /**
   * Costant represents typeName of table bean_a_2
   */
  public static final String TABLE_NAME = "bean_a_2";

  /**
   * <p>
   * DDL to create table bean_a_2
   * </p>
   *
   * <pre>CREATE TABLE bean_a_2 (pk INTEGER PRIMARY KEY AUTOINCREMENT, value_string2 TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_2 (pk INTEGER PRIMARY KEY AUTOINCREMENT, value_string2 TEXT);";

  /**
   * <p>
   * DDL to drop table bean_a_2
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_a_2;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_2;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>pk</code>. This costant represents column name.
   *
   *  @see BeanA_2#id
   */
  public static final String COLUMN_ID = "pk";

  /**
   * Entity's property <code>valueString2</code> is associated to table column <code>value_string2</code>. This costant represents column name.
   *
   *  @see BeanA_2#valueString2
   */
  public static final String COLUMN_VALUE_STRING2 = "value_string2";
}
