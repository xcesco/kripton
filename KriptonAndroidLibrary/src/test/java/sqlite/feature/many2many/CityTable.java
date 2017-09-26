package sqlite.feature.many2many;

import java.lang.String;

/**
 * <p>
 * Entity <code>City</code> is associated to table <code>cities</code>
 * This class represents table associated to entity.
 * </p>
 *  @see City
 */
public class CityTable {
  /**
   * Costant represents typeName of table cities
   */
  public static final String TABLE_NAME = "cities";

  /**
   * <p>
   * DDL to create table cities
   * </p>
   *
   * <pre>CREATE TABLE cities (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE cities (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

  /**
   * <p>
   * DDL to drop table cities
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS cities;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS cities;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see City#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see City#name
   */
  public static final String COLUMN_NAME = "name";
}
