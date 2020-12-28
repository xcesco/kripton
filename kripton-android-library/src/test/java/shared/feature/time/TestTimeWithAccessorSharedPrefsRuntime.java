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
import shared.feature.time.case1.Bean;
import shared.feature.time.case1.BeanWithAccessors;
import shared.feature.time.case1.BindBeanSharedPreferences;
import shared.feature.time.case1.BindBeanWithAccessorsSharedPreferences;

import java.time.*;

public class TestTimeWithAccessorSharedPrefsRuntime extends BaseAndroidTest {

  /**
   * Creates the bean.
   *
   * @return the app preferences
   */
  public BeanWithAccessors createBean() {
    BeanWithAccessors bean = new BeanWithAccessors();

    bean.setIstant(Instant.now());
    bean.setLocalDate(LocalDate.now());
    bean.setLocalDateTime(LocalDateTime.now());
    bean.setLocalTime(LocalTime.now());
    bean.setMonthDay(MonthDay.now());
    bean.setOffsetDateTime(OffsetDateTime.now());
    bean.setOffsetTime(OffsetTime.now());
    bean.setPeriod(Period.ofDays(1));
    bean.setLocalTime(LocalTime.now());
    bean.setYear(Year.now());
    bean.setYearMonth(YearMonth.now());
    bean.setZonedDateTime(ZonedDateTime.now());
    bean.setZoneId(ZoneId.systemDefault());
    bean.setZoneOffset(ZoneId.of("Europe/Berlin").getRules().getOffset(LocalDateTime.now()));

    return bean;
  }


  /**
   * Test app run.
   */
  @Test
  public void testAppRun() {
    BeanWithAccessors bean = createBean();
    BindBeanWithAccessorsSharedPreferences prefs = BindBeanWithAccessorsSharedPreferences.getInstance();
    prefs.write(bean);

    BeanWithAccessors bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }

  /**
   * Test app run fail.
   */
  @Test(expected = AssertionFailedError.class)
  public void testAppRunFail() {
    BeanWithAccessors bean = createBean();
    BindBeanWithAccessorsSharedPreferences prefs = BindBeanWithAccessorsSharedPreferences.getInstance();
    prefs.write(bean);

    bean.setDuration(Duration.ofMillis(101));

    BeanWithAccessors bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }


}
