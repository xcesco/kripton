package sqlite.feature.many2many.err3;

/**
 * <p>
 * Entity <code>PersonCityErr3</code> is associated to table <code>person_city_err3</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCityErr3
 */
public class PersonCityErr3Table {
  /**
   * Costant represents typeName of table person_city_err3
   */
  public static final String TABLE_NAME = "person_city_err3";

  /**
   * <p>
   * DDL to create table person_city_err3
   * </p>
   *
   * <pre>CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));";

  /**
   * <p>
   * DDL to drop table person_city_err3
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_city_err3;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city_err3;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";
}
