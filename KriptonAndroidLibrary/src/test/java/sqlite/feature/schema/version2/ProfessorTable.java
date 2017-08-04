package sqlite.feature.schema.version2;

import java.lang.String;

/**
 * <p>
 * Entity <code>Professor</code> is associated to table <code>professor</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Professor
 */
public class ProfessorTable {
  /**
   * Costant represents typeName of table professor
   */
  public static final String TABLE_NAME = "professor";

  /**
   * <p>
   * DDL to create table professor
   * </p>
   *
   * <pre>CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_date TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_date TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);";

  /**
   * <p>
   * DDL to drop table professor
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_professor_1;DROP TABLE IF EXISTS professor;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_professor_1;DROP TABLE IF EXISTS professor;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Professor#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Professor#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>birthDate</code> is associated to table column <code>birth_date</code>. This costant represents column typeName.
   *
   *  @see Professor#birthDate
   */
  public static final String COLUMN_BIRTH_DATE = "birth_date";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column typeName.
   *
   *  @see Professor#surname
   */
  public static final String COLUMN_SURNAME = "surname";
}
