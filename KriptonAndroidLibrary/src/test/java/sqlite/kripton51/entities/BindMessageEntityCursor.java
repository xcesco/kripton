package sqlite.kripton51.entities;

import android.database.Cursor;
import java.util.LinkedList;
import sqlite.kripton51.internal.MessageType;

/**
 * <p>
 * Cursor implementation for entity <code>MessageEntity</code>
 * </p>
 *  @see MessageEntity
 */
public class BindMessageEntityCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "channelId"
   */
  protected int index1;

  /**
   * Index for column "ownerType"
   */
  protected int index2;

  /**
   * Index for column "uid"
   */
  protected int index3;

  /**
   * Index for column "faceUid"
   */
  protected int index4;

  /**
   * Index for column "text"
   */
  protected int index5;

  /**
   * Index for column "ownerUid"
   */
  protected int index6;

  /**
   * Index for column "channelUid"
   */
  protected int index7;

  /**
   * Index for column "updateTime"
   */
  protected int index8;

  /**
   * Index for column "type"
   */
  protected int index9;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindMessageEntityCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindMessageEntityCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("channel_id");
    index2=cursor.getColumnIndex("owner_type");
    index3=cursor.getColumnIndex("uid");
    index4=cursor.getColumnIndex("face_uid");
    index5=cursor.getColumnIndex("text");
    index6=cursor.getColumnIndex("owner_uid");
    index7=cursor.getColumnIndex("channel_uid");
    index8=cursor.getColumnIndex("update_time");
    index9=cursor.getColumnIndex("type");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<MessageEntity> execute() {

    LinkedList<MessageEntity> resultList=new LinkedList<MessageEntity>();
    MessageEntity resultBean=new MessageEntity();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new MessageEntity();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.uid=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.faceUid=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.text=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.ownerUid=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.channelUid=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.updateTime=cursor.getLong(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(cursor.getString(index9));}

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
  public void executeListener(OnMessageEntityListener listener) {
    MessageEntity resultBean=new MessageEntity();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.channelId=0L;}
        if (index2>=0) { resultBean.ownerType=null;}
        if (index3>=0) { resultBean.uid=null;}
        if (index4>=0) { resultBean.faceUid=null;}
        if (index5>=0) { resultBean.text=null;}
        if (index6>=0) { resultBean.ownerUid=null;}
        if (index7>=0) { resultBean.channelUid=null;}
        if (index8>=0) { resultBean.updateTime=0L;}
        if (index9>=0) { resultBean.type=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.channelId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.ownerType=OwnerType.valueOf(cursor.getString(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.uid=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.faceUid=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.text=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.ownerUid=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.channelUid=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.updateTime=cursor.getLong(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.type=MessageType.valueOf(cursor.getString(index9));}

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
  public static BindMessageEntityCursor create(Cursor cursor) {
    return new BindMessageEntityCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnMessageEntityListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(MessageEntity bean, int rowPosition, int rowCount);
  }
}
