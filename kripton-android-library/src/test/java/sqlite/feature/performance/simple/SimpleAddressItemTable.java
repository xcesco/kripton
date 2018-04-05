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
package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>SimpleAddressItem</code> is associated to table <code>simple_address_item</code>
 * This class represents table associated to entity.
 * </p>
 *  @see SimpleAddressItem
 */
public class SimpleAddressItemTable implements SQLiteTable {
  
  /** Costant represents typeName of table simple_address_item. */
  public static final String TABLE_NAME = "simple_address_item";

  /** <p> DDL to create table simple_address_item </p>  <pre>CREATE TABLE simple_address_item (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, city TEXT, state TEXT, phone INTEGER);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE simple_address_item (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, city TEXT, state TEXT, phone INTEGER);";

  /** <p> DDL to drop table simple_address_item </p>  <pre>DROP TABLE IF EXISTS simple_address_item;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS simple_address_item;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>city</code> is associated to table column <code>city</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#city
   */
  public static final String COLUMN_CITY = "city";

  /**
   * Entity's property <code>state</code> is associated to table column <code>state</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#state
   */
  public static final String COLUMN_STATE = "state";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>phone</code>. This costant represents column name.
   *
   *  @see SimpleAddressItem#phone
   */
  public static final String COLUMN_PHONE = "phone";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_ADDRESS, COLUMN_CITY, COLUMN_STATE, COLUMN_PHONE};

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
