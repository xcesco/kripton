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
package sqlite.kripton48.entities;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Bean01</code> is associated to table <code>bean01</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean01
 */
public class Bean01Table implements SQLiteTable {
  
  /** Costant represents typeName of table bean01. */
  public static final String TABLE_NAME = "bean01";

  /** <p> DDL to create table bean01 </p>  <pre>CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);";

  /** <p> DDL to drop table bean01 </p>  <pre>DROP TABLE IF EXISTS bean01;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean01;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean01#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean01#text
   */
  public static final String COLUMN_TEXT = "text";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TEXT};

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
