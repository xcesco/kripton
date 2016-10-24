/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.kripton41;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanSelectOK</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01
 *  @see com.abubusoft.kripton.processor.kripton41.DaoBeanSelectOK
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01Table
 */
public class BindDaoBeanSelectOK extends DaoBase implements DaoBeanSelectOK {
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
