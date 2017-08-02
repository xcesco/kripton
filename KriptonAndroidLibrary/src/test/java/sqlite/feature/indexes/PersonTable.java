package sqlite.feature.indexes;

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
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, type_name TEXT, name_temp TEXT, date TEXT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX idx_person_name ON person(name); CREATE UNIQUE INDEX idx_person_0 on person (type_name, date); CREATE INDEX idx_person_0 on person (birth_city, birth_day); CREATE INDEX idx_person_1 on person (surname);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, type_name TEXT, name_temp TEXT, date TEXT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); CREATE INDEX idx_person_name ON person(name); CREATE UNIQUE INDEX idx_person_0 on person (type_name, date); CREATE INDEX idx_person_0 on person (birth_city, birth_day); CREATE INDEX idx_person_1 on person (surname);";

  /**
   * <p>
   * DDL to drop table person
   * </p>
   *
   * <pre> DROP INDEX idx_person_name; DROP INDEX idx_person_1; DROP INDEX idx_person_1; DROP INDEX idx_person_2;DROP TABLE IF EXISTS person;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX idx_person_name; DROP INDEX idx_person_1; DROP INDEX idx_person_1; DROP INDEX idx_person_2;DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>typeName</code> is associated to table column <code>type_name</code>. This costant represents column typeName.
   *
   *  @see Person#typeName
   */
  public static final String COLUMN_TYPE_NAME = "type_name";

  /**
   * Entity's property <code>nameTemp</code> is associated to table column <code>name_temp</code>. This costant represents column typeName.
   *
   *  @see Person#nameTemp
   */
  public static final String COLUMN_NAME_TEMP = "name_temp";

  /**
   * Entity's property <code>date</code> is associated to table column <code>date</code>. This costant represents column typeName.
   *
   *  @see Person#date
   */
  public static final String COLUMN_DATE = "date";

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
   * Entity's property <code>birthCity</code> is associated to table column <code>birth_city</code>. This costant represents column typeName.
   *
   *  @see Person#birthCity
   */
  public static final String COLUMN_BIRTH_CITY = "birth_city";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column typeName.
   *
   *  @see Person#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";
}
