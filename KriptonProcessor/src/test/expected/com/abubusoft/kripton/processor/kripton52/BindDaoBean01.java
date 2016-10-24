package com.abubusoft.kripton.processor.kripton52;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

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
   * <pre>SELECT id, a_byte, a_string, a_long FROM bean01 WHERE id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, a_byte, a_string, a_long]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, a_byte, a_string, a_long FROM bean01 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, a_byte, a_string, a_long FROM bean01 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("a_byte");
      int index2=cursor.getColumnIndex("a_string");
      int index3=cursor.getColumnIndex("a_long");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.aByte=cursor.getBlob(index1); }
      if (!cursor.isNull(index2)) { resultBean.aString=(String[])readFromByteArray(String.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.aLong=(Long[])readFromByteArray(Long.class, cursor.getBlob(index3)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, a_byte, a_string, a_long FROM bean01 WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, a_byte, a_string, a_long]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, a_byte, a_string, a_long FROM bean01 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, a_byte, a_string, a_long FROM bean01 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("a_byte");
      int index2=cursor.getColumnIndex("a_string");
      int index3=cursor.getColumnIndex("a_long");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.aByte=cursor.getBlob(index1); }
      if (!cursor.isNull(index2)) { resultBean.aString=(String[])readFromByteArray(String.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.aLong=(Long[])readFromByteArray(Long.class, cursor.getBlob(index3)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET id=${id} WHERE id=${uid}</pre>
   *
   * @param id
   * 	used as updated field
   * @param uid
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, long uid) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    String[] whereConditions={String.valueOf(uid)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET id='"+StringUtil.checkSize(contentValues.get("id"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "id=?", whereConditions);
    return result;
  }
}
