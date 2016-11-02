package com.abubusoft.kripton.processor.test03;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.test03.Bean01
 *  @see com.abubusoft.kripton.processor.test03.DaoBean01
 *  @see com.abubusoft.kripton.processor.test03.Bean01Table
 */
public class BindDaoBean01 extends AbstractDao implements DaoBean01 {
  public BindDaoBean01(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[lista, id, message_date, message_text, bean_list, value]</pre>
   *
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean01> listAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean01> resultList=new LinkedList<Bean01>();
    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("lista");
      int index1=cursor.getColumnIndex("id");
      int index2=cursor.getColumnIndex("message_date");
      int index3=cursor.getColumnIndex("message_text");
      int index4=cursor.getColumnIndex("bean_list");
      int index5=cursor.getColumnIndex("value");

      do
       {
        resultBean=new Bean01();

        if (!cursor.isNull(index0)) { resultBean.setLista(ProcessorHelper.asList(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index0))); }
        if (!cursor.isNull(index1)) { resultBean.setId(cursor.getLong(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setMessageDate(cursor.getLong(index2)); }
        resultBean.setMessageText(cursor.getString(index3));
        if (!cursor.isNull(index4)) { resultBean.setBeanList(ProcessorHelper.asList(new ArrayList<Bean02>(), Bean02.class, cursor.getBlob(index4))); }
        if (!cursor.isNull(index5)) { resultBean.setValue(cursor.getLong(index5)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}
