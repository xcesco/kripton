package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Seminar2Student</code> is associated to table <code>seminar_2_student</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Seminar2Student
 */
public class Seminar2StudentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table seminar_2_student
   */
  public static final String TABLE_NAME = "seminar_2_student";

  /**
   * <p>
   * DDL to create table seminar_2_student
   * </p>
   *
   * <pre>CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);";

  /**
   * <p>
   * DDL to drop table seminar_2_student
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_seminar_2_student_1;DROP TABLE IF EXISTS seminar_2_student;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_seminar_2_student_1;DROP TABLE IF EXISTS seminar_2_student;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>seminarId</code> is associated to table column <code>seminar_id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#seminarId
   */
  public static final String COLUMN_SEMINAR_ID = "seminar_id";

  /**
   * Entity's property <code>studentId</code> is associated to table column <code>student_id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#studentId
   */
  public static final String COLUMN_STUDENT_ID = "student_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_SEMINAR_ID, COLUMN_STUDENT_ID};

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
