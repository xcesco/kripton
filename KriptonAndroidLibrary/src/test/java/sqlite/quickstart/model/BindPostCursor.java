package sqlite.quickstart.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Post</code>
 * </p>
 *  @see Post
 */
public class BindPostCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "userId"
   */
  protected int index0;

  /**
   * Index for column "id"
   */
  protected int index1;

  /**
   * Index for column "title"
   */
  protected int index2;

  /**
   * Index for column "body"
   */
  protected int index3;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindPostCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindPostCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("user_id");
    index1=cursor.getColumnIndex("id");
    index2=cursor.getColumnIndex("title");
    index3=cursor.getColumnIndex("body");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Post> execute() {

    LinkedList<Post> resultList=new LinkedList<Post>();
    Post resultBean=new Post();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Post();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.userId=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.title=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.body=cursor.getString(index3);}

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
  public void executeListener(OnPostListener listener) {
    Post resultBean=new Post();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.userId=0L;}
        if (index1>=0) { resultBean.id=0L;}
        if (index2>=0) { resultBean.title=null;}
        if (index3>=0) { resultBean.body=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.userId=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.title=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.body=cursor.getString(index3);}

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
  public static BindPostCursor create(Cursor cursor) {
    return new BindPostCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnPostListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Post bean, int rowPosition, int rowCount);
  }
}
