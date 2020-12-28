package com.abubusoft.kripton.common.time;

import java.time.Instant;

public abstract class InstantUtils {
  private InstantUtils() {

  }

  public static Instant read(String value) {
    if (value == null) return null;

    return Instant.parse(value);
  }

  public static String write(Instant value) {
    if (value == null) return null;

    return value.toString();
  }
}
