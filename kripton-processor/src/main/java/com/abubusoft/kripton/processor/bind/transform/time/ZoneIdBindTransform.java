package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.ZoneIdUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;


public class ZoneIdBindTransform extends WrappedBindTransform {

  public ZoneIdBindTransform() {
    super(ZoneIdUtils.class);
  }

}
