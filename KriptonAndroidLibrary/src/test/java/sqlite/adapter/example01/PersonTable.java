package sqlite.adapter.example01;

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
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth TEXT);";

  /**
   * <p>
   * DDL to drop table person
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column typeName.
   *
   *  @see Person#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>birthDate</code> is associated to table column <code>birth</code>. This costant represents column typeName.
   *
   *  @see Person#birthDate
   */
  public static final String COLUMN_BIRTH_DATE = "birth";
}
