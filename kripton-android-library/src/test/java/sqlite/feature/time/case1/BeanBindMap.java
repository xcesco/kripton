package sqlite.feature.time.case1;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
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

/**
 * This class is binder map for Bean
 *
 * @see Bean
 */
@BindMap(Bean.class)
public class BeanBindMap extends AbstractMapper<Bean> {
  @Override
  public int serializeOnJackson(Bean object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field duration (mapped with "duration")
    if (object.duration!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("duration", DurationUtils.write(object.duration));
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field istant (mapped with "istant")
    if (object.istant!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("istant", InstantUtils.write(object.istant));
    }

    // field localDate (mapped with "localDate")
    if (object.localDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDate", LocalDateUtils.write(object.localDate));
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.localDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDateTime", LocalDateTimeUtils.write(object.localDateTime));
    }

    // field localTime (mapped with "localTime")
    if (object.localTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localTime", LocalTimeUtils.write(object.localTime));
    }

    // field monthDay (mapped with "monthDay")
    if (object.monthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("monthDay", MonthDayUtils.write(object.monthDay));
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.offsetDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetDateTime", OffsetDateTimeUtils.write(object.offsetDateTime));
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.offsetTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetTime", OffsetTimeUtils.write(object.offsetTime));
    }

    // field period (mapped with "period")
    if (object.period!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("period", PeriodUtils.write(object.period));
    }

    // field year (mapped with "year")
    if (object.year!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", YearUtils.write(object.year));
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.yearMonth!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yearMonth", YearMonthUtils.write(object.yearMonth));
    }

    // field zoneId (mapped with "zoneId")
    if (object.zoneId!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneId", ZoneIdUtils.write(object.zoneId));
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.zoneOffset!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneOffset", ZoneOffsetUtils.write(object.zoneOffset));
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.zonedDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zonedDateTime", ZonedDateTimeUtils.write(object.zonedDateTime));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field duration (mapped with "duration")
    if (object.duration!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("duration", DurationUtils.write(object.duration));
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field istant (mapped with "istant")
    if (object.istant!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("istant", InstantUtils.write(object.istant));
    }

    // field localDate (mapped with "localDate")
    if (object.localDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDate", LocalDateUtils.write(object.localDate));
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.localDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localDateTime", LocalDateTimeUtils.write(object.localDateTime));
    }

    // field localTime (mapped with "localTime")
    if (object.localTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("localTime", LocalTimeUtils.write(object.localTime));
    }

    // field monthDay (mapped with "monthDay")
    if (object.monthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("monthDay", MonthDayUtils.write(object.monthDay));
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.offsetDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetDateTime", OffsetDateTimeUtils.write(object.offsetDateTime));
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.offsetTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("offsetTime", OffsetTimeUtils.write(object.offsetTime));
    }

    // field period (mapped with "period")
    if (object.period!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("period", PeriodUtils.write(object.period));
    }

    // field year (mapped with "year")
    if (object.year!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", YearUtils.write(object.year));
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.yearMonth!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yearMonth", YearMonthUtils.write(object.yearMonth));
    }

    // field zoneId (mapped with "zoneId")
    if (object.zoneId!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneId", ZoneIdUtils.write(object.zoneId));
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.zoneOffset!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zoneOffset", ZoneOffsetUtils.write(object.zoneOffset));
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.zonedDateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zonedDateTime", ZonedDateTimeUtils.write(object.zonedDateTime));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean");
    }

    // Persisted fields:

    // field duration (mapped with "duration")
    if (object.duration!=null)  {
      xmlSerializer.writeStartElement("duration");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DurationUtils.write(object.duration)));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field istant (mapped with "istant")
    if (object.istant!=null)  {
      xmlSerializer.writeStartElement("istant");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(InstantUtils.write(object.istant)));
      xmlSerializer.writeEndElement();
    }

    // field localDate (mapped with "localDate")
    if (object.localDate!=null)  {
      xmlSerializer.writeStartElement("localDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalDateUtils.write(object.localDate)));
      xmlSerializer.writeEndElement();
    }

    // field localDateTime (mapped with "localDateTime")
    if (object.localDateTime!=null)  {
      xmlSerializer.writeStartElement("localDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalDateTimeUtils.write(object.localDateTime)));
      xmlSerializer.writeEndElement();
    }

    // field localTime (mapped with "localTime")
    if (object.localTime!=null)  {
      xmlSerializer.writeStartElement("localTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocalTimeUtils.write(object.localTime)));
      xmlSerializer.writeEndElement();
    }

    // field monthDay (mapped with "monthDay")
    if (object.monthDay!=null)  {
      xmlSerializer.writeStartElement("monthDay");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(MonthDayUtils.write(object.monthDay)));
      xmlSerializer.writeEndElement();
    }

    // field offsetDateTime (mapped with "offsetDateTime")
    if (object.offsetDateTime!=null)  {
      xmlSerializer.writeStartElement("offsetDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(OffsetDateTimeUtils.write(object.offsetDateTime)));
      xmlSerializer.writeEndElement();
    }

    // field offsetTime (mapped with "offsetTime")
    if (object.offsetTime!=null)  {
      xmlSerializer.writeStartElement("offsetTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(OffsetTimeUtils.write(object.offsetTime)));
      xmlSerializer.writeEndElement();
    }

    // field period (mapped with "period")
    if (object.period!=null)  {
      xmlSerializer.writeStartElement("period");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(PeriodUtils.write(object.period)));
      xmlSerializer.writeEndElement();
    }

    // field year (mapped with "year")
    if (object.year!=null)  {
      xmlSerializer.writeStartElement("year");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(YearUtils.write(object.year)));
      xmlSerializer.writeEndElement();
    }

    // field yearMonth (mapped with "yearMonth")
    if (object.yearMonth!=null)  {
      xmlSerializer.writeStartElement("yearMonth");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(YearMonthUtils.write(object.yearMonth)));
      xmlSerializer.writeEndElement();
    }

    // field zoneId (mapped with "zoneId")
    if (object.zoneId!=null)  {
      xmlSerializer.writeStartElement("zoneId");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZoneIdUtils.write(object.zoneId)));
      xmlSerializer.writeEndElement();
    }

    // field zoneOffset (mapped with "zoneOffset")
    if (object.zoneOffset!=null)  {
      xmlSerializer.writeStartElement("zoneOffset");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZoneOffsetUtils.write(object.zoneOffset)));
      xmlSerializer.writeEndElement();
    }

    // field zonedDateTime (mapped with "zonedDateTime")
    if (object.zonedDateTime!=null)  {
      xmlSerializer.writeStartElement("zonedDateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZonedDateTimeUtils.write(object.zonedDateTime)));
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
  public Bean parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean instance = new Bean();
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
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
              instance.duration=DurationUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "istant":
            // field istant (mapped with "istant")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.istant=InstantUtils.read(jacksonParser.getText());
            }
          break;
          case "localDate":
            // field localDate (mapped with "localDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localDate=LocalDateUtils.read(jacksonParser.getText());
            }
          break;
          case "localDateTime":
            // field localDateTime (mapped with "localDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localDateTime=LocalDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "localTime":
            // field localTime (mapped with "localTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localTime=LocalTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "monthDay":
            // field monthDay (mapped with "monthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.monthDay=MonthDayUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetDateTime":
            // field offsetDateTime (mapped with "offsetDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.offsetDateTime=OffsetDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetTime":
            // field offsetTime (mapped with "offsetTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.offsetTime=OffsetTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "period":
            // field period (mapped with "period")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.period=PeriodUtils.read(jacksonParser.getText());
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.year=YearUtils.read(jacksonParser.getText());
            }
          break;
          case "yearMonth":
            // field yearMonth (mapped with "yearMonth")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.yearMonth=YearMonthUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneId":
            // field zoneId (mapped with "zoneId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zoneId=ZoneIdUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneOffset":
            // field zoneOffset (mapped with "zoneOffset")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zoneOffset=ZoneOffsetUtils.read(jacksonParser.getText());
            }
          break;
          case "zonedDateTime":
            // field zonedDateTime (mapped with "zonedDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zonedDateTime=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean instance = new Bean();
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
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
              instance.duration=DurationUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "istant":
            // field istant (mapped with "istant")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.istant=InstantUtils.read(jacksonParser.getText());
            }
          break;
          case "localDate":
            // field localDate (mapped with "localDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localDate=LocalDateUtils.read(jacksonParser.getText());
            }
          break;
          case "localDateTime":
            // field localDateTime (mapped with "localDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localDateTime=LocalDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "localTime":
            // field localTime (mapped with "localTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.localTime=LocalTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "monthDay":
            // field monthDay (mapped with "monthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.monthDay=MonthDayUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetDateTime":
            // field offsetDateTime (mapped with "offsetDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.offsetDateTime=OffsetDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "offsetTime":
            // field offsetTime (mapped with "offsetTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.offsetTime=OffsetTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "period":
            // field period (mapped with "period")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.period=PeriodUtils.read(jacksonParser.getText());
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.year=YearUtils.read(jacksonParser.getText());
            }
          break;
          case "yearMonth":
            // field yearMonth (mapped with "yearMonth")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.yearMonth=YearMonthUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneId":
            // field zoneId (mapped with "zoneId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zoneId=ZoneIdUtils.read(jacksonParser.getText());
            }
          break;
          case "zoneOffset":
            // field zoneOffset (mapped with "zoneOffset")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zoneOffset=ZoneOffsetUtils.read(jacksonParser.getText());
            }
          break;
          case "zonedDateTime":
            // field zonedDateTime (mapped with "zonedDateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zonedDateTime=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Bean parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean instance = new Bean();
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
                  instance.duration=DurationUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "istant":
                  // property istant (mapped on "istant")
                  instance.istant=InstantUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localDate":
                  // property localDate (mapped on "localDate")
                  instance.localDate=LocalDateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localDateTime":
                  // property localDateTime (mapped on "localDateTime")
                  instance.localDateTime=LocalDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "localTime":
                  // property localTime (mapped on "localTime")
                  instance.localTime=LocalTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "monthDay":
                  // property monthDay (mapped on "monthDay")
                  instance.monthDay=MonthDayUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "offsetDateTime":
                  // property offsetDateTime (mapped on "offsetDateTime")
                  instance.offsetDateTime=OffsetDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "offsetTime":
                  // property offsetTime (mapped on "offsetTime")
                  instance.offsetTime=OffsetTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "period":
                  // property period (mapped on "period")
                  instance.period=PeriodUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "year":
                  // property year (mapped on "year")
                  instance.year=YearUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "yearMonth":
                  // property yearMonth (mapped on "yearMonth")
                  instance.yearMonth=YearMonthUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zoneId":
                  // property zoneId (mapped on "zoneId")
                  instance.zoneId=ZoneIdUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zoneOffset":
                  // property zoneOffset (mapped on "zoneOffset")
                  instance.zoneOffset=ZoneOffsetUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "zonedDateTime":
                  // property zonedDateTime (mapped on "zonedDateTime")
                  instance.zonedDateTime=ZonedDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
      return instance;
    }

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
