package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.time.ZoneIdUtils;
import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.processor.bind.transform.WrappedBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.UtilSQLTransform;

import java.time.ZoneId;


public class ZoneIdSQLTransform extends UtilSQLTransform<ZoneIdUtils> {

  public ZoneIdSQLTransform() {
    super(ZoneIdUtils.class);
  }

}
