package com.abubusoft.kripton.example01;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Channel</code>
 * </p>
 *  @see com.abubusoft.kripton.example01.Channel
 */
public class BindChannelCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "uid"
   */
  protected int index0;

  /**
   * Index for column "ownerUid"
   */
  protected int index1;

  /**
   * Index for column "updateTime"
   */
  protected int index2;

  /**
   * Index for column "name"
   */
  protected int index3;

  /**
   * Index for column "id"
   */
  protected int index4;

  /**
   * <p>Constructor</p>
   * @param cursor cursor used to read from database
   */
  BindChannelCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindChannelCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("uid");
    index1=cursor.getColumnIndex("owner_uid");
    index2=cursor.getColumnIndex("update_time");
    index3=cursor.getColumnIndex("name");
    index4=cursor.getColumnIndex("id");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Channel> execute() {

    LinkedList<Channel> resultList=new LinkedList<Channel>();
    Channel resultBean=new Channel();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Channel();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4));}

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
  public void executeListener(OnChannelListener listener) {
    Channel resultBean=new Channel();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.setUid(null);}
        if (index1>=0) { resultBean.setOwnerUid(null);}
        if (index2>=0) { resultBean.setUpdateTime(0L);}
        if (index3>=0) { resultBean.setName(null);}
        if (index4>=0) { resultBean.setId(0L);}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setUid(cursor.getString(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.setOwnerUid(cursor.getString(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.setUpdateTime(cursor.getLong(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.setId(cursor.getLong(index4));}

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
  public static BindChannelCursor create(Cursor cursor) {
    return new BindChannelCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnChannelListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Channel bean, int rowPosition, int rowCount);
  }
}
