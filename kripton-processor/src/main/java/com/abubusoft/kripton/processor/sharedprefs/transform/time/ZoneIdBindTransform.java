package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.ZoneIdUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;


public class ZoneIdBindTransform extends WrappedPrefsTransform {

  public ZoneIdBindTransform() {
    super(ZoneIdUtils.class);
  }

}
