package com.abubusoft.kripton.common.time;

import java.time.ZoneOffset;

public abstract class ZoneOffsetUtils {
  private ZoneOffsetUtils() {

  }

  public static ZoneOffset read(String value) {
    if (value == null) return null;

    return ZoneOffset.of(value);
  }

  public static String write(ZoneOffset value) {
    if (value == null) return null;

    return value.getId();
  }
}
