package sqlite.indexes;

import java.lang.String;

/**
 * <p>
 * Entity <code>Err2Person</code> is associated to table <code>err2_person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Err2Person
 */
public class Err2PersonTable {
  /**
   * Costant represents name of table err2_person
   */
  public static final String TABLE_NAME = "err2_person";

  /**
   * <p>
   * DDL to create table err2_person
   * </p>
   *
   * <pre>CREATE TABLE err2_person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX IF NOT EXISTS idx_err2_person_name ON err2_person(name); CREATE INDEX IF NOT EXISTS idx_err2_person_0 (birth_city birth_day);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE err2_person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX IF NOT EXISTS idx_err2_person_name ON err2_person(name); CREATE INDEX IF NOT EXISTS idx_err2_person_0 (birth_city birth_day);";

  /**
   * <p>
   * DDL to drop table err2_person
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS err2_person;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS err2_person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Err2Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Err2Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Err2Person#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>birthCity</code> is associated to table column <code>birth_city</code>. This costant represents column name.
   *
   *  @see Err2Person#birthCity
   */
  public static final String COLUMN_BIRTH_CITY = "birth_city";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Err2Person#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";
}
