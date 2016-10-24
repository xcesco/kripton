/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.kripton38;

import android.database.Cursor;
import com.abubusoft.kripton.common.DateUtil;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean05</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton38.Bean05
 */
public class BindBean05Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "pk"
   */
  protected int index0;

  /**
   * Index for column "number"
   */
  protected int index1;

  /**
   * Index for column "beanType"
   */
  protected int index2;

  /**
   * Index for column "text"
   */
  protected int index3;

  /**
   * Index for column "content"
   */
  protected int index4;

  /**
   * Index for column "creationTime"
   */
  protected int index5;

  /**
   * <p>Constructor</p>
   * @param cursor cursor used to read from database
   */
  BindBean05Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean05Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("pk");
    index1=cursor.getColumnIndex("number");
    index2=cursor.getColumnIndex("bean_type");
    index3=cursor.getColumnIndex("text");
    index4=cursor.getColumnIndex("content");
    index5=cursor.getColumnIndex("creation_time");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean05> execute() {

    LinkedList<Bean05> resultList=new LinkedList<Bean05>();
    Bean05 resultBean=new Bean05();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean05();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2)));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5)));}

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
  public void executeListener(OnBean05Listener listener) {
    Bean05 resultBean=new Bean05();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.setPk(0L);}
        if (index1>=0) { resultBean.setNumber(0L);}
        if (index2>=0) { resultBean.setBeanType(null);}
        if (index3>=0) { resultBean.setText(null);}
        if (index4>=0) { resultBean.setContent(null);}
        if (index5>=0) { resultBean.setCreationTime(null);}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2)));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5)));}

        listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());
      } while (cursor.moveToNext());
    }
    cursor.close();
  }

  /**
   * <p>Create a binded cursor starting from a cursor</p>
   *
   * @param cursor to wrap
   */
  public static BindBean05Cursor create(Cursor cursor) {
    return new BindBean05Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean05Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean05 bean, int rowPosition, int rowCount);
  }
}
