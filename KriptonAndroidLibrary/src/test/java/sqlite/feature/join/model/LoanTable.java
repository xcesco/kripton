package sqlite.feature.join.model;

/**
 * <p>
 * Entity <code>Loan</code> is associated to table <code>loan</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Loan
 */
public class LoanTable {
  /**
   * Costant represents typeName of table loan
   */
  public static final String TABLE_NAME = "loan";

  /**
   * <p>
   * DDL to create table loan
   * </p>
   *
   * <pre>CREATE TABLE loan (id INTEGER PRIMARY KEY AUTOINCREMENT, start_time TEXT, end_time TEXT, book_id INTEGER, user_id INTEGER, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE loan (id INTEGER PRIMARY KEY AUTOINCREMENT, start_time TEXT, end_time TEXT, book_id INTEGER, user_id INTEGER, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id));";

  /**
   * <p>
   * DDL to drop table loan
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS loan;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS loan;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Loan#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>startTime</code> is associated to table column <code>start_time</code>. This costant represents column name.
   *
   *  @see Loan#startTime
   */
  public static final String COLUMN_START_TIME = "start_time";

  /**
   * Entity's property <code>endTime</code> is associated to table column <code>end_time</code>. This costant represents column name.
   *
   *  @see Loan#endTime
   */
  public static final String COLUMN_END_TIME = "end_time";

  /**
   * Entity's property <code>bookId</code> is associated to table column <code>book_id</code>. This costant represents column name.
   *
   *  @see Loan#bookId
   */
  public static final String COLUMN_BOOK_ID = "book_id";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see Loan#userId
   */
  public static final String COLUMN_USER_ID = "user_id";
}
