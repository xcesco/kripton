/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.rx.model;

import android.database.Cursor;
import java.util.LinkedList;


/**
 * <p>
 * Cursor implementation for entity <code>Person</code>
 * </p>.
 *
 * @see Person
 */
public class BindPersonCursor {
  
  /** Cursor used to read database. */
  protected Cursor cursor;

  /** Index for column "id". */
  protected int index0;

  /** Index for column "name". */
  protected int index1;

  /** Index for column "age". */
  protected int index2;

  /**
   * <p>Constructor</p>.
   *
   * @param cursor cursor used to read from database
   */
  BindPersonCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>.
   *
   * @param cursor cursor to include
   * @return the bind person cursor
   */
  public BindPersonCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("name");
    index2=cursor.getColumnIndex("age");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Person> execute() {

    LinkedList<Person> resultList=new LinkedList<Person>();
    Person resultBean=new Person();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Person();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.age=cursor.getInt(index2);}

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * Method executed for each row extracted from database. For each row specified listener will be invoked.
   *
   * @param listener listener to invoke for each row
   */
  public void executeListener(OnPersonListener listener) {
    Person resultBean=new Person();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.name=null;}
        if (index2>=0) { resultBean.age=0;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.age=cursor.getInt(index2);}

        listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());
      } while (cursor.moveToNext());
    }
    cursor.close();
  }

  /**
   * <p>Create a binded cursor starting from a cursor</p>.
   *
   * @param cursor to wrap
   * @return the bind person cursor
   */
  public static BindPersonCursor create(Cursor cursor) {
    return new BindPersonCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   *
   * @see OnPersonEvent
   */
  public interface OnPersonListener {
    
    /**
     * Method executed for each row extracted from database.
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Person bean, int rowPosition, int rowCount);
  }
}
