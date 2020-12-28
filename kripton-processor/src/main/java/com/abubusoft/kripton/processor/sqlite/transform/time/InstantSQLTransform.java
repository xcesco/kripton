package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.InstantUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.Instant;

public class InstantSQLTransform extends UtilSQLTransform<InstantUtils> {

  public InstantSQLTransform() {
    super(InstantUtils.class);
  }

}
