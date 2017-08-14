package sqlite.kripton49.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean01Entity</code> is associated to table <code>bean01</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean01Entity
 */
public class Bean01EntityTable {
  /**
   * Costant represents typeName of table bean01
   */
  public static final String TABLE_NAME = "bean01";

  /**
   * <p>
   * DDL to create table bean01
   * </p>
   *
   * <pre>CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);";

  /**
   * <p>
   * DDL to drop table bean01
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean01;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean01;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean01Entity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean01Entity#text
   */
  public static final String COLUMN_TEXT = "text";
}
