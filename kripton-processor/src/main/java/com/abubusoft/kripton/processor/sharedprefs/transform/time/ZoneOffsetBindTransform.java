package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class ZoneOffsetBindTransform extends WrappedPrefsTransform {

  public ZoneOffsetBindTransform() {
    super(ZoneOffsetUtils.class);
  }

}
