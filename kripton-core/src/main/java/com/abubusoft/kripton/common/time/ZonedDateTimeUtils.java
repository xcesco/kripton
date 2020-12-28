package com.abubusoft.kripton.common.time;

import java.time.ZonedDateTime;

public abstract class ZonedDateTimeUtils {
  private ZonedDateTimeUtils() {

  }

  public static ZonedDateTime read(String value) {
    if (value == null) return null;

    return ZonedDateTime.parse(value);
  }

  public static String write(ZonedDateTime value) {
    if (value == null) return null;

    return value.toString();
  }
}
