package sqlite.feature.kotlin.immutable2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>RssFeed</code> is associated to table <code>rss_feed</code>
 * This class represents table associated to entity.
 * </p>
 *  @see RssFeed
 */
public class RssFeedTable implements SQLiteTable {
  /**
   * Costant represents typeName of table rss_feed
   */
  public static final String TABLE_NAME = "rss_feed";

  /**
   * <p>
   * DDL to create table rss_feed
   * </p>
   *
   * <pre>CREATE TABLE rss_feed (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT UNIQUE, version TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE rss_feed (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT UNIQUE, version TEXT);";

  /**
   * <p>
   * DDL to drop table rss_feed
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS rss_feed;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS rss_feed;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see RssFeed#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see RssFeed#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>version</code> is associated to table column <code>version</code>. This costant represents column name.
   *
   *  @see RssFeed#version
   */
  public static final String COLUMN_VERSION = "version";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_UID, COLUMN_VERSION};

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
