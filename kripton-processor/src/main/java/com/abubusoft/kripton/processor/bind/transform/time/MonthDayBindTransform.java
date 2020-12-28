package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.MonthDayUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class MonthDayBindTransform extends WrappedBindTransform {

  public MonthDayBindTransform() {
    super(MonthDayUtils.class);
  }

}
