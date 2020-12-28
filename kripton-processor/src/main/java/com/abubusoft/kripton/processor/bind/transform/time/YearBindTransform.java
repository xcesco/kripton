package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.YearUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class YearBindTransform extends WrappedBindTransform {

  public YearBindTransform() {
    super(YearUtils.class);
  }

}
