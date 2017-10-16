package sqlite.feature.many2many.err1;

/**
 * <p>
 * Entity <code>PersonCityErr1</code> is associated to table <code>person_city_err1</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCityErr1
 */
public class PersonCityErr1Table {
  /**
   * Costant represents typeName of table person_city_err1
   */
  public static final String TABLE_NAME = "person_city_err1";

  /**
   * <p>
   * DDL to create table person_city_err1
   * </p>
   *
   * <pre>CREATE TABLE person_city_err1 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city_err1 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER);";

  /**
   * <p>
   * DDL to drop table person_city_err1
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_city_err1;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city_err1;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCityErr1#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr1#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr1#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";
}
