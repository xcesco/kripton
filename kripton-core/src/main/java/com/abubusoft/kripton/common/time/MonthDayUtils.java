package com.abubusoft.kripton.common.time;

import java.time.MonthDay;

public abstract class MonthDayUtils {
  private MonthDayUtils() {

  }

  public static MonthDay read(String value) {
    if (value == null) return null;

    return MonthDay.parse(value);
  }

  public static String write(MonthDay value) {
    if (value == null) return null;

    return value.toString();
  }
}
