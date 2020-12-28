/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package shared.feature.time;


import base.BaseAndroidTest;
import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import shared.feature.time.case1.BeanImmutable;
import shared.feature.time.case1.BindBeanImmutableSharedPreferences;

import java.time.*;

public class TestTimeImmutableSharedPrefsRuntime extends BaseAndroidTest {

  /**
   * Creates the bean.
   *
   * @return the app preferences
   */
  public BeanImmutable createBean() {
    BeanImmutable bean = new BeanImmutable(
            Duration.ofHours(1),
            Instant.now(),
            LocalDate.now(),
            LocalDateTime.now(),
            LocalTime.now(),
            MonthDay.now(),
            OffsetDateTime.now(),
            OffsetTime.now(),
            Period.ofDays(1),
            Year.now(),
            YearMonth.now(),
            ZonedDateTime.now(),
            ZoneId.systemDefault(),
            ZoneId.of("Europe/Berlin").getRules().getOffset(LocalDateTime.now())
    );

    return bean;
  }

  public BeanImmutable createBean2() {
    BeanImmutable bean = new BeanImmutable(
            Duration.ofHours(1),
            Instant.now(),
            LocalDate.now(),
            LocalDateTime.now(),
            LocalTime.of(23, 34),
            MonthDay.now(),
            OffsetDateTime.now(),
            OffsetTime.now(),
            Period.ofDays(3),
            Year.now(),
            YearMonth.now(),
            ZonedDateTime.now(),
            ZoneId.systemDefault(),
            ZoneId.of("Europe/Berlin").getRules().getOffset(LocalDateTime.now())
    );

    return bean;
  }


  /**
   * Test app run.
   */
  @Test
  public void testAppRun() {
    BeanImmutable bean = createBean();
    BindBeanImmutableSharedPreferences prefs = BindBeanImmutableSharedPreferences.getInstance();
    prefs.write(bean);

    BeanImmutable bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }

  /**
   * Test app run fail.
   */
  @Test(expected = AssertionFailedError.class)
  public void testAppRunFail() {
    BeanImmutable bean = createBean();
    BindBeanImmutableSharedPreferences prefs = BindBeanImmutableSharedPreferences.getInstance();
    prefs.write(bean);

    bean = createBean2();

    BeanImmutable bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }


}
