package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.PeriodUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class PeriodBindTransform extends WrappedPrefsTransform {

  public PeriodBindTransform() {
    super(PeriodUtils.class);
  }

}
