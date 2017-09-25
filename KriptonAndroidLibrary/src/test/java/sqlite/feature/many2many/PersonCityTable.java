package sqlite.feature.many2many;

import java.lang.String;

/**
 * <p>
 * Entity <code>PersonCity</code> is associated to table <code>person_city</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCity
 */
public class PersonCityTable {
  /**
   * Costant represents typeName of table person_city
   */
  public static final String TABLE_NAME = "person_city";

  /**
   * <p>
   * DDL to create table person_city
   * </p>
   *
   * <pre>CREATE TABLE person_city (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));";

  /**
   * <p>
   * DDL to drop table person_city
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_city;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCity#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCity#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";
}
