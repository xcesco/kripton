package sqlite.feature.many2many.case4.persistence;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PersonPhoneNumber</code> is associated to table <code>person_phone_number</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonPhoneNumber
 */
public class PersonPhoneNumberTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person_phone_number
   */
  public static final String TABLE_NAME = "person_phone_number";

  /**
   * <p>
   * DDL to create table person_phone_number
   * </p>
   *
   * <pre>CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER NOT NULL, phone_number_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE); CREATE INDEX idx_person_phone_number_person_id ON person_phone_number(person_id); CREATE INDEX idx_person_phone_number_phone_number_id ON person_phone_number(phone_number_id); CREATE UNIQUE INDEX idx_person_phone_number_0 on person_phone_number (person_id, phone_number_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER NOT NULL, phone_number_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE); CREATE INDEX idx_person_phone_number_person_id ON person_phone_number(person_id); CREATE INDEX idx_person_phone_number_phone_number_id ON person_phone_number(phone_number_id); CREATE UNIQUE INDEX idx_person_phone_number_0 on person_phone_number (person_id, phone_number_id);";

  /**
   * <p>
   * DDL to drop table person_phone_number
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_person_phone_number_person_id; DROP INDEX IF EXISTS idx_person_phone_number_phone_number_id; DROP INDEX IF EXISTS idx_person_phone_number_1;DROP TABLE IF EXISTS person_phone_number;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_person_phone_number_person_id; DROP INDEX IF EXISTS idx_person_phone_number_phone_number_id; DROP INDEX IF EXISTS idx_person_phone_number_1;DROP TABLE IF EXISTS person_phone_number;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonPhoneNumber#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonPhoneNumber#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>phoneNumberId</code> is associated to table column <code>phone_number_id</code>. This costant represents column name.
   *
   *  @see PersonPhoneNumber#phoneNumberId
   */
  public static final String COLUMN_PHONE_NUMBER_ID = "phone_number_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_PERSON_ID, COLUMN_PHONE_NUMBER_ID};

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
