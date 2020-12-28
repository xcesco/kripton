package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.OffsetDateTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class OffsetDateTimeBindTransform extends WrappedBindTransform {

  public OffsetDateTimeBindTransform() {
    super(OffsetDateTimeUtils.class);
  }

}
