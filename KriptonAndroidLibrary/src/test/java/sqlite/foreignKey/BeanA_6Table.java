package sqlite.foreignKey;

import java.lang.String;

/**
 * <p>
 * Entity <code>BeanA_6</code> is associated to table <code>bean_a_6</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanA_6
 */
public class BeanA_6Table {
  /**
   * Costant represents name of table bean_a_6
   */
  public static final String TABLE_NAME = "bean_a_6";

  /**
   * <p>
   * DDL to create table bean_a_6
   * </p>
   *
   * <pre>CREATE TABLE bean_a_6 (pk INTEGER PRIMARY KEY AUTOINCREMENT, bean_a2_id INTEGER NOT NULL, value_string2 TEXT, FOREIGN KEY(bean_a2_id) REFERENCES bean_a_5(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_6 (pk INTEGER PRIMARY KEY AUTOINCREMENT, bean_a2_id INTEGER NOT NULL, value_string2 TEXT, FOREIGN KEY(bean_a2_id) REFERENCES bean_a_5(id));";

  /**
   * <p>
   * DDL to drop table bean_a_6
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_a_6;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_6;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>pk</code>. This costant represents column name.
   *
   *  @see BeanA_6#id
   */
  public static final String COLUMN_ID = "pk";

  /**
   * Entity's property <code>beanA2Id</code> is associated to table column <code>bean_a2_id</code>. This costant represents column name.
   *
   *  @see BeanA_6#beanA2Id
   */
  public static final String COLUMN_BEAN_A2_ID = "bean_a2_id";

  /**
   * Entity's property <code>valueString2</code> is associated to table column <code>value_string2</code>. This costant represents column name.
   *
   *  @see BeanA_6#valueString2
   */
  public static final String COLUMN_VALUE_STRING2 = "value_string2";
}
