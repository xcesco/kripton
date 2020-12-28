package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.LocalDateTimeUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;


public class LocalDateTimeBindTransform extends WrappedBindTransform {

  public LocalDateTimeBindTransform() {
    super(LocalDateTimeUtils.class);
  }

}
