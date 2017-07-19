package sqlite.kripton38;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean05</code> is associated to table <code>bean05</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean05
 */
public class Bean05Table {
  /**
   * Costant represents typeName of table bean05
   */
  public static final String TABLE_NAME = "bean05";

  /**
   * <p>
   * DDL to create table bean05
   * </p>
   *
   * <pre>CREATE TABLE bean05 (pk INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, bean_type TEXT, text TEXT, content BLOB, creation_time TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean05 (pk INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, bean_type TEXT, text TEXT, content BLOB, creation_time TEXT);";

  /**
   * <p>
   * DDL to drop table bean05
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean05;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean05;";

  /**
   * Entity's property <code>pk</code> is associated to table column <code>pk</code>. This costant represents column typeName.
   *
   *  @see Bean05#pk
   */
  public static final String COLUMN_PK = "pk";

  /**
   * Entity's property <code>number</code> is associated to table column <code>number</code>. This costant represents column typeName.
   *
   *  @see Bean05#number
   */
  public static final String COLUMN_NUMBER = "number";

  /**
   * Entity's property <code>beanType</code> is associated to table column <code>bean_type</code>. This costant represents column typeName.
   *
   *  @see Bean05#beanType
   */
  public static final String COLUMN_BEAN_TYPE = "bean_type";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column typeName.
   *
   *  @see Bean05#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column typeName.
   *
   *  @see Bean05#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>creationTime</code> is associated to table column <code>creation_time</code>. This costant represents column typeName.
   *
   *  @see Bean05#creationTime
   */
  public static final String COLUMN_CREATION_TIME = "creation_time";

  /**
   * write
   */
  public static byte[] serializeContent(byte[] value) {
    return value;
  }

  /**
   * parse
   */
  public static byte[] parseContent(byte[] input) {
    return input;
  }
}
