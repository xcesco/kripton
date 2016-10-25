package com.abubusoft.kripton.processor.kripton52;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.StringUtil;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton52.Bean01
 *  @see com.abubusoft.kripton.processor.kripton52.DaoBean01
 *  @see com.abubusoft.kripton.processor.kripton52.Bean01Table
 */
public class BindDaoBean01 extends AbstractDao implements DaoBean01 {
  public BindDaoBean01(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT count(*) FROM bean01 WHERE id=${uid} and longValue=${longValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[uid, longValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[count(*)]</pre>
   *
   * @param uid
   * @param longValue
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectLongSingle(long uid, long[] longValue) {
    // build where condition
    String[] args={String.valueOf(uid), (longValue==null?null:DaoHelper.toString(CollectionUtility.toList(longValue, ArrayList.class)))};

    Logger.info(StringUtil.formatSQL("SELECT count(*) FROM bean01 WHERE id='%s' and long_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*) FROM bean01 WHERE id=? and long_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
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
