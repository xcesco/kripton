package sqlite.feature.many2many;

import java.lang.String;

/**
 * <p>
 * Entity <code>Person2City</code> is associated to table <code>Person2City</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person2City
 */
public class Person2CityTable {
  /**
   * Costant represents typeName of table Person2City
   */
  public static final String TABLE_NAME = "Person2City";

  /**
   * <p>
   * DDL to create table Person2City
   * </p>
   *
   * <pre>CREATE TABLE Person2City (id INTEGER PRIMARY KEY AUTOINCREMENT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE Person2City (id INTEGER PRIMARY KEY AUTOINCREMENT);";

  /**
   * <p>
   * DDL to drop table Person2City
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS Person2City;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Person2City;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person2City#id
   */
  public static final String COLUMN_ID = "id";
}
