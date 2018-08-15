package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Professor</code> is associated to table <code>professor</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Professor
 */
public class ProfessorTable implements SQLiteTable {
  /**
   * Costant represents typeName of table professor
   */
  public static final String TABLE_NAME = "professor";

  /**
   * <p>
   * DDL to create table professor
   * </p>
   *
   * <pre>CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_date TEXT, name TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_date TEXT, name TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);";

  /**
   * <p>
   * DDL to drop table professor
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_professor_1;DROP TABLE IF EXISTS professor;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_professor_1;DROP TABLE IF EXISTS professor;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Professor#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>birthDate</code> is associated to table column <code>birth_date</code>. This costant represents column name.
   *
   *  @see Professor#birthDate
   */
  public static final String COLUMN_BIRTH_DATE = "birth_date";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Professor#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Professor#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BIRTH_DATE, COLUMN_NAME, COLUMN_SURNAME};

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
