package com.abubusoft.kripton.processor.bind.transform.time;

import com.abubusoft.kripton.common.time.InstantUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;

public class InstantBindTransform extends WrappedBindTransform {

  public InstantBindTransform() {
    super(InstantUtils.class);
  }

}
