package commons.kripton86.test7;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean7</code>, based on interface <code>Dao7</code>
 * </p>
 *
 *  @see Bean7
 *  @see Dao7
 *  @see Bean7Table
 */
public class Dao7Impl extends AbstractDao implements Dao7 {
  public Dao7Impl(BindDS7DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, ida, test FROM bean7 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>ida</dt><dd>is associated to bean's property <strong>ida</strong></dd>
   * 	<dt>test</dt><dd>is associated to bean's property <strong>test</strong></dd>
   * </dl>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean7 selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, ida, test FROM bean7 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, ida, test FROM bean7 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean7 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("ida");
      int index2=cursor.getColumnIndex("test");

      resultBean=new Bean7();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.ida=cursor.getLong(index1); }
      if (!cursor.isNull(index2)) { resultBean.test=cursor.getString(index2); }

    }
    cursor.close();

    return resultBean;
  }
}
