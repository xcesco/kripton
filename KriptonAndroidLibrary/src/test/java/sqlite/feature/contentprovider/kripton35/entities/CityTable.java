package sqlite.feature.contentprovider.kripton35.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>City</code> is associated to table <code>city</code>
 * This class represents table associated to entity.
 * </p>
 *  @see City
 */
public class CityTable {
  /**
   * Costant represents typeName of table city
   */
  public static final String TABLE_NAME = "city";

  /**
   * <p>
   * DDL to create table city
   * </p>
   *
   * <pre>CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE city (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

  /**
   * <p>
   * DDL to drop table city
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS city;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS city;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see City#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see City#name
   */
  public static final String COLUMN_NAME = "name";
}
