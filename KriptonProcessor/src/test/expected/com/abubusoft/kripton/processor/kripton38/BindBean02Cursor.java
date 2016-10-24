package com.abubusoft.kripton.processor.kripton38;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean02</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton38.Bean02
 */
public class BindBean02Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "text"
   */
  protected int index1;

  /**
   * <p>Constructor</p>
   * @param cursor cursor used to read from database
   */
  BindBean02Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean02Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("text");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean02> execute() {

    LinkedList<Bean02> resultList=new LinkedList<Bean02>();
    Bean02 resultBean=new Bean02();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean02();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1));}

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
  public void executeListener(OnBean02Listener listener) {
    Bean02 resultBean=new Bean02();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.setId(0L);}
        if (index1>=0) { resultBean.setText(null);}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1));}

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
  public static BindBean02Cursor create(Cursor cursor) {
    return new BindBean02Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean02Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean02 bean, int rowPosition, int rowCount);
  }
}
