package sqlite.feature.time.case1;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>BeanWithAccessors</code> is associated to table <code>bean_with_accessors</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanWithAccessors
 */
public class BeanWithAccessorsTable implements SQLiteTable {
  /**
   * Costant represents typeName of table bean_with_accessors
   */
  public static final String TABLE_NAME = "bean_with_accessors";

  /**
   * <p>
   * DDL to create table bean_with_accessors
   * </p>
   *
   * <pre>CREATE TABLE bean_with_accessors (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, duration TEXT, istant TEXT, local_date TEXT, local_date_time TEXT, local_time TEXT, month_day TEXT, offset_date_time TEXT, offset_time TEXT, period TEXT, year TEXT, year_month TEXT, zone_id TEXT, zone_offset TEXT, zoned_date_time TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_with_accessors (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, duration TEXT, istant TEXT, local_date TEXT, local_date_time TEXT, local_time TEXT, month_day TEXT, offset_date_time TEXT, offset_time TEXT, period TEXT, year TEXT, year_month TEXT, zone_id TEXT, zone_offset TEXT, zoned_date_time TEXT);";

  /**
   * <p>
   * DDL to drop table bean_with_accessors
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_with_accessors;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_with_accessors;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>duration</code> is associated to table column <code>duration</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#duration
   */
  public static final String COLUMN_DURATION = "duration";

  /**
   * Entity's property <code>istant</code> is associated to table column <code>istant</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#istant
   */
  public static final String COLUMN_ISTANT = "istant";

  /**
   * Entity's property <code>localDate</code> is associated to table column <code>local_date</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#localDate
   */
  public static final String COLUMN_LOCAL_DATE = "local_date";

  /**
   * Entity's property <code>localDateTime</code> is associated to table column <code>local_date_time</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#localDateTime
   */
  public static final String COLUMN_LOCAL_DATE_TIME = "local_date_time";

  /**
   * Entity's property <code>localTime</code> is associated to table column <code>local_time</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#localTime
   */
  public static final String COLUMN_LOCAL_TIME = "local_time";

  /**
   * Entity's property <code>monthDay</code> is associated to table column <code>month_day</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#monthDay
   */
  public static final String COLUMN_MONTH_DAY = "month_day";

  /**
   * Entity's property <code>offsetDateTime</code> is associated to table column <code>offset_date_time</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#offsetDateTime
   */
  public static final String COLUMN_OFFSET_DATE_TIME = "offset_date_time";

  /**
   * Entity's property <code>offsetTime</code> is associated to table column <code>offset_time</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#offsetTime
   */
  public static final String COLUMN_OFFSET_TIME = "offset_time";

  /**
   * Entity's property <code>period</code> is associated to table column <code>period</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#period
   */
  public static final String COLUMN_PERIOD = "period";

  /**
   * Entity's property <code>year</code> is associated to table column <code>year</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#year
   */
  public static final String COLUMN_YEAR = "year";

  /**
   * Entity's property <code>yearMonth</code> is associated to table column <code>year_month</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#yearMonth
   */
  public static final String COLUMN_YEAR_MONTH = "year_month";

  /**
   * Entity's property <code>zoneId</code> is associated to table column <code>zone_id</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#zoneId
   */
  public static final String COLUMN_ZONE_ID = "zone_id";

  /**
   * Entity's property <code>zoneOffset</code> is associated to table column <code>zone_offset</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#zoneOffset
   */
  public static final String COLUMN_ZONE_OFFSET = "zone_offset";

  /**
   * Entity's property <code>zonedDateTime</code> is associated to table column <code>zoned_date_time</code>. This costant represents column name.
   *
   *  @see BeanWithAccessors#zonedDateTime
   */
  public static final String COLUMN_ZONED_DATE_TIME = "zoned_date_time";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DURATION, COLUMN_ISTANT, COLUMN_LOCAL_DATE, COLUMN_LOCAL_DATE_TIME, COLUMN_LOCAL_TIME, COLUMN_MONTH_DAY, COLUMN_OFFSET_DATE_TIME, COLUMN_OFFSET_TIME, COLUMN_PERIOD, COLUMN_YEAR, COLUMN_YEAR_MONTH, COLUMN_ZONE_ID, COLUMN_ZONE_OFFSET, COLUMN_ZONED_DATE_TIME};

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
