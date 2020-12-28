package com.abubusoft.kripton.processor.bind.transform.lang;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.google.common.collect.Lists;

import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class LangTransformations {
  public static final List<Pair<Class<?>, Class<? extends BindTransform>>> transformations = Lists.newArrayList(
          of(Integer.class, IntegerBindTransform.class),
          of(Boolean.class, BooleanBindTransform.class),
          of(Long.class, LongBindTransform.class),
          of(Double.class, DoubleBindTransform.class),
          of(Float.class, FloatBindTransform.class),
          of(Short.class, ShortBindTransform.class),
          of(Byte.class, ByteBindTransform.class),
          of(Character.class, CharacterBindTransform.class),
          of(String.class, StringBindTransform.class)
  );
}
