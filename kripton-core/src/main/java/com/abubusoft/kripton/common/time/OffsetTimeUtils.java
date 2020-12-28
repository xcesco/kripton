package com.abubusoft.kripton.common.time;

import java.time.OffsetTime;

public abstract class OffsetTimeUtils {
  private OffsetTimeUtils() {

  }

  public static OffsetTime read(String value) {
    if (value == null) return null;

    return OffsetTime.parse(value);
  }

  public static String write(OffsetTime value) {
    if (value == null) return null;

    return value.toString();
  }
}
