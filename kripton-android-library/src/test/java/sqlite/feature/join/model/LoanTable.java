/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.join.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Loan</code> is associated to table <code>loan</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Loan
 */
public class LoanTable implements SQLiteTable {
  
  /** Costant represents typeName of table loan. */
  public static final String TABLE_NAME = "loan";

  /** <p> DDL to create table loan </p>  <pre>CREATE TABLE loan (id INTEGER PRIMARY KEY AUTOINCREMENT, start_time TEXT, end_time TEXT, book_id INTEGER, user_id INTEGER, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id));</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE loan (id INTEGER PRIMARY KEY AUTOINCREMENT, start_time TEXT, end_time TEXT, book_id INTEGER, user_id INTEGER, FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(user_id) REFERENCES user(id));";

  /** <p> DDL to drop table loan </p>  <pre>DROP TABLE IF EXISTS loan;</pre>. */
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

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_START_TIME, COLUMN_END_TIME, COLUMN_BOOK_ID, COLUMN_USER_ID};

  /**
   * Columns array.
   *
   * @return the string[]
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name.
   *
   * @return the string
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
