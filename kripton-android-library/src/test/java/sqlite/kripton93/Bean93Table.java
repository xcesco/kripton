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
package sqlite.kripton93;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Bean93</code> is associated to table <code>bean93</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean93
 */
public class Bean93Table implements SQLiteTable {
  
  /** Costant represents typeName of table bean93. */
  public static final String TABLE_NAME = "bean93";

  /** <p> DDL to create table bean93 </p>  <pre>CREATE TABLE bean93 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, type_name TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean93 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, type_name TEXT);";

  /** <p> DDL to drop table bean93 </p>  <pre>DROP TABLE IF EXISTS bean93;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean93;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean93#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Bean93#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Bean93#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>typeName</code> is associated to table column <code>type_name</code>. This costant represents column name.
   *
   *  @see Bean93#typeName
   */
  public static final String COLUMN_TYPE_NAME = "type_name";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME, COLUMN_TYPE_NAME};

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
