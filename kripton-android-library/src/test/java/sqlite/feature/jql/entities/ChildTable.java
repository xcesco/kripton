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
package sqlite.feature.jql.entities;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Child</code> is associated to table <code>child</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Child
 */
public class ChildTable implements SQLiteTable {
  
  /** Costant represents typeName of table child. */
  public static final String TABLE_NAME = "child";

  /** <p> DDL to create table child </p>  <pre>CREATE TABLE child (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, parent_id INTEGER, FOREIGN KEY(parent_id) REFERENCES person(_id));</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE child (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, parent_id INTEGER, FOREIGN KEY(parent_id) REFERENCES person(_id));";

  /** <p> DDL to drop table child </p>  <pre>DROP TABLE IF EXISTS child;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS child;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>_id</code>. This costant represents column name.
   *
   *  @see Child#id
   */
  public static final String COLUMN_ID = "_id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Child#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>parentId</code> is associated to table column <code>parent_id</code>. This costant represents column name.
   *
   *  @see Child#parentId
   */
  public static final String COLUMN_PARENT_ID = "parent_id";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_PARENT_ID};

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
