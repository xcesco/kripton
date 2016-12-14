package commons.kripton86.test7;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean7</code> is associated to table <code>bean7</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean7
 */
public class Bean7Table {
  /**
   * Costant represents name of table bean7
   */
  public static final String TABLE_NAME = "bean7";

  /**
   * <p>
   * DDL to create table bean7
   * </p>
   *
   * <pre>CREATE TABLE bean7 (id INTEGER, ida INTEGER, test TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean7 (id INTEGER, ida INTEGER, test TEXT);";

  /**
   * <p>
   * DDL to drop table bean7
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean7;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean7;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean7#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>ida</code> is associated to table column <code>ida</code>. This costant represents column name.
   *
   *  @see Bean7#ida
   */
  public static final String COLUMN_IDA = "ida";

  /**
   * Entity's property <code>test</code> is associated to table column <code>test</code>. This costant represents column name.
   *
   *  @see Bean7#test
   */
  public static final String COLUMN_TEST = "test";
}
