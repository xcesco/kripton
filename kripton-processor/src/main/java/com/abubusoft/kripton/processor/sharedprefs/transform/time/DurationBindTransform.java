package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;


public class DurationBindTransform extends WrappedPrefsTransform {

  public DurationBindTransform() {
    super(DurationUtils.class);
  }

}
