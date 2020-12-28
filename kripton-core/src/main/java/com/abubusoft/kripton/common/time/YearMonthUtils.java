package com.abubusoft.kripton.common.time;

import java.time.YearMonth;

public abstract class YearMonthUtils {
  private YearMonthUtils() {

  }

  public static YearMonth read(String value) {
    if (value == null) return null;

    return YearMonth.parse(value);
  }

  public static String write(YearMonth value) {
    if (value == null) return null;

    return value.toString();
  }
}
