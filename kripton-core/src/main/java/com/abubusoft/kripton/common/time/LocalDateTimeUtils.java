package com.abubusoft.kripton.common.time;

import java.time.LocalDateTime;

public abstract class LocalDateTimeUtils {
  private LocalDateTimeUtils() {

  }

  public static LocalDateTime read(String value) {
    if (value == null) return null;

    return LocalDateTime.parse(value);
  }

  public static String write(LocalDateTime value) {
    if (value == null) return null;

    return value.toString();
  }
}
