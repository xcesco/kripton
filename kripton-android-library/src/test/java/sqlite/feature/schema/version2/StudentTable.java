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
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Student</code> is associated to table <code>student</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Student
 */
public class StudentTable implements SQLiteTable {
  
  /** Costant represents typeName of table student. */
  public static final String TABLE_NAME = "student";

  /** <p> DDL to create table student </p>  <pre>CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);";

  /** <p> DDL to drop table student </p>  <pre>DROP TABLE IF EXISTS student;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS student;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Student#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Student#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>location</code> is associated to table column <code>location</code>. This costant represents column name.
   *
   *  @see Student#location
   */
  public static final String COLUMN_LOCATION = "location";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION};

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
