package com.abubusoft.kripton.processor.sharedprefs.transform.math;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class MathTransformations {
  public static final List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations = Lists.newArrayList(
          of(BigDecimal.class, BigDecimalPrefsTransform.class),
          of(BigInteger.class, BigIntegerPrefsTransform.class)
  );
}
