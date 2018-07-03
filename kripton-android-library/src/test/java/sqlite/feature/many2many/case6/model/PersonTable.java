package sqlite.feature.many2many.case6.model;

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
   * <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, age INTEGER, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, age INTEGER, name TEXT);";

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
   * Entity's property <code>age</code> is associated to table column <code>age</code>. This costant represents column name.
   *
   *  @see Person#age
   */
  public static final String COLUMN_AGE = "age";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AGE, COLUMN_NAME};

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
