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
package sqlite.stack45184504;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>FileBean</code> is associated to table <code>files</code>
 * This class represents table associated to entity.
 * </p>
 *  @see FileBean
 */
public class FileBeanTable implements SQLiteTable {
  
  /** Costant represents typeName of table files. */
  public static final String TABLE_NAME = "files";

  /** <p> DDL to create table files </p>  <pre>CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, content BLOB, content_type TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, content BLOB, content_type TEXT);";

  /** <p> DDL to drop table files </p>  <pre>DROP TABLE IF EXISTS files;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS files;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see FileBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see FileBean#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column name.
   *
   *  @see FileBean#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>contentType</code> is associated to table column <code>content_type</code>. This costant represents column name.
   *
   *  @see FileBean#contentType
   */
  public static final String COLUMN_CONTENT_TYPE = "content_type";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_CONTENT, COLUMN_CONTENT_TYPE};

  /**
   * for attribute content serialization.
   *
   * @param value the value
   * @return the byte[]
   */
  public static byte[] serializeContent(byte[] value) {
    return value;
  }

  /**
   * for attribute content parsing.
   *
   * @param input the input
   * @return the byte[]
   */
  public static byte[] parseContent(byte[] input) {
    return input;
  }

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
