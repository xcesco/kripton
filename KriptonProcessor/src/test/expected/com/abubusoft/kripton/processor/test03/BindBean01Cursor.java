package com.abubusoft.kripton.processor.test03;

import android.database.Cursor;
import com.abubusoft.kripton.common.ProcessorHelper;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean01</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.test03.Bean01
 */
public class BindBean01Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "lista"
   */
  protected int index0;

  /**
   * Index for column "id"
   */
  protected int index1;

  /**
   * Index for column "messageDate"
   */
  protected int index2;

  /**
   * Index for column "messageText"
   */
  protected int index3;

  /**
   * Index for column "beanList"
   */
  protected int index4;

  /**
   * Index for column "value"
   */
  protected int index5;

  /**
   * <p>Constructor</p>
   * @param cursor cursor used to read from database
   */
  BindBean01Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean01Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("lista");
    index1=cursor.getColumnIndex("id");
    index2=cursor.getColumnIndex("message_date");
    index3=cursor.getColumnIndex("message_text");
    index4=cursor.getColumnIndex("bean_list");
    index5=cursor.getColumnIndex("value");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean01> execute() {

    LinkedList<Bean01> resultList=new LinkedList<Bean01>();
    Bean01 resultBean=new Bean01();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean01();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setLista(ProcessorHelper.asCollection(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index0)));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setId(cursor.getLong(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setMessageDate(cursor.getLong(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setMessageText(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setBeanList(ProcessorHelper.asCollection(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index4)));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.setValue(cursor.getLong(index5));}

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
  public void executeListener(OnBean01Listener listener) {
    Bean01 resultBean=new Bean01();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.setLista(null);}
        if (index1>=0) { resultBean.setId(0L);}
        if (index2>=0) { resultBean.setMessageDate(0L);}
        if (index3>=0) { resultBean.setMessageText(null);}
        if (index4>=0) { resultBean.setBeanList(null);}
        if (index5>=0) { resultBean.setValue(0L);}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setLista(ProcessorHelper.asCollection(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index0)));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setId(cursor.getLong(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setMessageDate(cursor.getLong(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setMessageText(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setBeanList(ProcessorHelper.asCollection(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index4)));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.setValue(cursor.getLong(index5));}

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
  public static BindBean01Cursor create(Cursor cursor) {
    return new BindBean01Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean01Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean01 bean, int rowPosition, int rowCount);
  }
}
