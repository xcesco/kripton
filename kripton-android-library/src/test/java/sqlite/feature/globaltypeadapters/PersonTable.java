package sqlite.feature.globaltypeadapters;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Person</code> is associated to table <code>person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person
 */
public class PersonTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person
   */
  public static final String TABLE_NAME = "person";

  /**
   * <p>
   * DDL to create table person
   * </p>
   *
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, birth_day INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, birth_day INTEGER);";

  /**
   * <p>
   * DDL to drop table person
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Person#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BIRTH_DAY};

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
