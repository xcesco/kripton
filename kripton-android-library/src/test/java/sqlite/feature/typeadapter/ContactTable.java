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
package sqlite.feature.typeadapter;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Contact</code> is associated to table <code>contact</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Contact
 */
public class ContactTable implements SQLiteTable {
  
  /** Costant represents typeName of table contact. */
  public static final String TABLE_NAME = "contact";

  /** <p> DDL to create table contact </p>  <pre>CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, surname TEXT, birth_day INTEGER, password BLOB, type INTEGER, update_date TEXT, update_time TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, surname TEXT, birth_day INTEGER, password BLOB, type INTEGER, update_date TEXT, update_time TEXT);";

  /** <p> DDL to drop table contact </p>  <pre>DROP TABLE IF EXISTS contact;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS contact;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Contact#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Contact#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Contact#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";

  /**
   * Entity's property <code>password</code> is associated to table column <code>password</code>. This costant represents column name.
   *
   *  @see Contact#password
   */
  public static final String COLUMN_PASSWORD = "password";

  /**
   * Entity's property <code>type</code> is associated to table column <code>type</code>. This costant represents column name.
   *
   *  @see Contact#type
   */
  public static final String COLUMN_TYPE = "type";

  /**
   * Entity's property <code>updateDate</code> is associated to table column <code>update_date</code>. This costant represents column name.
   *
   *  @see Contact#updateDate
   */
  public static final String COLUMN_UPDATE_DATE = "update_date";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see Contact#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_SURNAME, COLUMN_BIRTH_DAY, COLUMN_PASSWORD, COLUMN_TYPE, COLUMN_UPDATE_DATE, COLUMN_UPDATE_TIME};

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
