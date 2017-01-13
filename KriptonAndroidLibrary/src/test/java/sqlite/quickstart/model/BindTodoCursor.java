package sqlite.quickstart.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Todo</code>
 * </p>
 *  @see Todo
 */
public class BindTodoCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "userId"
   */
  protected int index1;

  /**
   * Index for column "title"
   */
  protected int index2;

  /**
   * Index for column "completed"
   */
  protected int index3;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindTodoCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindTodoCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("user_id");
    index2=cursor.getColumnIndex("title");
    index3=cursor.getColumnIndex("completed");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Todo> execute() {

    LinkedList<Todo> resultList=new LinkedList<Todo>();
    Todo resultBean=new Todo();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Todo();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.userId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.title=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.completed=cursor.getInt(index3)==0?false:true;}

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
  public void executeListener(OnTodoListener listener) {
    Todo resultBean=new Todo();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.userId=0L;}
        if (index2>=0) { resultBean.title=null;}
        if (index3>=0) { resultBean.completed=false;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.userId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.title=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.completed=cursor.getInt(index3)==0?false:true;}

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
  public static BindTodoCursor create(Cursor cursor) {
    return new BindTodoCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnTodoListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Todo bean, int rowPosition, int rowCount);
  }
}
