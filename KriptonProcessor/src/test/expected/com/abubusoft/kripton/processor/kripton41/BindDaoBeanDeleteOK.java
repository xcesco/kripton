package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanDeleteOK</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01
 *  @see com.abubusoft.kripton.processor.kripton41.DaoBeanDeleteOK
 *  @see com.abubusoft.kripton.processor.kripton41.Bean01Table
 */
public class BindDaoBeanDeleteOK extends AbstractDao implements DaoBeanDeleteOK {
  public BindDaoBeanDeleteOK(BindDummy08DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean01 WHERE id=${value}</pre>
   *
   * @param value
   * 	used in where condition
   *
   * @return true if record is deleted */
  @Override
  public boolean deleteDistance(double value) {
    String[] whereConditions={String.valueOf(value)};

    Logger.info(StringUtil.formatSQL("DELETE bean01 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", "id=?", whereConditions);
    return result!=0;
  }
}
