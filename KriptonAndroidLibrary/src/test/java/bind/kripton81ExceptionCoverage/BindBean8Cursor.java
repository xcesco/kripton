package bind.kripton81ExceptionCoverage;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean8</code>
 * </p>
 *  @see Bean8
 */
public class BindBean8Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "ignore2"
   */
  protected int index1;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBean8Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean8Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("ignore2");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean8> execute() {

    LinkedList<Bean8> resultList=new LinkedList<Bean8>();
    Bean8 resultBean=new Bean8();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean8();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.ignore2=cursor.getString(index1);}

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
  public void executeListener(OnBean8Listener listener) {
    Bean8 resultBean=new Bean8();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.ignore2=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.ignore2=cursor.getString(index1);}

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
  public static BindBean8Cursor create(Cursor cursor) {
    return new BindBean8Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean8Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean8 bean, int rowPosition, int rowCount);
  }
}
