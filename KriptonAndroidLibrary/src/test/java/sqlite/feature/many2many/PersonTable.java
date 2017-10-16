package sqlite.feature.many2many;

/**
 * <p>
 * Entity <code>Person</code> is associated to table <code>persons</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person
 */
public class PersonTable {
  /**
   * Costant represents typeName of table persons
   */
  public static final String TABLE_NAME = "persons";

  /**
   * <p>
   * DDL to create table persons
   * </p>
   *
   * <pre>CREATE TABLE persons (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE persons (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";

  /**
   * <p>
   * DDL to drop table persons
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS persons;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS persons;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";
}
