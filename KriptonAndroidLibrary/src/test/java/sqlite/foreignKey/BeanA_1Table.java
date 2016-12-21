package sqlite.foreignKey;

import java.lang.String;

/**
 * <p>
 * Entity <code>BeanA_1</code> is associated to table <code>bean_a_1</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanA_1
 */
public class BeanA_1Table {
  /**
   * Costant represents name of table bean_a_1
   */
  public static final String TABLE_NAME = "bean_a_1";

  /**
   * <p>
   * DDL to create table bean_a_1
   * </p>
   *
   * <pre>CREATE TABLE bean_a_1 (ID INTEGER PRIMARY KEY AUTOINCREMENT, BEAN_A2_ID INTEGER, FOREIGN KEY(BEAN_A2_ID) REFERENCES bean_a_2(PK));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_1 (ID INTEGER PRIMARY KEY AUTOINCREMENT, BEAN_A2_ID INTEGER, FOREIGN KEY(BEAN_A2_ID) REFERENCES bean_a_2(PK));";

  /**
   * <p>
   * DDL to drop table bean_a_1
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_a_1;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_1;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>ID</code>. This costant represents column name.
   *
   *  @see BeanA_1#id
   */
  public static final String COLUMN_ID = "ID";

  /**
   * Entity's property <code>beanA2Id</code> is associated to table column <code>BEAN_A2_ID</code>. This costant represents column name.
   *
   *  @see BeanA_1#beanA2Id
   */
  public static final String COLUMN_BEAN_A2_ID = "BEAN_A2_ID";
}
