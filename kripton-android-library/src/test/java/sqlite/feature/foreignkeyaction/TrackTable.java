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
package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Track</code> is associated to table <code>track</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Track
 */
public class TrackTable implements SQLiteTable {
  
  /** Costant represents typeName of table track. */
  public static final String TABLE_NAME = "track";

  /** <p> DDL to create table track </p>  <pre>CREATE TABLE track (id INTEGER PRIMARY KEY AUTOINCREMENT, album_id INTEGER, FOREIGN KEY(album_id) REFERENCES album(id) ON DELETE CASCADE ON UPDATE SET NULL);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE track (id INTEGER PRIMARY KEY AUTOINCREMENT, album_id INTEGER, FOREIGN KEY(album_id) REFERENCES album(id) ON DELETE CASCADE ON UPDATE SET NULL);";

  /** <p> DDL to drop table track </p>  <pre>DROP TABLE IF EXISTS track;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS track;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Track#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>albumId</code> is associated to table column <code>album_id</code>. This costant represents column name.
   *
   *  @see Track#albumId
   */
  public static final String COLUMN_ALBUM_ID = "album_id";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_ALBUM_ID};

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
