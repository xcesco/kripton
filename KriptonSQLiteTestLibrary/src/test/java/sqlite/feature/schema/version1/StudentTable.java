package sqlite.feature.schema.version1;

import java.lang.String;

/**
 * <p>
 * Entity <code>Student</code> is associated to table <code>student</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Student
 */
public class StudentTable {
  /**
   * Costant represents typeName of table student
   */
  public static final String TABLE_NAME = "student";

  /**
   * <p>
   * DDL to create table student
   * </p>
   *
   * <pre>CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);";

  /**
   * <p>
   * DDL to drop table student
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS student;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS student;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Student#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see Student#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>location</code> is associated to table column <code>location</code>. This costant represents column typeName.
   *
   *  @see Student#location
   */
  public static final String COLUMN_LOCATION = "location";
}
