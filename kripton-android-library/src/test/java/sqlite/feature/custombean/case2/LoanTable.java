package sqlite.feature.custombean.case2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Loan</code> is associated to table <code>loan</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Loan
 */
public class LoanTable implements SQLiteTable {
  /**
   * Costant represents typeName of table loan
   */
  public static final String TABLE_NAME = "loan";

  /**
   * <p>
   * DDL to create table loan
   * </p>
   *
   * <pre>CREATE TABLE loan (id TEXT PRIMARY KEY NOT NULL, book_id TEXT, end_time TEXT, start_time TEXT, user_id TEXT, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id), UNIQUE (book_id, user_id)); CREATE UNIQUE INDEX idx_loan_0 on loan (book_id, user_id);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE loan (id TEXT PRIMARY KEY NOT NULL, book_id TEXT, end_time TEXT, start_time TEXT, user_id TEXT, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id), UNIQUE (book_id, user_id)); CREATE UNIQUE INDEX idx_loan_0 on loan (book_id, user_id);";

  /**
   * <p>
   * DDL to drop table loan
   * </p>
   *
   * <pre> DROP INDEX IF EXISTS idx_loan_1;DROP TABLE IF EXISTS loan;</pre>
   */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_loan_1;DROP TABLE IF EXISTS loan;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Loan#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>bookId</code> is associated to table column <code>book_id</code>. This costant represents column name.
   *
   *  @see Loan#bookId
   */
  public static final String COLUMN_BOOK_ID = "book_id";

  /**
   * Entity's property <code>endTime</code> is associated to table column <code>end_time</code>. This costant represents column name.
   *
   *  @see Loan#endTime
   */
  public static final String COLUMN_END_TIME = "end_time";

  /**
   * Entity's property <code>startTime</code> is associated to table column <code>start_time</code>. This costant represents column name.
   *
   *  @see Loan#startTime
   */
  public static final String COLUMN_START_TIME = "start_time";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see Loan#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BOOK_ID, COLUMN_END_TIME, COLUMN_START_TIME, COLUMN_USER_ID};

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
