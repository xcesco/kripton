package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

public class ZoneOffsetSQLTransform extends UtilSQLTransform<ZoneOffsetUtils> {

  public ZoneOffsetSQLTransform() {
    super(ZoneOffsetUtils.class);
  }

}
