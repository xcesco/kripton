package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.YearMonthUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;


public class YearMonthBindTransform extends WrappedPrefsTransform {

  public YearMonthBindTransform() {
    super(YearMonthUtils.class);
  }

}
