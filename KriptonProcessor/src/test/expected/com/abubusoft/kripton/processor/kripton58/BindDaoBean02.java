package com.abubusoft.kripton.processor.kripton58;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean02</code>, based on interface <code>DaoBean02</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.Bean02
 *  @see com.abubusoft.kripton.processor.kripton58.DaoBean02
 *  @see com.abubusoft.kripton.processor.kripton58.Bean02Table
 */
public class BindDaoBean02 extends AbstractDao implements DaoBean02 {
  public BindDaoBean02(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id FROM bean02 WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean02 selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id FROM bean02 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id FROM bean02 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean02 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");

      resultBean=new Bean02();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }

    }
    cursor.close();

    return resultBean;
  }
}
