package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.time.InstantUtils;
import com.abubusoft.kripton.processor.sharedprefs.transform.WrappedPrefsTransform;

public class InstantBindTransform extends WrappedPrefsTransform {

  public InstantBindTransform() {
    super(InstantUtils.class);
  }

}
