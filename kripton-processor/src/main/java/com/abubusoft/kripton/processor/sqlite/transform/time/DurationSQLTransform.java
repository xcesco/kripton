package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

public class DurationSQLTransform extends UtilSQLTransform<DurationUtils> {

  public DurationSQLTransform() {
    super(DurationUtils.class);
  }

}
