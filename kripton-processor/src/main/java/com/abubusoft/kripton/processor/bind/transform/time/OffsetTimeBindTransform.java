package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.OffsetTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class OffsetTimeBindTransform extends WrappedBindTransform {

  public OffsetTimeBindTransform() {
    super(OffsetTimeUtils.class);
  }
}
