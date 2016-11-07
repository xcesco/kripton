package com.abubusoft.kripton.tutorial;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>User</code>
 * </p>
 *  @see User
 */
public class BindUserCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "email"
   */
  protected int index1;

  /**
   * Index for column "name"
   */
  protected int index2;

  /**
   * Index for column "surname"
   */
  protected int index3;

  /**
   * Index for column "username"
   */
  protected int index4;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindUserCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindUserCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("email");
    index2=cursor.getColumnIndex("name");
    index3=cursor.getColumnIndex("surname");
    index4=cursor.getColumnIndex("username");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<User> execute() {

    LinkedList<User> resultList=new LinkedList<User>();
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new User();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.email=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.name=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.surname=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.username=cursor.getString(index4);}

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
  public void executeListener(OnUserListener listener) {
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.email=null;}
        if (index2>=0) { resultBean.name=null;}
        if (index3>=0) { resultBean.surname=null;}
        if (index4>=0) { resultBean.username=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.email=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.name=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.surname=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.username=cursor.getString(index4);}

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
  public static BindUserCursor create(Cursor cursor) {
    return new BindUserCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnUserListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(User bean, int rowPosition, int rowCount);
  }
}
