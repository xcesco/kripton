package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class DurationBindTransform extends WrappedBindTransform {

  public DurationBindTransform() {
    super(DurationUtils.class);
  }

}
