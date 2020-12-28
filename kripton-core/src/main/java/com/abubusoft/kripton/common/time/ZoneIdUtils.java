package com.abubusoft.kripton.common.time;

import java.time.ZoneId;

public abstract class ZoneIdUtils {
  private ZoneIdUtils() {

  }

  public static ZoneId read(String value) {
    if (value == null) return null;

    return ZoneId.of(value);
  }

  public static String write(ZoneId value) {
    if (value == null) return null;

    return value.toString();
  }
}
