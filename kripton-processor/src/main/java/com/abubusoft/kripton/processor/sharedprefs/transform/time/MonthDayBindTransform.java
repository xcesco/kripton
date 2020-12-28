package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.MonthDayUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class MonthDayBindTransform extends WrappedPrefsTransform {

  public MonthDayBindTransform() {
    super(MonthDayUtils.class);
  }

}
