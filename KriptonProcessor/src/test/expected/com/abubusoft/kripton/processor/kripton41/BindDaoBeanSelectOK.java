package com.abubusoft.kripton.processor.kripton41;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanSelectOK</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01
 *  @see com.abubusoft.kripton.processor.kripton41.DaoBeanSelectOK
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01Table
 */
public class BindDaoBeanSelectOK extends AbstractDao implements DaoBeanSelectOK {
  public BindDaoBeanSelectOK(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT count(*)>1 FROM bean01 WHERE id=${id} and value=${value}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id, value]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[count(*)>1]</pre>
   *
   * @param id
   * @param value
   *
   * @return single value extracted with query.
   */
  @Override
  public Boolean selectDistance(long id, double value) {
    // build where condition
    String[] args={String.valueOf(id), String.valueOf(value)};

    Logger.info(StringUtil.formatSQL("SELECT count(*)>1 FROM bean01 WHERE id='%s' and value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*)>1 FROM bean01 WHERE id=? and value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Boolean result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0)==0?false:true;
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }
}
