package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.ZonedDateTime;

public class ZonedDateTimeSQLTransform extends UtilSQLTransform<ZonedDateTimeUtils> {

  public ZonedDateTimeSQLTransform() {
    super(ZonedDateTimeUtils.class);
  }

}
