package sqlite.quickstart.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>User</code>
 * </p>
 *  @see User
 */
public class BindUserCursor {
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
   * Index for column "username"
   */
  protected int index2;

  /**
   * Index for column "email"
   */
  protected int index3;

  /**
   * Index for column "address"
   */
  protected int index4;

  /**
   * Index for column "phone"
   */
  protected int index5;

  /**
   * Index for column "website"
   */
  protected int index6;

  /**
   * Index for column "company"
   */
  protected int index7;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindUserCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindUserCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("name");
    index2=cursor.getColumnIndex("username");
    index3=cursor.getColumnIndex("email");
    index4=cursor.getColumnIndex("address");
    index5=cursor.getColumnIndex("phone");
    index6=cursor.getColumnIndex("website");
    index7=cursor.getColumnIndex("company");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<User> execute() {

    LinkedList<User> resultList=new LinkedList<User>();
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new User();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.username=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.email=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.website=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7));}

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
  public void executeListener(OnUserListener listener) {
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.name=null;}
        if (index2>=0) { resultBean.username=null;}
        if (index3>=0) { resultBean.email=null;}
        if (index4>=0) { resultBean.address=null;}
        if (index5>=0) { resultBean.phone=null;}
        if (index6>=0) { resultBean.website=null;}
        if (index7>=0) { resultBean.company=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.name=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.username=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.email=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4));}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.website=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7));}

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
  public static BindUserCursor create(Cursor cursor) {
    return new BindUserCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnUserListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(User bean, int rowPosition, int rowCount);
  }
}
