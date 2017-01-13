package sqlite.quickstart.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Comment</code>
 * </p>
 *  @see Comment
 */
public class BindCommentCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "postId"
   */
  protected int index0;

  /**
   * Index for column "id"
   */
  protected int index1;

  /**
   * Index for column "name"
   */
  protected int index2;

  /**
   * Index for column "email"
   */
  protected int index3;

  /**
   * Index for column "body"
   */
  protected int index4;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindCommentCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindCommentCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("post_id");
    index1=cursor.getColumnIndex("id");
    index2=cursor.getColumnIndex("name");
    index3=cursor.getColumnIndex("email");
    index4=cursor.getColumnIndex("body");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Comment> execute() {

    LinkedList<Comment> resultList=new LinkedList<Comment>();
    Comment resultBean=new Comment();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Comment();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.postId=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.name=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.email=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.body=cursor.getString(index4);}

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
  public void executeListener(OnCommentListener listener) {
    Comment resultBean=new Comment();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.postId=0L;}
        if (index1>=0) { resultBean.id=0L;}
        if (index2>=0) { resultBean.name=null;}
        if (index3>=0) { resultBean.email=null;}
        if (index4>=0) { resultBean.body=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.postId=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.name=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.email=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.body=cursor.getString(index4);}

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
  public static BindCommentCursor create(Cursor cursor) {
    return new BindCommentCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnCommentListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Comment bean, int rowPosition, int rowCount);
  }
}
