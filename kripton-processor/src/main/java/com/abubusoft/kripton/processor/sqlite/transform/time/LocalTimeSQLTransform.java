package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.LocalTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.LocalTime;

public class LocalTimeSQLTransform extends UtilSQLTransform<LocalTimeUtils> {

  public LocalTimeSQLTransform() {
    super(LocalTimeUtils.class);
  }

}
