package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.LocalTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class LocalTimeBindTransform extends WrappedBindTransform {

  public LocalTimeBindTransform() {
    super(LocalTimeUtils.class);
  }

}
