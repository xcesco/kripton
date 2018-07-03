package sqlite.kripton84;

import android.database.Cursor;
import java.util.ArrayList;

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
   * Index for column "columnArrayByteType"
   */
  protected int index1;

  /**
   * Index for column "columnArrayChar"
   */
  protected int index2;

  /**
   * Index for column "columnArrayCharType"
   */
  protected int index3;

  /**
   * Index for column "columnBean"
   */
  protected int index4;

  /**
   * Index for column "columnListString"
   */
  protected int index5;

  /**
   * Index for column "columnMapIntegerString"
   */
  protected int index6;

  /**
   * Index for column "param1"
   */
  protected int index7;

  /**
   * Index for column "param2"
   */
  protected int index8;

  /**
   * Index for column "param3"
   */
  protected int index9;

  /**
   * Index for column "param4"
   */
  protected int index10;

  /**
   * Index for column "valueString"
   */
  protected int index11;

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
    index1=cursor.getColumnIndex("column_array_byte_type");
    index2=cursor.getColumnIndex("column_array_char");
    index3=cursor.getColumnIndex("column_array_char_type");
    index4=cursor.getColumnIndex("column_bean");
    index5=cursor.getColumnIndex("column_list_string");
    index6=cursor.getColumnIndex("column_map_integer_string");
    index7=cursor.getColumnIndex("param1");
    index8=cursor.getColumnIndex("param2");
    index9=cursor.getColumnIndex("param3");
    index10=cursor.getColumnIndex("param4");
    index11=cursor.getColumnIndex("value_string");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public ArrayList<Bean84A> execute() {

    ArrayList<Bean84A> resultList=new ArrayList<Bean84A>(cursor.getCount());
    Bean84A resultBean=new Bean84A();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean84A();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnArrayByteType=cursor.getBlob(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index5));}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index6));}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.param1=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.param2=cursor.getString(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.param3=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.param4=cursor.getString(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.valueString=cursor.getString(index11);}

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
        if (index1>=0) { resultBean.columnArrayByteType=null;}
        if (index2>=0) { resultBean.columnArrayChar=null;}
        if (index3>=0) { resultBean.columnArrayCharType=null;}
        if (index4>=0) { resultBean.columnBean=null;}
        if (index5>=0) { resultBean.columnListString=null;}
        if (index6>=0) { resultBean.columnMapIntegerString=null;}
        if (index7>=0) { resultBean.param1=null;}
        if (index8>=0) { resultBean.param2=null;}
        if (index9>=0) { resultBean.param3=null;}
        if (index10>=0) { resultBean.param4=null;}
        if (index11>=0) { resultBean.valueString=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.columnArrayByteType=cursor.getBlob(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index3));}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index5));}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index6));}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.param1=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.param2=cursor.getString(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.param3=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.param4=cursor.getString(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.valueString=cursor.getString(index11);}

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
