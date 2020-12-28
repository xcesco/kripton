package com.abubusoft.kripton.common.time;

import java.time.Duration;

public abstract class DurationUtils {
  private DurationUtils() {
  }

  public static Duration read(String value) {
    if (value == null) return null;

    return Duration.parse(value);
  }

  public static String write(Duration value) {
    if (value == null) return null;

    return value.toString();
  }
}
