package sqlite.feature.many2many.case6.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PrefixConfig</code> is associated to table <code>prefix_config</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PrefixConfig
 */
public class PrefixConfigTable implements SQLiteTable {
  /**
   * Costant represents typeName of table prefix_config
   */
  public static final String TABLE_NAME = "prefix_config";

  /**
   * <p>
   * DDL to create table prefix_config
   * </p>
   *
   * <pre>CREATE TABLE prefix_config (id INTEGER PRIMARY KEY AUTOINCREMENT, default_country TEXT, dialog_timeout INTEGER, dual_billing_prefix TEXT, enabled INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE prefix_config (id INTEGER PRIMARY KEY AUTOINCREMENT, default_country TEXT, dialog_timeout INTEGER, dual_billing_prefix TEXT, enabled INTEGER);";

  /**
   * <p>
   * DDL to drop table prefix_config
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS prefix_config;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS prefix_config;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PrefixConfig#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>defaultCountry</code> is associated to table column <code>default_country</code>. This costant represents column name.
   *
   *  @see PrefixConfig#defaultCountry
   */
  public static final String COLUMN_DEFAULT_COUNTRY = "default_country";

  /**
   * Entity's property <code>dialogTimeout</code> is associated to table column <code>dialog_timeout</code>. This costant represents column name.
   *
   *  @see PrefixConfig#dialogTimeout
   */
  public static final String COLUMN_DIALOG_TIMEOUT = "dialog_timeout";

  /**
   * Entity's property <code>dualBillingPrefix</code> is associated to table column <code>dual_billing_prefix</code>. This costant represents column name.
   *
   *  @see PrefixConfig#dualBillingPrefix
   */
  public static final String COLUMN_DUAL_BILLING_PREFIX = "dual_billing_prefix";

  /**
   * Entity's property <code>enabled</code> is associated to table column <code>enabled</code>. This costant represents column name.
   *
   *  @see PrefixConfig#enabled
   */
  public static final String COLUMN_ENABLED = "enabled";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DEFAULT_COUNTRY, COLUMN_DIALOG_TIMEOUT, COLUMN_DUAL_BILLING_PREFIX, COLUMN_ENABLED};

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
