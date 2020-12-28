package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.OffsetDateTimeUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class OffsetDateTimeBindTransform extends WrappedPrefsTransform {

  public OffsetDateTimeBindTransform() {
    super(OffsetDateTimeUtils.class);
  }

}
