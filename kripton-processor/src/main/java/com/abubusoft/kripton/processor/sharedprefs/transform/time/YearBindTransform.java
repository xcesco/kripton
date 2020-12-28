package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.YearUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class YearBindTransform extends WrappedPrefsTransform {

  public YearBindTransform() {
    super(YearUtils.class);
  }

}
