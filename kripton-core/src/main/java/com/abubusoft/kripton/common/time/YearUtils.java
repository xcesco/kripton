package com.abubusoft.kripton.common.time;

import java.time.Year;

public abstract class YearUtils {
  private YearUtils() {

  }

  public static Year read(String value) {
    if (value == null) return null;

    return Year.parse(value);
  }

  public static String write(Year value) {
    if (value == null) return null;

    return value.toString();
  }
}
