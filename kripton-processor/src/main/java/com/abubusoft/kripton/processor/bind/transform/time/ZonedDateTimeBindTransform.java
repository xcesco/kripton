package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class ZonedDateTimeBindTransform extends WrappedBindTransform {

  public ZonedDateTimeBindTransform() {
    super(ZonedDateTimeUtils.class);
  }

}
