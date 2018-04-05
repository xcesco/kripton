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
package sqlite.quickstart.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Comment</code> is associated to table <code>comment</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Comment
 */
public class CommentTable implements SQLiteTable {
  
  /** Costant represents typeName of table comment. */
  public static final String TABLE_NAME = "comment";

  /** <p> DDL to create table comment </p>  <pre>CREATE TABLE comment (post_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, body TEXT, FOREIGN KEY(post_id) REFERENCES post(id));</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE comment (post_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, body TEXT, FOREIGN KEY(post_id) REFERENCES post(id));";

  /** <p> DDL to drop table comment </p>  <pre>DROP TABLE IF EXISTS comment;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS comment;";

  /**
   * Entity's property <code>postId</code> is associated to table column <code>post_id</code>. This costant represents column name.
   *
   *  @see Comment#postId
   */
  public static final String COLUMN_POST_ID = "post_id";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Comment#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Comment#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>email</code> is associated to table column <code>email</code>. This costant represents column name.
   *
   *  @see Comment#email
   */
  public static final String COLUMN_EMAIL = "email";

  /**
   * Entity's property <code>body</code> is associated to table column <code>body</code>. This costant represents column name.
   *
   *  @see Comment#body
   */
  public static final String COLUMN_BODY = "body";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_POST_ID, COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_BODY};

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
