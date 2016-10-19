package com.abubusoft.kripton.processor.kripton41;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanInsertOK</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01
 *  @see com.abubusoft.kripton.processor.kripton41.DaoBeanInsertOK
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01Table
 */
public class BindDaoBeanInsertOK extends DaoBase implements DaoBeanInsertOK {
  public BindDaoBeanInsertOK(BindDummy04DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (id, value) VALUES (${id}, ${value})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param value
   * 	used as updated field and in where condition
   * @return true if record is inserted
   */
  @Override
  public boolean insertDistance(long id, Double value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", value);
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (id, value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    return result!=-1;
  }
}
