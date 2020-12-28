package com.abubusoft.kripton.common.time;

import java.time.Period;

public abstract class PeriodUtils {
  private PeriodUtils() {

  }

  public static Period read(String value) {
    if (value == null) return null;

    return Period.parse(value);
  }

  public static String write(Period value) {
    if (value == null) return null;

    return value.toString();
  }
}
