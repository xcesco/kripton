package com.abubusoft.kripton.common.time;

import java.time.OffsetDateTime;

public abstract class OffsetDateTimeUtils {
  private OffsetDateTimeUtils() {

  }

  public static OffsetDateTime read(String value) {
    if (value == null) return null;

    return OffsetDateTime.parse(value);
  }

  public static String write(OffsetDateTime value) {
    if (value == null) return null;

    return value.toString();
  }
}
