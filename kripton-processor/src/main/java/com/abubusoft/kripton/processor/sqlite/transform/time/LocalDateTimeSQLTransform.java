package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.LocalDateTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.LocalDateTime;


public class LocalDateTimeSQLTransform extends UtilSQLTransform<LocalDateTimeUtils> {

  public LocalDateTimeSQLTransform() {
    super(LocalDateTimeUtils.class);
  }

}
