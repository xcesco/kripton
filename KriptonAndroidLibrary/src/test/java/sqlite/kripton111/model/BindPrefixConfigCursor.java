package sqlite.kripton111.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>PrefixConfig</code>
 * </p>
 *  @see PrefixConfig
 */
public class BindPrefixConfigCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "defaultCountry"
   */
  protected int index1;

  /**
   * Index for column "dualBillingPrefix"
   */
  protected int index2;

  /**
   * Index for column "enabled"
   */
  protected int index3;

  /**
   * Index for column "dialogTimeout"
   */
  protected int index4;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindPrefixConfigCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindPrefixConfigCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("default_country");
    index2=cursor.getColumnIndex("dual_billing_prefix");
    index3=cursor.getColumnIndex("enabled");
    index4=cursor.getColumnIndex("dialog_timeout");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<PrefixConfig> execute() {

    LinkedList<PrefixConfig> resultList=new LinkedList<PrefixConfig>();
    PrefixConfig resultBean=new PrefixConfig();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new PrefixConfig();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.defaultCountry=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.dualBillingPrefix=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.enabled=cursor.getInt(index3)==0?false:true;}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.dialogTimeout=cursor.getLong(index4);}

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
  public void executeListener(OnPrefixConfigListener listener) {
    PrefixConfig resultBean=new PrefixConfig();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.defaultCountry=null;}
        if (index2>=0) { resultBean.dualBillingPrefix=null;}
        if (index3>=0) { resultBean.enabled=false;}
        if (index4>=0) { resultBean.dialogTimeout=0L;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.defaultCountry=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.dualBillingPrefix=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.enabled=cursor.getInt(index3)==0?false:true;}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.dialogTimeout=cursor.getLong(index4);}

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
  public static BindPrefixConfigCursor create(Cursor cursor) {
    return new BindPrefixConfigCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnPrefixConfigListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(PrefixConfig bean, int rowPosition, int rowCount);
  }
}
