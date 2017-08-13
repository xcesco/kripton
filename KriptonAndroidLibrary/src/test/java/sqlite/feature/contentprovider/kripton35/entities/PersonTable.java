package sqlite.feature.contentprovider.kripton35.entities;

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
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, alias_parent_id INTEGER, birth_city INTEGER, birth_day TEXT, value INTEGER, name TEXT, surname TEXT, FOREIGN KEY(alias_parent_id) REFERENCES person(id), FOREIGN KEY(birth_city) REFERENCES city(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, alias_parent_id INTEGER, birth_city INTEGER, birth_day TEXT, value INTEGER, name TEXT, surname TEXT, FOREIGN KEY(alias_parent_id) REFERENCES person(id), FOREIGN KEY(birth_city) REFERENCES city(id));";

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
   * Entity's property <code>parentId</code> is associated to table column <code>alias_parent_id</code>. This costant represents column typeName.
   *
   *  @see Person#parentId
   */
  public static final String COLUMN_PARENT_ID = "alias_parent_id";

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

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column typeName.
   *
   *  @see Person#value
   */
  public static final String COLUMN_VALUE = "value";

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
}
