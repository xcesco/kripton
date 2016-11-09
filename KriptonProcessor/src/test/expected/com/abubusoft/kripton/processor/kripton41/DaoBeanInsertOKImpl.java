package com.abubusoft.kripton.processor.kripton41;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanInsertOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanInsertOK
 *  @see Bean01Table
 */
public class DaoBeanInsertOKImpl extends AbstractDao implements DaoBeanInsertOK {
  public DaoBeanInsertOKImpl(BindDummy04DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (id, value) VALUES (${id}, ${value})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
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
