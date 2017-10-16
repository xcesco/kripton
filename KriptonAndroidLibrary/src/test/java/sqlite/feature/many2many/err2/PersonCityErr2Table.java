package sqlite.feature.many2many.err2;

/**
 * <p>
 * Entity <code>PersonCityErr2</code> is associated to table <code>person_city_err2</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCityErr2
 */
public class PersonCityErr2Table {
  /**
   * Costant represents typeName of table person_city_err2
   */
  public static final String TABLE_NAME = "person_city_err2";

  /**
   * <p>
   * DDL to create table person_city_err2
   * </p>
   *
   * <pre>CREATE TABLE person_city_err2 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city_err2 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id));";

  /**
   * <p>
   * DDL to drop table person_city_err2
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_city_err2;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city_err2;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCityErr2#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr2#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr2#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";
}
