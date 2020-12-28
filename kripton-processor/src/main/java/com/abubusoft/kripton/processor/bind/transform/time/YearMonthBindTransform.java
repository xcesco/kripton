package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.YearMonthUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;


public class YearMonthBindTransform extends WrappedBindTransform {

  public YearMonthBindTransform() {
    super(YearMonthUtils.class);
  }

}
