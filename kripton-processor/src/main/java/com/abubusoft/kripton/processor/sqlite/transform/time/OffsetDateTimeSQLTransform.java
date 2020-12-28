package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.OffsetDateTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.OffsetDateTime;

public class OffsetDateTimeSQLTransform extends UtilSQLTransform<OffsetDateTimeUtils> {

  public OffsetDateTimeSQLTransform() {
    super(OffsetDateTimeUtils.class);
  }

}
