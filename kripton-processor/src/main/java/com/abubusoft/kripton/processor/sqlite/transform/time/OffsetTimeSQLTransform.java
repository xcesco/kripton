package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.OffsetTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.OffsetTime;

public class OffsetTimeSQLTransform extends UtilSQLTransform<OffsetTimeUtils> {

  public OffsetTimeSQLTransform() {
    super(OffsetTimeUtils.class);
  }
}
