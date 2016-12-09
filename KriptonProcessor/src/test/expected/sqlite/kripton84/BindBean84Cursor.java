package sqlite.kripton84;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean84</code>
 * </p>
 *  @see Bean84
 */
public class BindBean84Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "columnListString"
   */
  protected int index1;

  /**
   * Index for column "columnMapIntegerString"
   */
  protected int index2;

  /**
   * Index for column "columnArrayChar"
   */
  protected int index3;

  /**
   * Index for column "columnArrayCharType"
   */
  protected int index4;

  /**
   * Index for column "columnArrayByteType"
   */
  protected int index5;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBean84Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean84Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("column_list_string");
    index2=cursor.getColumnIndex("column_map_integer_string");
    index3=cursor.getColumnIndex("column_array_char");
    index4=cursor.getColumnIndex("column_array_char_type");
    index5=cursor.getColumnIndex("column_array_byte_type");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean84> execute() {

    LinkedList<Bean84> resultList=new LinkedList<Bean84>();
    Bean84 resultBean=new Bean84();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean84();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnListString=Bean84Table.parseColumnListString(cursor.getBlob(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84Table.parseColumnMapIntegerString(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84Table.parseColumnArrayChar(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84Table.parseColumnArrayCharType(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnArrayByteType=cursor.getBlob(index5);}

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
  public void executeListener(OnBean84Listener listener) {
    Bean84 resultBean=new Bean84();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.columnListString=null;}
        if (index2>=0) { resultBean.columnMapIntegerString=null;}
        if (index3>=0) { resultBean.columnArrayChar=null;}
        if (index4>=0) { resultBean.columnArrayCharType=null;}
        if (index5>=0) { resultBean.columnArrayByteType=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnListString=Bean84Table.parseColumnListString(cursor.getBlob(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84Table.parseColumnMapIntegerString(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84Table.parseColumnArrayChar(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84Table.parseColumnArrayCharType(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnArrayByteType=cursor.getBlob(index5);}

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
  public static BindBean84Cursor create(Cursor cursor) {
    return new BindBean84Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean84Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean84 bean, int rowPosition, int rowCount);
  }
}
