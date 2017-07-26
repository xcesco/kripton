package bind.kripton81ExceptionCoverage;

import android.database.Cursor;
import com.abubusoft.kripton.common.DateUtils;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Error16Bean</code>
 * </p>
 *  @see Error16Bean
 */
public class BindError16BeanCursor {
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
   * Index for column "date"
   */
  protected int index2;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindError16BeanCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindError16BeanCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("name");
    index2=cursor.getColumnIndex("date");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Error16Bean> execute() {

    LinkedList<Error16Bean> resultList=new LinkedList<Error16Bean>();
    Error16Bean resultBean=new Error16Bean();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Error16Bean();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.date=DateUtils.read(cursor.getString(index2));}

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
  public void executeListener(OnError16BeanListener listener) {
    Error16Bean resultBean=new Error16Bean();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.name=null;}
        if (index2>=0) { resultBean.date=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.date=DateUtils.read(cursor.getString(index2));}

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
  public static BindError16BeanCursor create(Cursor cursor) {
    return new BindError16BeanCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnError16BeanListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Error16Bean bean, int rowPosition, int rowCount);
  }
}
