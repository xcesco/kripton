package com.abubusoft.kripton.common.time;

import java.time.LocalTime;

public abstract class LocalTimeUtils {
  private LocalTimeUtils() {

  }

  public static LocalTime read(String value) {
    if (value == null) return null;

    return LocalTime.parse(value);
  }

  public static String write(LocalTime value) {
    if (value == null) return null;

    return value.toString();
  }
}
