package bind.kripton81exceptioncoverage;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PKBean</code> is associated to table <code>p_k_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PKBean
 */
public class PKBeanTable implements SQLiteTable {
  /**
   * Costant represents typeName of table p_k_bean
   */
  public static final String TABLE_NAME = "p_k_bean";

  /**
   * <p>
   * DDL to create table p_k_bean
   * </p>
   *
   * <pre>CREATE TABLE p_k_bean (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, description TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE p_k_bean (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, description TEXT);";

  /**
   * <p>
   * DDL to drop table p_k_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS p_k_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS p_k_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PKBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>description</code> is associated to table column <code>description</code>. This costant represents column name.
   *
   *  @see PKBean#description
   */
  public static final String COLUMN_DESCRIPTION = "description";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DESCRIPTION};

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
