package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.OffsetTimeUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class OffsetTimeBindTransform extends WrappedPrefsTransform {

  public OffsetTimeBindTransform() {
    super(OffsetTimeUtils.class);
  }
}
