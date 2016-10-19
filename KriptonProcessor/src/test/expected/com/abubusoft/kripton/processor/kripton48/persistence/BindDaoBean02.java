package com.abubusoft.kripton.processor.kripton48.persistence;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.kripton48.entities.Bean02;

/**
 * <p>
 * DAO implementation for entity <code>Bean02</code>, based on interface <code>DaoBean02</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02
 *  @see com.abubusoft.kripton.processor.kripton48.persistence.DaoBean02
 *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02Table
 */
public class BindDaoBean02 extends DaoBase implements DaoBean02 {
  public BindDaoBean02(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, text FROM bean02 WHERE id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean02 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, text FROM bean02 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, text FROM bean02 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean02 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean02();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean02 WHERE id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE bean02 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean02", "id=?", whereConditions);
    return result;
  }
}
