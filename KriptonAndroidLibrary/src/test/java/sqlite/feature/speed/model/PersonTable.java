package sqlite.feature.speed.model;

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
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, data1 TEXT, data2 TEXT, data3 TEXT, data4 TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, data1 TEXT, data2 TEXT, data3 TEXT, data4 TEXT);";

  /**
   * <p>
   * DDL to drop table person
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Person#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>data1</code> is associated to table column <code>data1</code>. This costant represents column name.
   *
   *  @see Person#data1
   */
  public static final String COLUMN_DATA1 = "data1";

  /**
   * Entity's property <code>data2</code> is associated to table column <code>data2</code>. This costant represents column name.
   *
   *  @see Person#data2
   */
  public static final String COLUMN_DATA2 = "data2";

  /**
   * Entity's property <code>data3</code> is associated to table column <code>data3</code>. This costant represents column name.
   *
   *  @see Person#data3
   */
  public static final String COLUMN_DATA3 = "data3";

  /**
   * Entity's property <code>data4</code> is associated to table column <code>data4</code>. This costant represents column name.
   *
   *  @see Person#data4
   */
  public static final String COLUMN_DATA4 = "data4";
}
