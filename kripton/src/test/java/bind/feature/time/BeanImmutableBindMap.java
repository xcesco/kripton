package bind.feature.time;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.InstantUtils;
import com.abubusoft.kripton.common.time.LocalDateTimeUtils;
import com.abubusoft.kripton.common.time.LocalDateUtils;
import com.abubusoft.kripton.common.time.LocalTimeUtils;
import com.abubusoft.kripton.common.time.MonthDayUtils;
import com.abubusoft.kripton.common.time.OffsetDateTimeUtils;
import com.abubusoft.kripton.common.time.OffsetTimeUtils;
import com.abubusoft.kripton.common.time.PeriodUtils;
import com.abubusoft.kripton.common.time.YearMonthUtils;
import com.abubusoft.kripton.common.time.YearUtils;
import com.abubusoft.kripton.common.time.ZoneIdUtils;
import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * This class is binder map for BeanImmutable
 *
 * @see BeanImmutable
 */
@BindMap(BeanImmutable.class)
public class BeanImmutableBindMap extends AbstractMapper<BeanImmutable> {
  @Override
  public int serializeOnJackson(BeanImmutable object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field duration (mapped with "duration")
    if (object.getDuration()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("duration", DurationUtils.write(object.getDuration()));
    }

    // field istant (mapped with "istant")
    if (object.getIstant()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("istant", InstantUtils.write(object.getIstant()));
    }

    // field localDate (mapped with "localDate")
    if (object.getLocalDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDate", LocalDateUtils.write(object.getLocalDate()));
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.getLocalDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDateTime", LocalDateTimeUtils.write(object.getLocalDateTime()));
    }

    // field localTime (mapped with "localTime")
    if (object.getLocalTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localTime", LocalTimeUtils.write(object.getLocalTime()));
    }

    // field monthDay (mapped with "monthDay")
    if (object.getMonthDay()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("monthDay", MonthDayUtils.write(object.getMonthDay()));
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.getOffsetDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetDateTime", OffsetDateTimeUtils.write(object.getOffsetDateTime()));
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.getOffsetTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetTime", OffsetTimeUtils.write(object.getOffsetTime()));
    }

    // field period (mapped with "period")
    if (object.getPeriod()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("period", PeriodUtils.write(object.getPeriod()));
    }

    // field year (mapped with "year")
    if (object.getYear()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", YearUtils.write(object.getYear()));
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.getYearMonth()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yearMonth", YearMonthUtils.write(object.getYearMonth()));
    }

    // field zoneId (mapped with "zoneId")
    if (object.getZoneId()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneId", ZoneIdUtils.write(object.getZoneId()));
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.getZoneOffset()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneOffset", ZoneOffsetUtils.write(object.getZoneOffset()));
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.getZonedDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zonedDateTime", ZonedDateTimeUtils.write(object.getZonedDateTime()));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(BeanImmutable object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field duration (mapped with "duration")
    if (object.getDuration()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("duration", DurationUtils.write(object.getDuration()));
    }

    // field istant (mapped with "istant")
    if (object.getIstant()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("istant", InstantUtils.write(object.getIstant()));
    }

    // field localDate (mapped with "localDate")
    if (object.getLocalDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDate", LocalDateUtils.write(object.getLocalDate()));
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.getLocalDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDateTime", LocalDateTimeUtils.write(object.getLocalDateTime()));
    }

    // field localTime (mapped with "localTime")
    if (object.getLocalTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localTime", LocalTimeUtils.write(object.getLocalTime()));
    }

    // field monthDay (mapped with "monthDay")
    if (object.getMonthDay()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("monthDay", MonthDayUtils.write(object.getMonthDay()));
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.getOffsetDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetDateTime", OffsetDateTimeUtils.write(object.getOffsetDateTime()));
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.getOffsetTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetTime", OffsetTimeUtils.write(object.getOffsetTime()));
    }

    // field period (mapped with "period")
    if (object.getPeriod()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("period", PeriodUtils.write(object.getPeriod()));
    }

    // field year (mapped with "year")
    if (object.getYear()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", YearUtils.write(object.getYear()));
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.getYearMonth()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yearMonth", YearMonthUtils.write(object.getYearMonth()));
    }

    // field zoneId (mapped with "zoneId")
    if (object.getZoneId()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneId", ZoneIdUtils.write(object.getZoneId()));
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.getZoneOffset()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneOffset", ZoneOffsetUtils.write(object.getZoneOffset()));
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.getZonedDateTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zonedDateTime", ZonedDateTimeUtils.write(object.getZonedDateTime()));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(BeanImmutable object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("beanImmutable");
    }

    // Persisted fields:

    // field duration (mapped with "duration")
    if (object.getDuration()!=null)  {
      xmlSerializer.writeStartElement("duration");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DurationUtils.write(object.getDuration())));
      xmlSerializer.writeEndElement();
    }

    // field istant (mapped with "istant")
    if (object.getIstant()!=null)  {
      xmlSerializer.writeStartElement("istant");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(InstantUtils.write(object.getIstant())));
      xmlSerializer.writeEndElement();
    }

    // field localDate (mapped with "localDate")
    if (object.getLocalDate()!=null)  {
      xmlSerializer.writeStartElement("localDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalDateUtils.write(object.getLocalDate())));
      xmlSerializer.writeEndElement();
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.getLocalDateTime()!=null)  {
      xmlSerializer.writeStartElement("localDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalDateTimeUtils.write(object.getLocalDateTime())));
      xmlSerializer.writeEndElement();
    }

    // field localTime (mapped with "localTime")
    if (object.getLocalTime()!=null)  {
      xmlSerializer.writeStartElement("localTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalTimeUtils.write(object.getLocalTime())));
      xmlSerializer.writeEndElement();
    }

    // field monthDay (mapped with "monthDay")
    if (object.getMonthDay()!=null)  {
      xmlSerializer.writeStartElement("monthDay");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(MonthDayUtils.write(object.getMonthDay())));
      xmlSerializer.writeEndElement();
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.getOffsetDateTime()!=null)  {
      xmlSerializer.writeStartElement("offsetDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(OffsetDateTimeUtils.write(object.getOffsetDateTime())));
      xmlSerializer.writeEndElement();
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.getOffsetTime()!=null)  {
      xmlSerializer.writeStartElement("offsetTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(OffsetTimeUtils.write(object.getOffsetTime())));
      xmlSerializer.writeEndElement();
    }

    // field period (mapped with "period")
    if (object.getPeriod()!=null)  {
      xmlSerializer.writeStartElement("period");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(PeriodUtils.write(object.getPeriod())));
      xmlSerializer.writeEndElement();
    }

    // field year (mapped with "year")
    if (object.getYear()!=null)  {
      xmlSerializer.writeStartElement("year");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(YearUtils.write(object.getYear())));
      xmlSerializer.writeEndElement();
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.getYearMonth()!=null)  {
      xmlSerializer.writeStartElement("yearMonth");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(YearMonthUtils.write(object.getYearMonth())));
      xmlSerializer.writeEndElement();
    }

    // field zoneId (mapped with "zoneId")
    if (object.getZoneId()!=null)  {
      xmlSerializer.writeStartElement("zoneId");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZoneIdUtils.write(object.getZoneId())));
      xmlSerializer.writeEndElement();
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.getZoneOffset()!=null)  {
      xmlSerializer.writeStartElement("zoneOffset");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZoneOffsetUtils.write(object.getZoneOffset())));
      xmlSerializer.writeEndElement();
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.getZonedDateTime()!=null)  {
      xmlSerializer.writeStartElement("zonedDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZonedDateTimeUtils.write(object.getZonedDateTime())));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public BeanImmutable parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    Duration __duration=null;
    Instant __istant=null;
    LocalDate __localDate=null;
    LocalDateTime __localDateTime=null;
    LocalTime __localTime=null;
    MonthDay __monthDay=null;
    OffsetDateTime __offsetDateTime=null;
    OffsetTime __offsetTime=null;
    Period __period=null;
    Year __year=null;
    YearMonth __yearMonth=null;
    ZonedDateTime __zonedDateTime=null;
    ZoneId __zoneId=null;
    ZoneOffset __zoneOffset=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      BeanImmutable instance=new BeanImmutable(__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "duration":
            // field duration (mapped with "duration")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __duration=DurationUtils.read(jacksonParser.getText());
            }
          break;
          case "istant":
            // field istant (mapped with "istant")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __istant=InstantUtils.read(jacksonParser.getText());
            }
          break;
          case "localDate":
            // field localDate (mapped with "localDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localDate=LocalDateUtils.read(jacksonParser.getText());
            }
          break;
          case "localDateTime":
            // field localDateTime (mapped with "localDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localDateTime=LocalDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "localTime":
            // field localTime (mapped with "localTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localTime=LocalTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "monthDay":
            // field monthDay (mapped with "monthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __monthDay=MonthDayUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetDateTime":
            // field offsetDateTime (mapped with "offsetDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __offsetDateTime=OffsetDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetTime":
            // field offsetTime (mapped with "offsetTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __offsetTime=OffsetTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "period":
            // field period (mapped with "period")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __period=PeriodUtils.read(jacksonParser.getText());
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __year=YearUtils.read(jacksonParser.getText());
            }
          break;
          case "yearMonth":
            // field yearMonth (mapped with "yearMonth")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __yearMonth=YearMonthUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneId":
            // field zoneId (mapped with "zoneId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zoneId=ZoneIdUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneOffset":
            // field zoneOffset (mapped with "zoneOffset")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zoneOffset=ZoneOffsetUtils.read(jacksonParser.getText());
            }
          break;
          case "zonedDateTime":
            // field zonedDateTime (mapped with "zonedDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zonedDateTime=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    BeanImmutable instance=new BeanImmutable(__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public BeanImmutable parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    Duration __duration=null;
    Instant __istant=null;
    LocalDate __localDate=null;
    LocalDateTime __localDateTime=null;
    LocalTime __localTime=null;
    MonthDay __monthDay=null;
    OffsetDateTime __offsetDateTime=null;
    OffsetTime __offsetTime=null;
    Period __period=null;
    Year __year=null;
    YearMonth __yearMonth=null;
    ZonedDateTime __zonedDateTime=null;
    ZoneId __zoneId=null;
    ZoneOffset __zoneOffset=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      BeanImmutable instance=new BeanImmutable(__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "duration":
            // field duration (mapped with "duration")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __duration=DurationUtils.read(jacksonParser.getText());
            }
          break;
          case "istant":
            // field istant (mapped with "istant")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __istant=InstantUtils.read(jacksonParser.getText());
            }
          break;
          case "localDate":
            // field localDate (mapped with "localDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localDate=LocalDateUtils.read(jacksonParser.getText());
            }
          break;
          case "localDateTime":
            // field localDateTime (mapped with "localDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localDateTime=LocalDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "localTime":
            // field localTime (mapped with "localTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __localTime=LocalTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "monthDay":
            // field monthDay (mapped with "monthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __monthDay=MonthDayUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetDateTime":
            // field offsetDateTime (mapped with "offsetDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __offsetDateTime=OffsetDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetTime":
            // field offsetTime (mapped with "offsetTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __offsetTime=OffsetTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "period":
            // field period (mapped with "period")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __period=PeriodUtils.read(jacksonParser.getText());
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __year=YearUtils.read(jacksonParser.getText());
            }
          break;
          case "yearMonth":
            // field yearMonth (mapped with "yearMonth")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __yearMonth=YearMonthUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneId":
            // field zoneId (mapped with "zoneId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zoneId=ZoneIdUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneOffset":
            // field zoneOffset (mapped with "zoneOffset")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zoneOffset=ZoneOffsetUtils.read(jacksonParser.getText());
            }
          break;
          case "zonedDateTime":
            // field zonedDateTime (mapped with "zonedDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __zonedDateTime=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    BeanImmutable instance=new BeanImmutable(__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public BeanImmutable parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    // immutable object: initialize temporary variables for properties
    Duration __duration=null;
    Instant __istant=null;
    LocalDate __localDate=null;
    LocalDateTime __localDateTime=null;
    LocalTime __localTime=null;
    MonthDay __monthDay=null;
    OffsetDateTime __offsetDateTime=null;
    OffsetTime __offsetTime=null;
    Period __period=null;
    Year __year=null;
    YearMonth __yearMonth=null;
    ZonedDateTime __zonedDateTime=null;
    ZoneId __zoneId=null;
    ZoneOffset __zoneOffset=null;
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;
    // No attributes found

    //sub-elements
    while (xmlParser.hasNext() && elementName!=null) {
      if (read) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      read=true;
      switch(eventType) {
          case START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "duration":
                  // property duration (mapped on "duration")
                  __duration=DurationUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "istant":
                  // property istant (mapped on "istant")
                  __istant=InstantUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localDate":
                  // property localDate (mapped on "localDate")
                  __localDate=LocalDateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localDateTime":
                  // property localDateTime (mapped on "localDateTime")
                  __localDateTime=LocalDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localTime":
                  // property localTime (mapped on "localTime")
                  __localTime=LocalTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "monthDay":
                  // property monthDay (mapped on "monthDay")
                  __monthDay=MonthDayUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "offsetDateTime":
                  // property offsetDateTime (mapped on "offsetDateTime")
                  __offsetDateTime=OffsetDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "offsetTime":
                  // property offsetTime (mapped on "offsetTime")
                  __offsetTime=OffsetTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "period":
                  // property period (mapped on "period")
                  __period=PeriodUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "year":
                  // property year (mapped on "year")
                  __year=YearUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "yearMonth":
                  // property yearMonth (mapped on "yearMonth")
                  __yearMonth=YearMonthUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zoneId":
                  // property zoneId (mapped on "zoneId")
                  __zoneId=ZoneIdUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zoneOffset":
                  // property zoneOffset (mapped on "zoneOffset")
                  __zoneOffset=ZoneOffsetUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zonedDateTime":
                  // property zonedDateTime (mapped on "zonedDateTime")
                  __zonedDateTime=ZonedDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                default:
                  xmlParser.skipChildren();
                break;
              }
            break;
            case END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case CDSECT:
            case TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      // immutable object: inizialize object
      BeanImmutable instance=new BeanImmutable(__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
      return instance;
    }
  }
