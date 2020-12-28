package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.MonthDayUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.MonthDay;

public class MonthDaySQLTransform extends UtilSQLTransform<MonthDayUtils> {

  public MonthDaySQLTransform() {
    super(MonthDayUtils.class);
  }

}
