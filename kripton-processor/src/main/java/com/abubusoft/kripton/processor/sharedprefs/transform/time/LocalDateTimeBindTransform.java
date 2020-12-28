package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.LocalDateTimeUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;


public class LocalDateTimeBindTransform extends WrappedPrefsTransform {

  public LocalDateTimeBindTransform() {
    super(LocalDateTimeUtils.class);
  }

}
