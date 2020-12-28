package com.abubusoft.kripton.common.time;

import java.time.LocalDate;

public abstract class LocalDateUtils {
  private LocalDateUtils() {

  }

  public static LocalDate read(String value) {
    if (value == null) return null;

    return LocalDate.parse(value);
  }

  public static String write(LocalDate value) {
    if (value == null) return null;

    return value.toString();
  }
}
