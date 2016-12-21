package sqlite.foreignKey;

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
   * Costant represents name of table bean_a_2
   */
  public static final String TABLE_NAME = "bean_a_2";

  /**
   * <p>
   * DDL to create table bean_a_2
   * </p>
   *
   * <pre>CREATE TABLE bean_a_2 (pk INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_2 (pk INTEGER);";

  /**
   * <p>
   * DDL to drop table bean_a_2
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_a_2;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_2;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see BeanA_2#id
   */
  public static final String COLUMN_ID = "id";
}
