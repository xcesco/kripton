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
import shared.feature.time.case1.BindBeanSharedPreferences;

import java.time.*;

public class TestTimeSimpleSharedPrefsRuntime extends BaseAndroidTest {

  /**
   * Creates the bean.
   *
   * @return the app preferences
   */
  public Bean createBean() {
    Bean bean = new Bean();

    bean.istant = Instant.now();
    bean.localDate = LocalDate.now();
    bean.localDateTime = LocalDateTime.now();
    bean.localTime = LocalTime.now();
    bean.monthDay = MonthDay.now();
    bean.offsetDateTime = OffsetDateTime.now();
    bean.offsetTime = OffsetTime.now();
    bean.period = Period.ofDays(1);
    bean.localTime = LocalTime.now();
    bean.year = Year.now();
    bean.yearMonth = YearMonth.now();
    bean.zonedDateTime = ZonedDateTime.now();
    bean.zoneId = ZoneId.systemDefault();
    bean.zoneOffset = ZoneId.of("Europe/Berlin").getRules().getOffset(LocalDateTime.now());

    return bean;
  }


  /**
   * Test app run.
   */
  @Test
  public void testAppRun() {
    Bean bean = createBean();
    BindBeanSharedPreferences prefs = BindBeanSharedPreferences.getInstance();
    prefs.write(bean);

    Bean bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }

  /**
   * Test app run fail.
   */
  @Test(expected = AssertionFailedError.class)
  public void testAppRunFail() {
    Bean bean = createBean();
    BindBeanSharedPreferences prefs = BindBeanSharedPreferences.getInstance();
    prefs.write(bean);

    bean.duration = Duration.ofMillis(101);

    Bean bean2 = prefs.read();
    ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
  }


}
