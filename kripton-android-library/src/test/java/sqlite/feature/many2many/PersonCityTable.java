package sqlite.feature.many2many;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PersonCity</code> is associated to table <code>person_city</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCity
 */
public class PersonCityTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person_city
   */
  public static final String TABLE_NAME = "person_city";

  /**
   * <p>
   * DDL to create table person_city
   * </p>
   *
   * <pre>CREATE TABLE person_city (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, person_id INTEGER NOT NULL, city_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES persons(id) ON DELETE CASCADE, FOREIGN KEY(city_id) REFERENCES cities(id) ON DELETE CASCADE, UNIQUE (person_id, city_id)); CREATE INDEX idx_person_city_person_id ON person_city(person_id); CREATE INDEX idx_person_city_city_id ON person_city(city_id); CREATE UNIQUE INDEX idx_person_city_0 on person_city (person_id, city_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, person_id INTEGER NOT NULL, city_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES persons(id) ON DELETE CASCADE, FOREIGN KEY(city_id) REFERENCES cities(id) ON DELETE CASCADE, UNIQUE (person_id, city_id)); CREATE INDEX idx_person_city_person_id ON person_city(person_id); CREATE INDEX idx_person_city_city_id ON person_city(city_id); CREATE UNIQUE INDEX idx_person_city_0 on person_city (person_id, city_id);";

  /**
   * <p>
   * DDL to drop table person_city
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_person_city_person_id; DROP INDEX IF EXISTS idx_person_city_city_id; DROP INDEX IF EXISTS idx_person_city_1;DROP TABLE IF EXISTS person_city;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_person_city_person_id; DROP INDEX IF EXISTS idx_person_city_city_id; DROP INDEX IF EXISTS idx_person_city_1;DROP TABLE IF EXISTS person_city;";

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

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_PERSON_ID, COLUMN_CITY_ID};

  /**
   * Columns array
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
