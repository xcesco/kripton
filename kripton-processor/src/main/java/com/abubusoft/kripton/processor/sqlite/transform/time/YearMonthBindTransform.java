package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.YearMonthUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.YearMonth;


public class YearMonthBindTransform extends UtilSQLTransform<YearMonthUtils> {

  public YearMonthBindTransform() {
    super(YearMonthUtils.class);
  }

}
