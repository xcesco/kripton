package sqlite.feature.indexes;

import java.lang.String;

/**
 * <p>
 * Entity <code>Err1Person</code> is associated to table <code>err1_person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Err1Person
 */
public class Err1PersonTable {
  /**
   * Costant represents typeName of table err1_person
   */
  public static final String TABLE_NAME = "err1_person";

  /**
   * <p>
   * DDL to create table err1_person
   * </p>
   *
   * <pre>CREATE TABLE err1_person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX idx_err1_person_name ON err1_person(name);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE err1_person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX idx_err1_person_name ON err1_person(name);";

  /**
   * <p>
   * DDL to drop table err1_person
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_err1_person_name;DROP TABLE IF EXISTS err1_person;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_err1_person_name;DROP TABLE IF EXISTS err1_person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Err1Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Err1Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Err1Person#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>birthCity</code> is associated to table column <code>birth_city</code>. This costant represents column name.
   *
   *  @see Err1Person#birthCity
   */
  public static final String COLUMN_BIRTH_CITY = "birth_city";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Err1Person#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";
}
