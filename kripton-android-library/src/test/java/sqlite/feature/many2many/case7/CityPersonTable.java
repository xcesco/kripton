package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>CityPerson</code> is associated to table <code>city_person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see CityPerson
 */
public class CityPersonTable implements SQLiteTable {
  /**
   * Costant represents typeName of table city_person
   */
  public static final String TABLE_NAME = "city_person";

  /**
   * <p>
   * DDL to create table city_person
   * </p>
   *
   * <pre>CREATE TABLE city_person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, city_id INTEGER NOT NULL, person_id INTEGER NOT NULL, FOREIGN KEY(city_id) REFERENCES city(id) ON DELETE CASCADE, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, UNIQUE (city_id, person_id)); CREATE INDEX idx_city_person_city_id ON city_person(city_id); CREATE INDEX idx_city_person_person_id ON city_person(person_id); CREATE UNIQUE INDEX idx_city_person_0 on city_person (city_id, person_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE city_person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, city_id INTEGER NOT NULL, person_id INTEGER NOT NULL, FOREIGN KEY(city_id) REFERENCES city(id) ON DELETE CASCADE, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, UNIQUE (city_id, person_id)); CREATE INDEX idx_city_person_city_id ON city_person(city_id); CREATE INDEX idx_city_person_person_id ON city_person(person_id); CREATE UNIQUE INDEX idx_city_person_0 on city_person (city_id, person_id);";

  /**
   * <p>
   * DDL to drop table city_person
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_city_person_city_id; DROP INDEX IF EXISTS idx_city_person_person_id; DROP INDEX IF EXISTS idx_city_person_1;DROP TABLE IF EXISTS city_person;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_city_person_city_id; DROP INDEX IF EXISTS idx_city_person_person_id; DROP INDEX IF EXISTS idx_city_person_1;DROP TABLE IF EXISTS city_person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see CityPerson#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see CityPerson#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see CityPerson#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_CITY_ID, COLUMN_PERSON_ID};

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
