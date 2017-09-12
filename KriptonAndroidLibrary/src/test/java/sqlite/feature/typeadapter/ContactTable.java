package sqlite.feature.typeadapter;

import java.lang.String;

/**
 * <p>
 * Entity <code>Contact</code> is associated to table <code>contact</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Contact
 */
public class ContactTable {
  /**
   * Costant represents typeName of table contact
   */
  public static final String TABLE_NAME = "contact";

  /**
   * <p>
   * DDL to create table contact
   * </p>
   *
   * <pre>CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_day TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_day TEXT);";

  /**
   * <p>
   * DDL to drop table contact
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS contact;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS contact;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Contact#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Contact#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Contact#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";
}
