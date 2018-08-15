package sqlite.feature.jql.entities;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Person</code> is associated to table <code>person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person
 */
public class PersonTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person
   */
  public static final String TABLE_NAME = "person";

  /**
   * <p>
   * DDL to create table person
   * </p>
   *
   * <pre>CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, image BLOB, name TEXT NOT NULL);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, image BLOB, name TEXT NOT NULL);";

  /**
   * <p>
   * DDL to drop table person
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>_id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "_id";

  /**
   * Entity's property <code>image</code> is associated to table column <code>image</code>. This costant represents column name.
   *
   *  @see Person#image
   */
  public static final String COLUMN_IMAGE = "image";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_IMAGE, COLUMN_NAME};

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
