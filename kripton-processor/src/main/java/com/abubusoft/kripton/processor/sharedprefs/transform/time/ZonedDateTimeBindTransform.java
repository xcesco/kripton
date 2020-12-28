package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class ZonedDateTimeBindTransform extends WrappedPrefsTransform {

  public ZonedDateTimeBindTransform() {
    super(ZonedDateTimeUtils.class);
  }

}
