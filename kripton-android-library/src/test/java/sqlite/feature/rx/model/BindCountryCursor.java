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
 * Cursor implementation for entity <code>Country</code>
 * </p>.
 *
 * @see Country
 */
public class BindCountryCursor {
  
  /** Cursor used to read database. */
  protected Cursor cursor;

  /** Index for column "id". */
  protected int index0;

  /** Index for column "area". */
  protected int index1;

  /** Index for column "code". */
  protected int index2;

  /** Index for column "callingCode". */
  protected int index3;

  /** Index for column "region". */
  protected int index4;

  /** Index for column "name". */
  protected int index5;

  /** Index for column "translatedName". */
  protected int index6;

  /**
   * <p>Constructor</p>.
   *
   * @param cursor cursor used to read from database
   */
  BindCountryCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>.
   *
   * @param cursor cursor to include
   * @return the bind country cursor
   */
  public BindCountryCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("area");
    index2=cursor.getColumnIndex("code");
    index3=cursor.getColumnIndex("calling_code");
    index4=cursor.getColumnIndex("region");
    index5=cursor.getColumnIndex("name");
    index6=cursor.getColumnIndex("translated_name");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Country> execute() {

    LinkedList<Country> resultList=new LinkedList<Country>();
    Country resultBean=new Country();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Country();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.code=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.callingCode=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.region=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.name=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6));}

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
  public void executeListener(OnCountryListener listener) {
    Country resultBean=new Country();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.area=0L;}
        if (index2>=0) { resultBean.code=null;}
        if (index3>=0) { resultBean.callingCode=null;}
        if (index4>=0) { resultBean.region=null;}
        if (index5>=0) { resultBean.name=null;}
        if (index6>=0) { resultBean.translatedName=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.code=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.callingCode=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.region=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.name=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6));}

        listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());
      } while (cursor.moveToNext());
    }
    cursor.close();
  }

  /**
   * <p>Create a binded cursor starting from a cursor</p>.
   *
   * @param cursor to wrap
   * @return the bind country cursor
   */
  public static BindCountryCursor create(Cursor cursor) {
    return new BindCountryCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   *
   * @see OnCountryEvent
   */
  public interface OnCountryListener {
    
    /**
     * Method executed for each row extracted from database.
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Country bean, int rowPosition, int rowCount);
  }
}
