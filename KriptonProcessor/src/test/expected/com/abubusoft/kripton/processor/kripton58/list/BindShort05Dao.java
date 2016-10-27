package com.abubusoft.kripton.processor.kripton58.list;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Short05Bean</code>, based on interface <code>Short05Dao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Dao
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable
 */
public class BindShort05Dao extends AbstractDao implements Short05Dao {
  public BindShort05Dao(BindShort05DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM short05_bean WHERE value=${value}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param listener
   */
  @Override
  public void selectOne(List<Short> value, ReadBeanListener<Short05Bean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM short05_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM short05_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Short05Bean resultBean=new Short05Bean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping (only for nullable property)
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Short>(), Short.class, cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Short>(), Short.class, cursor.getBlob(index2)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }
}
