package sqlite.feature.foreignKey;

import com.abubusoft.kripton.android.orm.SQLiteTable;

/**
 * <p>
 * Entity <code>BeanA_1</code> is associated to table <code>bean_a_1</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanA_1
 */
public class BeanA_1Table implements SQLiteTable {
  /**
   * Costant represents typeName of table bean_a_1
   */
  public static final String TABLE_NAME = "bean_a_1";

  /**
   * <p>
   * DDL to create table bean_a_1
   * </p>
   *
   * <pre>CREATE TABLE bean_a_1 (id INTEGER PRIMARY KEY AUTOINCREMENT, bean_a2_id INTEGER, value_string TEXT, FOREIGN KEY(bean_a2_id) REFERENCES bean_a_2(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_1 (id INTEGER PRIMARY KEY AUTOINCREMENT, bean_a2_id INTEGER, value_string TEXT, FOREIGN KEY(bean_a2_id) REFERENCES bean_a_2(id));";

  /**
   * <p>
   * DDL to drop table bean_a_1
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_a_1;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_1;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see BeanA_1#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>beanA2Id</code> is associated to table column <code>bean_a2_id</code>. This costant represents column name.
   *
   *  @see BeanA_1#beanA2Id
   */
  public static final String COLUMN_BEAN_A2_ID = "bean_a2_id";

  /**
   * Entity's property <code>valueString</code> is associated to table column <code>value_string</code>. This costant represents column name.
   *
   *  @see BeanA_1#valueString
   */
  public static final String COLUMN_VALUE_STRING = "value_string";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BEAN_A2_ID, COLUMN_VALUE_STRING};

  /**
   * Columns array
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
