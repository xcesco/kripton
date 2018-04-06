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
 * Entity <code>Seminar2Student</code> is associated to table <code>seminar_2_student</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Seminar2Student
 */
public class Seminar2StudentTable implements SQLiteTable {
  
  /** Costant represents typeName of table seminar_2_student. */
  public static final String TABLE_NAME = "seminar_2_student";

  /** <p> DDL to create table seminar_2_student </p>  <pre>CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, seminar_id INTEGER, FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(seminar_id) REFERENCES seminar(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc, seminar_id desc);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, seminar_id INTEGER, FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(seminar_id) REFERENCES seminar(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc, seminar_id desc);";

  /** <p> DDL to drop table seminar_2_student </p>  <pre> DROP INDEX IF EXISTS idx_seminar_2_student_1;DROP TABLE IF EXISTS seminar_2_student;</pre>. */
  public static final String DROP_TABLE_SQL = " DROP INDEX IF EXISTS idx_seminar_2_student_1;DROP TABLE IF EXISTS seminar_2_student;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>studentId</code> is associated to table column <code>student_id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#studentId
   */
  public static final String COLUMN_STUDENT_ID = "student_id";

  /**
   * Entity's property <code>seminarId</code> is associated to table column <code>seminar_id</code>. This costant represents column name.
   *
   *  @see Seminar2Student#seminarId
   */
  public static final String COLUMN_SEMINAR_ID = "seminar_id";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_STUDENT_ID, COLUMN_SEMINAR_ID};

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
