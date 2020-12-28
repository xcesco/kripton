package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.YearUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.Year;

public class YearSQLTransform extends UtilSQLTransform<YearUtils> {

  public YearSQLTransform() {
    super(YearUtils.class);
  }

}
