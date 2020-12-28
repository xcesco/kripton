package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.LocalTimeUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class LocalTimeBindTransform extends WrappedPrefsTransform {

  public LocalTimeBindTransform() {
    super(LocalTimeUtils.class);
  }

}
