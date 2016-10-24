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

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanUpdateOK</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01
 *  @see com.abubusoft.kripton.processor.kripton41.DaoBeanUpdateOK
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01Table
 */
public class BindDaoBeanUpdateOK extends DaoBase implements DaoBeanUpdateOK {
  public BindDaoBeanUpdateOK(BindDummy06DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET id=${id}, value=${value} WHERE id=${test}</pre>
   *
   * @param id
   * 	used as updated field
   * @param value
   * 	used as updated field
   * @param test
   * 	used in where condition
   *
   * @return true if record is updated */
  @Override
  public boolean updateDistance(long id, Double value, long test) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);
    if (value!=null) {
      contentValues.put("value", value);
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={String.valueOf(test)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET id='"+StringUtil.checkSize(contentValues.get("id"))+"', value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "id=?", whereConditions);
    return result!=0;
  }
}
