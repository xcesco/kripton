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
package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>PersonCityErr3</code> is associated to table <code>person_city_err3</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCityErr3
 */
public class PersonCityErr3Table implements SQLiteTable {
  
  /** Costant represents typeName of table person_city_err3. */
  public static final String TABLE_NAME = "person_city_err3";

  /** <p> DDL to create table person_city_err3 </p>  <pre>CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, city_id INTEGER, FOREIGN KEY(person_id) REFERENCES persons(id), FOREIGN KEY(city_id) REFERENCES cities(id));";

  /** <p> DDL to drop table person_city_err3 </p>  <pre>DROP TABLE IF EXISTS person_city_err3;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city_err3;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_PERSON_ID, COLUMN_CITY_ID};

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
