package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.PeriodUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.Period;

public class PeriodSQLTransform extends UtilSQLTransform<PeriodUtils> {

  public PeriodSQLTransform() {
    super(PeriodUtils.class);
  }

}
