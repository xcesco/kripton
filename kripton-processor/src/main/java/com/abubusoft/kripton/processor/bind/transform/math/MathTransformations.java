package com.abubusoft.kripton.processor.bind.transform.math;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class MathTransformations {
  public static final List<Pair<Class<?>, Class<? extends BindTransform>>> transformations = Lists.newArrayList(
          of(BigDecimal.class, BigDecimalBindTransform.class),
          of(BigInteger.class, BigIntegerBindTransform.class)
  );
}
