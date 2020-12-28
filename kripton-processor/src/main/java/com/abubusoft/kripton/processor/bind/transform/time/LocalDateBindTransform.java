package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.LocalDateUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class LocalDateBindTransform extends WrappedBindTransform {

  public LocalDateBindTransform() {
    super(LocalDateUtils.class);
  }

}
