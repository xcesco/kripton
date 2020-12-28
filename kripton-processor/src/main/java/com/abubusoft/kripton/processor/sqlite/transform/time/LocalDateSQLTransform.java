package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.LocalDateUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.LocalDate;

public class LocalDateSQLTransform extends UtilSQLTransform<LocalDateUtils> {

  public LocalDateSQLTransform() {
    super(LocalDateUtils.class);
  }

}
