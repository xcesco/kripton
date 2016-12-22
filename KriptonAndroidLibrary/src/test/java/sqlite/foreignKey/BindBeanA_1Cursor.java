package sqlite.foreignKey;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>BeanA_1</code>
 * </p>
 *  @see BeanA_1
 */
public class BindBeanA_1Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "beanA2Id"
   */
  protected int index1;

  /**
   * Index for column "valueString"
   */
  protected int index2;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBeanA_1Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBeanA_1Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("bean_a2_id");
    index2=cursor.getColumnIndex("value_string");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<BeanA_1> execute() {

    LinkedList<BeanA_1> resultList=new LinkedList<BeanA_1>();
    BeanA_1 resultBean=new BeanA_1();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new BeanA_1();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.beanA2Id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2);}

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
  public void executeListener(OnBeanA_1Listener listener) {
    BeanA_1 resultBean=new BeanA_1();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.beanA2Id=0L;}
        if (index2>=0) { resultBean.valueString=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.beanA2Id=cursor.getLong(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2);}

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
  public static BindBeanA_1Cursor create(Cursor cursor) {
    return new BindBeanA_1Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBeanA_1Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(BeanA_1 bean, int rowPosition, int rowCount);
  }
}
