package sqlite.kripton111.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>PhoneNumber</code>
 * </p>
 *  @see PhoneNumber
 */
public class BindPhoneNumberCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "action"
   */
  protected int index1;

  /**
   * Index for column "number"
   */
  protected int index2;

  /**
   * Index for column "countryCode"
   */
  protected int index3;

  /**
   * Index for column "contactName"
   */
  protected int index4;

  /**
   * Index for column "contactId"
   */
  protected int index5;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindPhoneNumberCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindPhoneNumberCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("action");
    index2=cursor.getColumnIndex("number");
    index3=cursor.getColumnIndex("country_code");
    index4=cursor.getColumnIndex("contact_name");
    index5=cursor.getColumnIndex("contact_id");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<PhoneNumber> execute() {

    LinkedList<PhoneNumber> resultList=new LinkedList<PhoneNumber>();
    PhoneNumber resultBean=new PhoneNumber();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new PhoneNumber();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.number=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5);}

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
  public void executeListener(OnPhoneNumberListener listener) {
    PhoneNumber resultBean=new PhoneNumber();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.action=null;}
        if (index2>=0) { resultBean.number=null;}
        if (index3>=0) { resultBean.countryCode=null;}
        if (index4>=0) { resultBean.contactName=null;}
        if (index5>=0) { resultBean.contactId=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1));}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.number=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5);}

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
  public static BindPhoneNumberCursor create(Cursor cursor) {
    return new BindPhoneNumberCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnPhoneNumberListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(PhoneNumber bean, int rowPosition, int rowCount);
  }
}
