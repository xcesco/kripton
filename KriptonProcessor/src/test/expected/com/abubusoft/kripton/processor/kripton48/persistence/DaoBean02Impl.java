package com.abubusoft.kripton.processor.kripton48.persistence;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.kripton48.entities.Bean02;

/**
 * <p>
 * DAO implementation for entity <code>Bean02</code>, based on interface <code>DaoBean02</code>
 * </p>
 *
 *  @see Bean02
 *  @see DaoBean02
 *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02Table
 */
public class DaoBean02Impl extends AbstractDao implements DaoBean02 {
  public DaoBean02Impl(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, text FROM bean02 WHERE id=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * </ul>
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
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
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
