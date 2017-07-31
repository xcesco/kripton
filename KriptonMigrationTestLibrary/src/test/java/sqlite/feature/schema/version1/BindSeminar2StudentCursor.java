package sqlite.feature.schema.version1;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Seminar2Student</code>
 * </p>
 *  @see Seminar2Student
 */
public class BindSeminar2StudentCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "studentId"
   */
  protected int index1;

  /**
   * Index for column "seminarId"
   */
  protected int index2;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindSeminar2StudentCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindSeminar2StudentCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("student_id");
    index2=cursor.getColumnIndex("seminar_id");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Seminar2Student> execute() {

    LinkedList<Seminar2Student> resultList=new LinkedList<Seminar2Student>();
    Seminar2Student resultBean=new Seminar2Student();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Seminar2Student();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.studentId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.seminarId=cursor.getLong(index2);}

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
  public void executeListener(OnSeminar2StudentListener listener) {
    Seminar2Student resultBean=new Seminar2Student();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.studentId=0L;}
        if (index2>=0) { resultBean.seminarId=0L;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.studentId=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.seminarId=cursor.getLong(index2);}

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
  public static BindSeminar2StudentCursor create(Cursor cursor) {
    return new BindSeminar2StudentCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnSeminar2StudentListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Seminar2Student bean, int rowPosition, int rowCount);
  }
}
