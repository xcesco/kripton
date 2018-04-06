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
package sqlite.feature.foreignKey;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>BeanA_3</code> is associated to table <code>bean_a_3</code>
 * This class represents table associated to entity.
 * </p>
 *  @see BeanA_3
 */
public class BeanA_3Table implements SQLiteTable {
  
  /** Costant represents typeName of table bean_a_3. */
  public static final String TABLE_NAME = "bean_a_3";

  /** <p> DDL to create table bean_a_3 </p>  <pre>CREATE TABLE bean_a_3 (pk INTEGER PRIMARY KEY AUTOINCREMENT, value_string2 TEXT);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_a_3 (pk INTEGER PRIMARY KEY AUTOINCREMENT, value_string2 TEXT);";

  /** <p> DDL to drop table bean_a_3 </p>  <pre>DROP TABLE IF EXISTS bean_a_3;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_a_3;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>pk</code>. This costant represents column name.
   *
   *  @see BeanA_3#id
   */
  public static final String COLUMN_ID = "pk";

  /**
   * Entity's property <code>valueString2</code> is associated to table column <code>value_string2</code>. This costant represents column name.
   *
   *  @see BeanA_3#valueString2
   */
  public static final String COLUMN_VALUE_STRING2 = "value_string2";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_VALUE_STRING2};

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
