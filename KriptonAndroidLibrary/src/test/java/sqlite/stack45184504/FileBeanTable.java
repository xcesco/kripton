package sqlite.stack45184504;

import java.lang.String;

/**
 * <p>
 * Entity <code>FileBean</code> is associated to table <code>file_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see FileBean
 */
public class FileBeanTable {
  /**
   * Costant represents typeName of table file_bean
   */
  public static final String TABLE_NAME = "file_bean";

  /**
   * <p>
   * DDL to create table file_bean
   * </p>
   *
   * <pre>CREATE TABLE file_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, text TEXT, address TEXT, image BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE file_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, text TEXT, address TEXT, image BLOB);";

  /**
   * <p>
   * DDL to drop table file_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS file_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS file_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see FileBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>date</code> is associated to table column <code>date</code>. This costant represents column name.
   *
   *  @see FileBean#date
   */
  public static final String COLUMN_DATE = "date";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see FileBean#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see FileBean#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see FileBean#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>image</code> is associated to table column <code>image</code>. This costant represents column name.
   *
   *  @see FileBean#image
   */
  public static final String COLUMN_IMAGE = "image";

  /**
   * for attribute image serialization
   */
  public static byte[] serializeImage(byte[] value) {
    return value;
  }

  /**
   * for attribute image parsing
   */
  public static byte[] parseImage(byte[] input) {
    return input;
  }
}
