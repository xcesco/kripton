package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.LocalDateUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class LocalDateBindTransform extends WrappedPrefsTransform {

  public LocalDateBindTransform() {
    super(LocalDateUtils.class);
  }

}
