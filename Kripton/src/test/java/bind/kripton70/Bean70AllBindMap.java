package bind.kripton70;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Bean70All
 *
 * @see Bean70All
 */
@BindMap(Bean70All.class)
public class Bean70AllBindMap extends AbstractMapper<Bean70All> {
  /**
   * Bean70AllBindMap */
  private Bean70AllBindMap bean70AllBindMap = this;

  @Override
  public int serializeOnJackson(Bean70All object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      bean70AllBindMap.serializeOnJackson(object.valueBean, jacksonSerializer);
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.valueBigDecimal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.valueBigInteger!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
    }

    // field valueBool (mapped with "valueBool")
    if (object.valueBool!=null)  {
      fieldCount++;
      jacksonSerializer.writeBooleanField("valueBool", object.valueBool);
    }

    // field valueBoolType (mapped with "valueBoolType")
    fieldCount++;
    jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

    // field valueByte (mapped with "valueByte")
    if (object.valueByte!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueByte", object.valueByte);
    }

    // field valueByteType (mapped with "valueByteType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueByteType", object.valueByteType);

    // field valueCalendar (mapped with "valueCalendar")
    if (object.valueCalendar!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.valueCalendar));
    }

    // field valueChar (mapped with "valueChar")
    if (object.valueChar!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueChar", object.valueChar);
    }

    // field valueCharType (mapped with "valueCharType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueCharType", object.valueCharType);

    // field valueContentBoolType (mapped with "valueContentBoolType")
    if (object.valueContentBoolType!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueContentBoolType", object.valueContentBoolType);
    }

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
    }

    // field valueDate (mapped with "valueDate")
    if (object.valueDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.valueDate));
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.valueDouble!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueDouble", object.valueDouble);
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueDoubleType", object.valueDoubleType);

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.valueFloat!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueFloat", object.valueFloat);
    }

    // field valueFloatType (mapped with "valueFloatType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueFloatType", object.valueFloatType);

    // field valueInt (mapped with "valueInt")
    if (object.valueInt!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueInt", object.valueInt);
    }

    // field valueIntType (mapped with "valueIntType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueIntType", object.valueIntType);

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
    }

    // field valueLong (mapped with "valueLong")
    if (object.valueLong!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueLong", object.valueLong);
    }

    // field valueLongType (mapped with "valueLongType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

    // field valueShort (mapped with "valueShort")
    if (object.valueShort!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueShort", object.valueShort);
    }

    // field valueShortType (mapped with "valueShortType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueTime (mapped with "valueTime")
    if (object.valueTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.valueTime));
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.valueTimeZone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.valueTimeZone));
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.valueUrl!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.valueUrl));
    }

    // field id (mapped with "typeName")
    fieldCount++;
    jacksonSerializer.writeNumberField("typeName", object.getId());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean70All object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      if (bean70AllBindMap.serializeOnJacksonAsString(object.valueBean, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("valueBean");
      }
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.valueBigDecimal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.valueBigInteger!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
    }

    // field valueBool (mapped with "valueBool")
    if (object.valueBool!=null)  {
      jacksonSerializer.writeStringField("valueBool", PrimitiveUtils.writeBoolean(object.valueBool));
    }

    // field valueBoolType (mapped with "valueBoolType")
    jacksonSerializer.writeStringField("valueBoolType", PrimitiveUtils.writeBoolean(object.valueBoolType));

    // field valueByte (mapped with "valueByte")
    if (object.valueByte!=null)  {
      jacksonSerializer.writeStringField("valueByte", PrimitiveUtils.writeByte(object.valueByte));
    }

    // field valueByteType (mapped with "valueByteType")
    jacksonSerializer.writeStringField("valueByteType", PrimitiveUtils.writeByte(object.valueByteType));

    // field valueCalendar (mapped with "valueCalendar")
    if (object.valueCalendar!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.valueCalendar));
    }

    // field valueChar (mapped with "valueChar")
    if (object.valueChar!=null)  {
      jacksonSerializer.writeStringField("valueChar", PrimitiveUtils.writeCharacter(object.valueChar));
    }

    // field valueCharType (mapped with "valueCharType")
    jacksonSerializer.writeStringField("valueCharType", PrimitiveUtils.writeCharacter(object.valueCharType));

    // field valueContentBoolType (mapped with "valueContentBoolType")
    if (object.valueContentBoolType!=null)  {
      jacksonSerializer.writeStringField("valueContentBoolType", PrimitiveUtils.writeInteger(object.valueContentBoolType));
    }

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
    }

    // field valueDate (mapped with "valueDate")
    if (object.valueDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.valueDate));
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.valueDouble!=null)  {
      jacksonSerializer.writeStringField("valueDouble", PrimitiveUtils.writeDouble(object.valueDouble));
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    jacksonSerializer.writeStringField("valueDoubleType", PrimitiveUtils.writeDouble(object.valueDoubleType));

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.valueFloat!=null)  {
      jacksonSerializer.writeStringField("valueFloat", PrimitiveUtils.writeFloat(object.valueFloat));
    }

    // field valueFloatType (mapped with "valueFloatType")
    jacksonSerializer.writeStringField("valueFloatType", PrimitiveUtils.writeFloat(object.valueFloatType));

    // field valueInt (mapped with "valueInt")
    if (object.valueInt!=null)  {
      jacksonSerializer.writeStringField("valueInt", PrimitiveUtils.writeInteger(object.valueInt));
    }

    // field valueIntType (mapped with "valueIntType")
    jacksonSerializer.writeStringField("valueIntType", PrimitiveUtils.writeInteger(object.valueIntType));

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
    }

    // field valueLong (mapped with "valueLong")
    if (object.valueLong!=null)  {
      jacksonSerializer.writeStringField("valueLong", PrimitiveUtils.writeLong(object.valueLong));
    }

    // field valueLongType (mapped with "valueLongType")
    jacksonSerializer.writeStringField("valueLongType", PrimitiveUtils.writeLong(object.valueLongType));

    // field valueShort (mapped with "valueShort")
    if (object.valueShort!=null)  {
      jacksonSerializer.writeStringField("valueShort", PrimitiveUtils.writeShort(object.valueShort));
    }

    // field valueShortType (mapped with "valueShortType")
    jacksonSerializer.writeStringField("valueShortType", PrimitiveUtils.writeShort(object.valueShortType));

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueTime (mapped with "valueTime")
    if (object.valueTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.valueTime));
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.valueTimeZone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.valueTimeZone));
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.valueUrl!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.valueUrl));
    }

    // field id (mapped with "typeName")
    jacksonSerializer.writeStringField("typeName", PrimitiveUtils.writeLong(object.getId()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean70All object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("root");
    }

    // Persisted fields:

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      xmlSerializer.writeStartElement("valueBean");
      bean70AllBindMap.serializeOnXml(object.valueBean, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.valueBigDecimal!=null)  {
      xmlSerializer.writeStartElement("valueBigDecimal");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(object.valueBigDecimal)));
      xmlSerializer.writeEndElement();
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.valueBigInteger!=null)  {
      xmlSerializer.writeStartElement("valueBigInteger");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtils.write(object.valueBigInteger)));
      xmlSerializer.writeEndElement();
    }

    // field valueBool (mapped with "valueBool")
    if (object.valueBool!=null)  {
      xmlSerializer.writeStartElement("valueBool");
      xmlSerializer.writeBoolean(object.valueBool);
      xmlSerializer.writeEndElement();
    }

    // field valueBoolType (mapped with "valueBoolType")
    xmlSerializer.writeStartElement("valueBoolType");
    xmlSerializer.writeBoolean(object.valueBoolType);
    xmlSerializer.writeEndElement();

    // field valueByte (mapped with "valueByte")
    if (object.valueByte!=null)  {
      xmlSerializer.writeStartElement("valueByte");
      xmlSerializer.writeInt(object.valueByte);
      xmlSerializer.writeEndElement();
    }

    // field valueByteType (mapped with "valueByteType")
    xmlSerializer.writeStartElement("valueByteType");
    xmlSerializer.writeInt(object.valueByteType);
    xmlSerializer.writeEndElement();

    // field valueCalendar (mapped with "valueCalendar")
    if (object.valueCalendar!=null)  {
      xmlSerializer.writeStartElement("valueCalendar");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CalendarUtils.write(object.valueCalendar)));
      xmlSerializer.writeEndElement();
    }

    // field valueChar (mapped with "valueChar")
    if (object.valueChar!=null)  {
      xmlSerializer.writeStartElement("valueChar");
      xmlSerializer.writeInt(object.valueChar);
      xmlSerializer.writeEndElement();
    }

    // field valueCharType (mapped with "valueCharType")
    xmlSerializer.writeStartElement("valueCharType");
    xmlSerializer.writeInt(object.valueCharType);
    xmlSerializer.writeEndElement();

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      xmlSerializer.writeStartElement("valueCurrency");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CurrencyUtils.write(object.valueCurrency)));
      xmlSerializer.writeEndElement();
    }

    // field valueDate (mapped with "valueDate")
    if (object.valueDate!=null)  {
      xmlSerializer.writeStartElement("valueDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.valueDate)));
      xmlSerializer.writeEndElement();
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.valueDouble!=null)  {
      xmlSerializer.writeStartElement("valueDouble");
      xmlSerializer.writeDouble(object.valueDouble);
      xmlSerializer.writeEndElement();
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    xmlSerializer.writeStartElement("valueDoubleType");
    xmlSerializer.writeDouble(object.valueDoubleType);
    xmlSerializer.writeEndElement();

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      xmlSerializer.writeStartElement("valueEnum");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueEnum.toString()));
      xmlSerializer.writeEndElement();
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.valueFloat!=null)  {
      xmlSerializer.writeStartElement("valueFloat");
      xmlSerializer.writeFloat(object.valueFloat);
      xmlSerializer.writeEndElement();
    }

    // field valueFloatType (mapped with "valueFloatType")
    xmlSerializer.writeStartElement("valueFloatType");
    xmlSerializer.writeFloat(object.valueFloatType);
    xmlSerializer.writeEndElement();

    // field valueInt (mapped with "valueInt")
    if (object.valueInt!=null)  {
      xmlSerializer.writeStartElement("valueInt");
      xmlSerializer.writeInt(object.valueInt);
      xmlSerializer.writeEndElement();
    }

    // field valueIntType (mapped with "valueIntType")
    xmlSerializer.writeStartElement("valueIntType");
    xmlSerializer.writeInt(object.valueIntType);
    xmlSerializer.writeEndElement();

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      xmlSerializer.writeStartElement("valueLocale");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(object.valueLocale)));
      xmlSerializer.writeEndElement();
    }

    // field valueLong (mapped with "valueLong")
    if (object.valueLong!=null)  {
      xmlSerializer.writeStartElement("valueLong");
      xmlSerializer.writeLong(object.valueLong);
      xmlSerializer.writeEndElement();
    }

    // field valueLongType (mapped with "valueLongType")
    xmlSerializer.writeStartElement("valueLongType");
    xmlSerializer.writeLong(object.valueLongType);
    xmlSerializer.writeEndElement();

    // field valueShort (mapped with "valueShort")
    if (object.valueShort!=null)  {
      xmlSerializer.writeStartElement("valueShort");
      xmlSerializer.writeInt(object.valueShort);
      xmlSerializer.writeEndElement();
    }

    // field valueShortType (mapped with "valueShortType")
    xmlSerializer.writeStartElement("valueShortType");
    xmlSerializer.writeInt(object.valueShortType);
    xmlSerializer.writeEndElement();

    // field valueString (mapped with "valueString")
    if (object.valueString!=null) {
      xmlSerializer.writeStartElement("valueString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
      xmlSerializer.writeEndElement();
    }

    // field valueTime (mapped with "valueTime")
    if (object.valueTime!=null)  {
      xmlSerializer.writeStartElement("valueTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLTimeUtils.write(object.valueTime)));
      xmlSerializer.writeEndElement();
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.valueTimeZone!=null)  {
      xmlSerializer.writeStartElement("valueTimeZone");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeZoneUtils.write(object.valueTimeZone)));
      xmlSerializer.writeEndElement();
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.valueUrl!=null)  {
      xmlSerializer.writeStartElement("valueUrl");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.valueUrl)));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "typeName")
    xmlSerializer.writeStartElement("typeName");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field valueContentBoolType (mapped with "valueContentBoolType")
    if (object.valueContentBoolType!=null)  {
      xmlSerializer.writeCData(PrimitiveUtils.writeInteger(object.valueContentBoolType));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean70All parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean70All instance = new Bean70All();
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
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.valueBean=bean70AllBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "valueBigDecimal":
            // field valueBigDecimal (mapped with "valueBigDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBigInteger":
            // field valueBigInteger (mapped with "valueBigInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBool":
            // field valueBool (mapped with "valueBool")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBool=jacksonParser.getBooleanValue();
            }
          break;
          case "valueBoolType":
            // field valueBoolType (mapped with "valueBoolType")
            instance.valueBoolType=jacksonParser.getBooleanValue();
          break;
          case "valueByte":
            // field valueByte (mapped with "valueByte")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByte=jacksonParser.getByteValue();
            }
          break;
          case "valueByteType":
            // field valueByteType (mapped with "valueByteType")
            instance.valueByteType=jacksonParser.getByteValue();
          break;
          case "valueCalendar":
            // field valueCalendar (mapped with "valueCalendar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCalendar=CalendarUtils.read(jacksonParser.getText());
            }
          break;
          case "valueChar":
            // field valueChar (mapped with "valueChar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueChar=Character.valueOf((char)jacksonParser.getIntValue());
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.valueCharType=Character.valueOf((char)jacksonParser.getIntValue());
          break;
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCurrency=CurrencyUtils.read(jacksonParser.getText());
            }
          break;
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueDate=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "valueDouble":
            // field valueDouble (mapped with "valueDouble")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueDouble=jacksonParser.getDoubleValue();
            }
          break;
          case "valueDoubleType":
            // field valueDoubleType (mapped with "valueDoubleType")
            instance.valueDoubleType=jacksonParser.getDoubleValue();
          break;
          case "valueEnum":
            // field valueEnum (mapped with "valueEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnum=StringUtils.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
            }
          break;
          case "valueFloat":
            // field valueFloat (mapped with "valueFloat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueFloat=jacksonParser.getFloatValue();
            }
          break;
          case "valueFloatType":
            // field valueFloatType (mapped with "valueFloatType")
            instance.valueFloatType=jacksonParser.getFloatValue();
          break;
          case "valueInt":
            // field valueInt (mapped with "valueInt")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueInt=jacksonParser.getIntValue();
            }
          break;
          case "valueIntType":
            // field valueIntType (mapped with "valueIntType")
            instance.valueIntType=jacksonParser.getIntValue();
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLocale=LocaleUtils.read(jacksonParser.getText());
            }
          break;
          case "valueLong":
            // field valueLong (mapped with "valueLong")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLong=jacksonParser.getLongValue();
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.valueLongType=jacksonParser.getLongValue();
          break;
          case "valueShort":
            // field valueShort (mapped with "valueShort")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueShort=jacksonParser.getShortValue();
            }
          break;
          case "valueShortType":
            // field valueShortType (mapped with "valueShortType")
            instance.valueShortType=jacksonParser.getShortValue();
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTime=SQLTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "valueTimeZone":
            // field valueTimeZone (mapped with "valueTimeZone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTimeZone=TimeZoneUtils.read(jacksonParser.getText());
            }
          break;
          case "valueUrl":
            // field valueUrl (mapped with "valueUrl")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueUrl=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "typeName":
            // field id (mapped with "typeName")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "valueContentBoolType":
            // field valueContentBoolType (mapped with "valueContentBoolType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueContentBoolType=jacksonParser.getIntValue();
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
  public Bean70All parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean70All instance = new Bean70All();
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
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.valueBean=bean70AllBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "valueBigDecimal":
            // field valueBigDecimal (mapped with "valueBigDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBigInteger":
            // field valueBigInteger (mapped with "valueBigInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBool":
            // field valueBool (mapped with "valueBool")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBool=PrimitiveUtils.readBoolean(jacksonParser.getText(), null);
            }
          break;
          case "valueBoolType":
            // field valueBoolType (mapped with "valueBoolType")
            instance.valueBoolType=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "valueByte":
            // field valueByte (mapped with "valueByte")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByte=PrimitiveUtils.readByte(jacksonParser.getText(), null);
            }
          break;
          case "valueByteType":
            // field valueByteType (mapped with "valueByteType")
            instance.valueByteType=PrimitiveUtils.readByte(jacksonParser.getText(), (byte)0);
          break;
          case "valueCalendar":
            // field valueCalendar (mapped with "valueCalendar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCalendar=CalendarUtils.read(jacksonParser.getText());
            }
          break;
          case "valueChar":
            // field valueChar (mapped with "valueChar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueChar=PrimitiveUtils.readCharacter(jacksonParser.getText(), null);
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.valueCharType=PrimitiveUtils.readCharacter(jacksonParser.getText(), ' ');
          break;
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCurrency=CurrencyUtils.read(jacksonParser.getText());
            }
          break;
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueDate=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "valueDouble":
            // field valueDouble (mapped with "valueDouble")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueDouble=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
            }
          break;
          case "valueDoubleType":
            // field valueDoubleType (mapped with "valueDoubleType")
            instance.valueDoubleType=PrimitiveUtils.readDouble(jacksonParser.getText(), 0.0);
          break;
          case "valueEnum":
            // field valueEnum (mapped with "valueEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnum=StringUtils.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
            }
          break;
          case "valueFloat":
            // field valueFloat (mapped with "valueFloat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueFloat=PrimitiveUtils.readFloat(jacksonParser.getText(), null);
            }
          break;
          case "valueFloatType":
            // field valueFloatType (mapped with "valueFloatType")
            instance.valueFloatType=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "valueInt":
            // field valueInt (mapped with "valueInt")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueInt=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
            }
          break;
          case "valueIntType":
            // field valueIntType (mapped with "valueIntType")
            instance.valueIntType=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLocale=LocaleUtils.read(jacksonParser.getText());
            }
          break;
          case "valueLong":
            // field valueLong (mapped with "valueLong")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLong=PrimitiveUtils.readLong(jacksonParser.getText(), null);
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.valueLongType=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueShort":
            // field valueShort (mapped with "valueShort")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueShort=PrimitiveUtils.readShort(jacksonParser.getText(), null);
            }
          break;
          case "valueShortType":
            // field valueShortType (mapped with "valueShortType")
            instance.valueShortType=PrimitiveUtils.readShort(jacksonParser.getText(), (short)0);
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTime=SQLTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "valueTimeZone":
            // field valueTimeZone (mapped with "valueTimeZone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTimeZone=TimeZoneUtils.read(jacksonParser.getText());
            }
          break;
          case "valueUrl":
            // field valueUrl (mapped with "valueUrl")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueUrl=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "typeName":
            // field id (mapped with "typeName")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "valueContentBoolType":
            // field valueContentBoolType (mapped with "valueContentBoolType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueContentBoolType=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
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
  public Bean70All parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean70All instance = new Bean70All();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
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
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "valueBean":
                  // property valueBean (mapped on "valueBean")
                  instance.valueBean=bean70AllBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "valueBigDecimal":
                  // property valueBigDecimal (mapped on "valueBigDecimal")
                  instance.valueBigDecimal=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueBigInteger":
                  // property valueBigInteger (mapped on "valueBigInteger")
                  instance.valueBigInteger=BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueBool":
                  // property valueBool (mapped on "valueBool")
                  instance.valueBool=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), null);
                break;
                case "valueBoolType":
                  // property valueBoolType (mapped on "valueBoolType")
                  instance.valueBoolType=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "valueByte":
                  // property valueByte (mapped on "valueByte")
                  instance.valueByte=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                break;
                case "valueByteType":
                  // property valueByteType (mapped on "valueByteType")
                  instance.valueByteType=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), (byte)0);
                break;
                case "valueCalendar":
                  // property valueCalendar (mapped on "valueCalendar")
                  instance.valueCalendar=CalendarUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueChar":
                  // property valueChar (mapped on "valueChar")
                  instance.valueChar=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                break;
                case "valueCharType":
                  // property valueCharType (mapped on "valueCharType")
                  instance.valueCharType=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                break;
                case "valueCurrency":
                  // property valueCurrency (mapped on "valueCurrency")
                  instance.valueCurrency=CurrencyUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueDate":
                  // property valueDate (mapped on "valueDate")
                  instance.valueDate=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueDouble":
                  // property valueDouble (mapped on "valueDouble")
                  instance.valueDouble=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                break;
                case "valueDoubleType":
                  // property valueDoubleType (mapped on "valueDoubleType")
                  instance.valueDoubleType=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), 0.0);
                break;
                case "valueEnum":
                  // property valueEnum (mapped on "valueEnum")
                  instance.valueEnum=BeanEnum.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueFloat":
                  // property valueFloat (mapped on "valueFloat")
                  instance.valueFloat=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                break;
                case "valueFloatType":
                  // property valueFloatType (mapped on "valueFloatType")
                  instance.valueFloatType=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
                break;
                case "valueInt":
                  // property valueInt (mapped on "valueInt")
                  instance.valueInt=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                break;
                case "valueIntType":
                  // property valueIntType (mapped on "valueIntType")
                  instance.valueIntType=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "valueLocale":
                  // property valueLocale (mapped on "valueLocale")
                  instance.valueLocale=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueLong":
                  // property valueLong (mapped on "valueLong")
                  instance.valueLong=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                break;
                case "valueLongType":
                  // property valueLongType (mapped on "valueLongType")
                  instance.valueLongType=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "valueShort":
                  // property valueShort (mapped on "valueShort")
                  instance.valueShort=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                break;
                case "valueShortType":
                  // property valueShortType (mapped on "valueShortType")
                  instance.valueShortType=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), (short)0);
                break;
                case "valueString":
                  // property valueString (mapped on "valueString")
                  instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueTime":
                  // property valueTime (mapped on "valueTime")
                  instance.valueTime=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueTimeZone":
                  // property valueTimeZone (mapped on "valueTimeZone")
                  instance.valueTimeZone=TimeZoneUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueUrl":
                  // property valueUrl (mapped on "valueUrl")
                  instance.valueUrl=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "typeName":
                  // property id (mapped on "typeName")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                default:
                break;
              }
            break;
            case XmlPullParser.END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case XmlPullParser.CDSECT:
            case XmlPullParser.TEXT:
              if (elementName!=null && xmlParser.hasText()) {
                // property valueContentBoolType
                instance.valueContentBoolType=PrimitiveUtils.readInteger(xmlParser.getText(), null);
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
