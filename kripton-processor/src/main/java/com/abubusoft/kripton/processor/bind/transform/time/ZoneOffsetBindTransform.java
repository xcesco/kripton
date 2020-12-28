package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class ZoneOffsetBindTransform extends WrappedBindTransform {

  public ZoneOffsetBindTransform() {
    super(ZoneOffsetUtils.class);
  }

}
