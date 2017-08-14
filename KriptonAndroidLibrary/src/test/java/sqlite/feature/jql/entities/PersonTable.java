package sqlite.feature.jql.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>Person</code> is associated to table <code>person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person
 */
public class PersonTable {
  /**
   * Costant represents typeName of table person
   */
  public static final String TABLE_NAME = "person";

  /**
   * <p>
   * DDL to create table person
   * </p>
   *
   * <pre>CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, image BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, image BLOB);";

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
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>image</code> is associated to table column <code>image</code>. This costant represents column name.
   *
   *  @see Person#image
   */
  public static final String COLUMN_IMAGE = "image";

  /**
   * write
   */
  public static byte[] serializeImage(byte[] value) {
    return value;
  }

  /**
   * parse
   */
  public static byte[] parseImage(byte[] input) {
    return input;
  }
}
