package sqlite.feature.many2many.case4.persistence;

/**
 * <p>
 * Entity <code>PersonPhoneNumber</code> is associated to table <code>person_phone_number</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonPhoneNumber
 */
public class PersonPhoneNumberTable {
  /**
   * Costant represents typeName of table person_phone_number
   */
  public static final String TABLE_NAME = "person_phone_number";

  /**
   * <p>
   * DDL to create table person_phone_number
   * </p>
   *
   * <pre>CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, phone_number_id INTEGER, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, phone_number_id INTEGER, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE);";

  /**
   * <p>
   * DDL to drop table person_phone_number
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_phone_number;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_phone_number;";

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
}
