package sqlite.kripton41;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanSelectOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanSelectOK
 *  @see Bean01Table
 */
public class DaoBeanSelectOKImpl extends AbstractDao implements DaoBeanSelectOK {
  public DaoBeanSelectOKImpl(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT count(*)>1 FROM bean01 WHERE id=${id} and value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>count(*)>1</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to ${id}
   * @param value
   * 	is binded to ${value}
   *
   * @return single value extracted with query.
   */
  @Override
  public Boolean selectDistance(long id, double value) {
    // build where condition
    String[] args={String.valueOf(id), String.valueOf(value)};

    Logger.info(StringUtils.formatSQL("SELECT count(*)>1 FROM bean01 WHERE id='%s' and value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*)>1 FROM bean01 WHERE id=? and value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Boolean result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0)==0?false:true;
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
    return result;
  }
}
