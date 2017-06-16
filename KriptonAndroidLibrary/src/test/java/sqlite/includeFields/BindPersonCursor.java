package sqlite.includeFields;

import android.database.Cursor;
import com.abubusoft.kripton.common.DateUtils;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>Person</code>
 * </p>
 *  @see Person
 */
public class BindPersonCursor {
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
   * Index for column "birthCity"
   */
  protected int index3;

  /**
   * Index for column "birthDay"
   */
  protected int index4;

  /**
   * Index for column "typeName"
   */
  protected int index5;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindPersonCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindPersonCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("name");
    index2=cursor.getColumnIndex("surname");
    index3=cursor.getColumnIndex("birth_city");
    index4=cursor.getColumnIndex("birth_day");
    index5=cursor.getColumnIndex("type_name");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<Person> execute() {

    LinkedList<Person> resultList=new LinkedList<Person>();
    Person resultBean=new Person();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new Person();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.typeName=cursor.getString(index5);}

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
  public void executeListener(OnPersonListener listener) {
    Person resultBean=new Person();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.name=null;}
        if (index2>=0) { resultBean.surname=null;}
        if (index3>=0) { resultBean.birthCity=null;}
        if (index4>=0) { resultBean.birthDay=null;}
        if (index5>=0) { resultBean.typeName=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.typeName=cursor.getString(index5);}

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
  public static BindPersonCursor create(Cursor cursor) {
    return new BindPersonCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnPersonListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(Person bean, int rowPosition, int rowCount);
  }
}
