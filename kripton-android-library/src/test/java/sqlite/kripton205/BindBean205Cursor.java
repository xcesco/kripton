package sqlite.kripton205;

import android.database.Cursor;
import java.util.ArrayList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean205</code>
 * </p>
 *  @see Bean205
 */
public class BindBean205Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "name"
   */
  protected int index1;

  /**
   * Index for column "surname"
   */
  protected int index2;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBean205Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean205Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("name");
    index2=cursor.getColumnIndex("surname");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public ArrayList<Bean205> execute() {

    ArrayList<Bean205> resultList=new ArrayList<Bean205>(cursor.getCount());
    Bean205 resultBean=new Bean205();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean205();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2);}

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
  public void executeListener(OnBean205Listener listener) {
    Bean205 resultBean=new Bean205();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.setId(0L);}
        if (index1>=0) { resultBean.name=null;}
        if (index2>=0) { resultBean.surname=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0));}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2);}

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
  public static BindBean205Cursor create(Cursor cursor) {
    return new BindBean205Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean205Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean205 bean, int rowPosition, int rowCount);
  }
}
