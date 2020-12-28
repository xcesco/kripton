package bind.feature.time;

import bind.AbstractBaseTest;
import org.junit.Test;

import java.time.*;

public class TestRuntimeTime1 extends AbstractBaseTest {

  /**
   * Test run.
   *
   * @throws Exception the exception
   */
  @Test
  public void testBeanRun() throws Exception {
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

    check(bean);
  }

  @Test
  public void testBeanWithAccessorRun() throws Exception {
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

    check(bean);
  }

  @Test
  public void testBeanImmutableRun() throws Exception {
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

    check(bean);
  }

  @Test
  public void test() {
    System.out.println(Instant.now().toString());
    System.out.println(Instant.parse(Instant.now().toString()));

    System.out.println(LocalDate.now().toString());
    System.out.println(LocalDate.parse(LocalDate.now().toString()));

    System.out.println(LocalDateTime.now().toString());
    System.out.println(LocalDateTime.parse(LocalDateTime.now().toString()));

    System.out.println(LocalTime.now().toString());
    System.out.println(LocalTime.parse(LocalTime.now().toString()));

    System.out.println(MonthDay.now().toString());
    System.out.println(MonthDay.parse(MonthDay.now().toString()));

    System.out.println(OffsetDateTime.now().toString());
    System.out.println(OffsetDateTime.parse(OffsetDateTime.now().toString()));

    System.out.println(OffsetTime.now().toString());
    System.out.println(OffsetTime.parse(OffsetTime.now().toString()));

    System.out.println(Period.ofDays(1).toString());
    System.out.println(Period.parse(Period.ofDays(1).toString()));

    System.out.println(LocalTime.now().toString());
    System.out.println(LocalTime.parse(LocalTime.now().toString()));

    System.out.println(Year.now().toString());
    System.out.println(Year.parse(Year.now().toString()));

    System.out.println(YearMonth.now().toString());
    System.out.println(YearMonth.parse(YearMonth.now().toString()));

    System.out.println(ZonedDateTime.now().toString());
    System.out.println(ZonedDateTime.parse(ZonedDateTime.now().toString()));

    System.out.println(ZoneId.systemDefault().toString());
    System.out.println(ZoneId.of(ZoneId.systemDefault().toString()));

    ZoneId zone = ZoneId.of("Europe/Berlin");
    System.out.println(zone.getRules().getOffset(LocalDateTime.now()).getId());
    System.out.println(ZoneOffset.of(zone.getRules().getOffset(LocalDateTime.now()).getId()));
  }
}