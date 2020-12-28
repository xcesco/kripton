package com.abubusoft.kripton.processor.sharedprefs.transform.lang;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.google.common.collect.Lists;

import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class LangTransformations {
  public static final List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations = Lists.newArrayList(
          of(Integer.class, IntegerPrefsTransform.class),
          of(Boolean.class, BooleanPrefsTransform.class),
          of(Long.class, LongPrefsTransform.class),
          of(Double.class, DoublePrefsTransform.class),
          of(Float.class, FloatPrefsTransform.class),
          of(Short.class, ShortPrefsTransform.class),
          of(Byte.class, BytePrefsTransform.class),
          of(Character.class, CharacterPrefsTransform.class),
          of(String.class, StringPrefsTransform.class)
  );
}
