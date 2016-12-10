package sqlite.kripton63;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Bean63</code>
 * </p>
 *  @see Bean63
 */
public class BindBean63Cursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "value"
   */
  protected int index1;

  /**
   * Index for column "valueMapStringByte"
   */
  protected int index2;

  /**
   * Index for column "valueMapEnumByte"
   */
  protected int index3;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindBean63Cursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindBean63Cursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("value");
    index2=cursor.getColumnIndex("value_map_string_byte");
    index3=cursor.getColumnIndex("value_map_enum_byte");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Bean63> execute() {

    LinkedList<Bean63> resultList=new LinkedList<Bean63>();
    Bean63 resultBean=new Bean63();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Bean63();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.value=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3));}

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
  public void executeListener(OnBean63Listener listener) {
    Bean63 resultBean=new Bean63();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.value=null;}
        if (index2>=0) { resultBean.valueMapStringByte=null;}
        if (index3>=0) { resultBean.valueMapEnumByte=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.value=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2));}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3));}

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
  public static BindBean63Cursor create(Cursor cursor) {
    return new BindBean63Cursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnBean63Listener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Bean63 bean, int rowPosition, int rowCount);
  }
}
