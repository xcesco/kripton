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
package sqlite.feature.javadoc;

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

  /** <p> DDL to create table person </p>  <pre>CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, person_name TEXT, person_surname TEXT, student INTEGER);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, person_name TEXT, person_surname TEXT, student INTEGER);";

  /** <p> DDL to drop table person </p>  <pre>DROP TABLE IF EXISTS person;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Person#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personName</code> is associated to table column <code>person_name</code>. This costant represents column name.
   *
   *  @see Person#personName
   */
  public static final String COLUMN_PERSON_NAME = "person_name";

  /**
   * Entity's property <code>personSurname</code> is associated to table column <code>person_surname</code>. This costant represents column name.
   *
   *  @see Person#personSurname
   */
  public static final String COLUMN_PERSON_SURNAME = "person_surname";

  /**
   * Entity's property <code>student</code> is associated to table column <code>student</code>. This costant represents column name.
   *
   *  @see Person#student
   */
  public static final String COLUMN_STUDENT = "student";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_PERSON_NAME, COLUMN_PERSON_SURNAME, COLUMN_STUDENT};

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
