package sqlite.kripton84;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean84A</code>
 * </p>
 *  @see Bean84A
 */
public class BindBean84ACursor {
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
   * Index for column "columnBean"
   */
  protected int index5;

  /**
   * Index for column "columnArrayByteType"
   */
  protected int index6;

  /**
   * Index for column "valueString"
   */
  protected int index7;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBean84ACursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean84ACursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("column_list_string");
    index2=cursor.getColumnIndex("column_map_integer_string");
    index3=cursor.getColumnIndex("column_array_char");
    index4=cursor.getColumnIndex("column_array_char_type");
    index5=cursor.getColumnIndex("column_bean");
    index6=cursor.getColumnIndex("column_array_byte_type");
    index7=cursor.getColumnIndex("value_string");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean84A> execute() {

    LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
    Bean84A resultBean=new Bean84A();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean84A();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index5));}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.columnArrayByteType=Bean84ATable.parseColumnArrayByteType(cursor.getBlob(index6));}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.valueString=cursor.getString(index7);}

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
  public void executeListener(OnBean84AListener listener) {
    Bean84A resultBean=new Bean84A();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.columnListString=null;}
        if (index2>=0) { resultBean.columnMapIntegerString=null;}
        if (index3>=0) { resultBean.columnArrayChar=null;}
        if (index4>=0) { resultBean.columnArrayCharType=null;}
        if (index5>=0) { resultBean.columnBean=null;}
        if (index6>=0) { resultBean.columnArrayByteType=null;}
        if (index7>=0) { resultBean.valueString=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index5));}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.columnArrayByteType=Bean84ATable.parseColumnArrayByteType(cursor.getBlob(index6));}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.valueString=cursor.getString(index7);}

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
  public static BindBean84ACursor create(Cursor cursor) {
    return new BindBean84ACursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean84AListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean84A bean, int rowPosition, int rowCount);
  }
}
