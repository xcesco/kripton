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
package sqlite.feat.includeFields;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Person</code> is associated to table <code>person</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Person
 */
public class PersonTable implements SQLiteTable {
  
  /** Costant represents typeName of table person. */
  public static final String TABLE_NAME = "person";

  /** <p> DDL to create table person </p>  <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT, type_name TEXT); CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_0 on person (birth_city, birth_day);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT, type_name TEXT); CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_0 on person (birth_city, birth_day);";

  /** <p> DDL to drop table person </p>  <pre> DROP INDEX IF EXISTS idx_person_name; DROP INDEX IF EXISTS idx_person_1;DROP TABLE IF EXISTS person;</pre>. */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_person_name; DROP INDEX IF EXISTS idx_person_1;DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Person#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see Person#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>birthCity</code> is associated to table column <code>birth_city</code>. This costant represents column name.
   *
   *  @see Person#birthCity
   */
  public static final String COLUMN_BIRTH_CITY = "birth_city";

  /**
   * Entity's property <code>birthDay</code> is associated to table column <code>birth_day</code>. This costant represents column name.
   *
   *  @see Person#birthDay
   */
  public static final String COLUMN_BIRTH_DAY = "birth_day";

  /**
   * Entity's property <code>typeName</code> is associated to table column <code>type_name</code>. This costant represents column name.
   *
   *  @see Person#typeName
   */
  public static final String COLUMN_TYPE_NAME = "type_name";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME, COLUMN_BIRTH_CITY, COLUMN_BIRTH_DAY, COLUMN_TYPE_NAME};

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
