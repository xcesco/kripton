package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.PeriodUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class PeriodBindTransform extends WrappedBindTransform {

  public PeriodBindTransform() {
    super(PeriodUtils.class);
  }

}
