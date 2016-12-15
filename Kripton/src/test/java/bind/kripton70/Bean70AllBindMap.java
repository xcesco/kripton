package bind.kripton70;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.AbstractJacksonContext;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.core.AbstractMapper;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean70All
 *
 * @see Bean70All
 */
@BindMap
public class Bean70AllBindMap extends AbstractMapper<Bean70All> {
  /**
   * create new object instance
   */
  @Override
  public Bean70All createInstance() {
    return new Bean70All();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean70All object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueBean
      if (object.valueBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("valueBean");
        context.mapperFor(Bean70All.class).serializeOnJackson(context, object.valueBean, wrapper);
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
      }

      // field valueBool
      if (object.valueBool!=null)  {
        fieldCount++;
        jacksonSerializer.writeBooleanField("valueBool", object.valueBool);
      }

      // field valueBoolType
      fieldCount++;
      jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

      // field valueByte
      if (object.valueByte!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueByte", object.valueByte);
      }

      // field valueByteType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueByteType", object.valueByteType);

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.valueCalendar));
      }

      // field valueChar
      if (object.valueChar!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueChar", object.valueChar);
      }

      // field valueCharType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueCharType", object.valueCharType);

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueContentBoolType", object.valueContentBoolType);
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.valueDate));
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueDouble", object.valueDouble);
      }

      // field valueDoubleType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueDoubleType", object.valueDoubleType);

      // field valueEnum
      if (object.valueEnum!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueFloat", object.valueFloat);
      }

      // field valueFloatType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueFloatType", object.valueFloatType);

      // field valueInt
      if (object.valueInt!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueInt", object.valueInt);
      }

      // field valueIntType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueIntType", object.valueIntType);

      // field valueLocale
      if (object.valueLocale!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
      }

      // field valueLong
      if (object.valueLong!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueLong", object.valueLong);
      }

      // field valueLongType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

      // field valueShort
      if (object.valueShort!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueShort", object.valueShort);
      }

      // field valueShortType
      fieldCount++;
      jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueTime
      if (object.valueTime!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueTime", TimeUtils.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.valueUrl));
      }

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.getId());

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean70All object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueBean
      if (object.valueBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("valueBean");
        if (context.mapperFor(Bean70All.class).serializeOnJacksonAsString(context, object.valueBean, wrapper)==0) {
          jacksonSerializer.writeNullField("valueBean");
        }
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
      }

      // field valueBool
      if (object.valueBool!=null)  {
        jacksonSerializer.writeStringField("valueBool", PrimitiveUtils.writeBoolean(object.valueBool));
      }

      // field valueBoolType
      jacksonSerializer.writeStringField("valueBoolType", PrimitiveUtils.writeBoolean(object.valueBoolType));

      // field valueByte
      if (object.valueByte!=null)  {
        jacksonSerializer.writeStringField("valueByte", PrimitiveUtils.writeByte(object.valueByte));
      }

      // field valueByteType
      jacksonSerializer.writeStringField("valueByteType", PrimitiveUtils.writeByte(object.valueByteType));

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.valueCalendar));
      }

      // field valueChar
      if (object.valueChar!=null)  {
        jacksonSerializer.writeStringField("valueChar", PrimitiveUtils.writeCharacter(object.valueChar));
      }

      // field valueCharType
      jacksonSerializer.writeStringField("valueCharType", PrimitiveUtils.writeCharacter(object.valueCharType));

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        jacksonSerializer.writeStringField("valueContentBoolType", PrimitiveUtils.writeInteger(object.valueContentBoolType));
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.valueDate));
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        jacksonSerializer.writeStringField("valueDouble", PrimitiveUtils.writeDouble(object.valueDouble));
      }

      // field valueDoubleType
      jacksonSerializer.writeStringField("valueDoubleType", PrimitiveUtils.writeDouble(object.valueDoubleType));

      // field valueEnum
      if (object.valueEnum!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        jacksonSerializer.writeStringField("valueFloat", PrimitiveUtils.writeFloat(object.valueFloat));
      }

      // field valueFloatType
      jacksonSerializer.writeStringField("valueFloatType", PrimitiveUtils.writeFloat(object.valueFloatType));

      // field valueInt
      if (object.valueInt!=null)  {
        jacksonSerializer.writeStringField("valueInt", PrimitiveUtils.writeInteger(object.valueInt));
      }

      // field valueIntType
      jacksonSerializer.writeStringField("valueIntType", PrimitiveUtils.writeInteger(object.valueIntType));

      // field valueLocale
      if (object.valueLocale!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
      }

      // field valueLong
      if (object.valueLong!=null)  {
        jacksonSerializer.writeStringField("valueLong", PrimitiveUtils.writeLong(object.valueLong));
      }

      // field valueLongType
      jacksonSerializer.writeStringField("valueLongType", PrimitiveUtils.writeLong(object.valueLongType));

      // field valueShort
      if (object.valueShort!=null)  {
        jacksonSerializer.writeStringField("valueShort", PrimitiveUtils.writeShort(object.valueShort));
      }

      // field valueShortType
      jacksonSerializer.writeStringField("valueShortType", PrimitiveUtils.writeShort(object.valueShortType));

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueTime
      if (object.valueTime!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueTime", TimeUtils.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.valueUrl));
      }

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, Bean70All object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueBean
      if (object.valueBean!=null)  {
        xmlSerializer.writeStartElement("valueBean");
        context.mapperFor(Bean70All.class).serializeOnXml(context, object.valueBean, wrapper, 1);
        xmlSerializer.writeEndElement();
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        xmlSerializer.writeStartElement("valueBigDecimal");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(object.valueBigDecimal)));
        xmlSerializer.writeEndElement();
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        xmlSerializer.writeStartElement("valueBigInteger");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtils.write(object.valueBigInteger)));
        xmlSerializer.writeEndElement();
      }

      // field valueBool
      if (object.valueBool!=null)  {
        xmlSerializer.writeStartElement("valueBool");
        xmlSerializer.writeBoolean(object.valueBool);
        xmlSerializer.writeEndElement();
      }

      // field valueBoolType
      xmlSerializer.writeStartElement("valueBoolType");
      xmlSerializer.writeBoolean(object.valueBoolType);
      xmlSerializer.writeEndElement();

      // field valueByte
      if (object.valueByte!=null)  {
        xmlSerializer.writeStartElement("valueByte");
        xmlSerializer.writeInt(object.valueByte);
        xmlSerializer.writeEndElement();
      }

      // field valueByteType
      xmlSerializer.writeStartElement("valueByteType");
      xmlSerializer.writeInt(object.valueByteType);
      xmlSerializer.writeEndElement();

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        xmlSerializer.writeStartElement("valueCalendar");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CalendarUtils.write(object.valueCalendar)));
        xmlSerializer.writeEndElement();
      }

      // field valueChar
      if (object.valueChar!=null)  {
        xmlSerializer.writeStartElement("valueChar");
        xmlSerializer.writeInt(object.valueChar);
        xmlSerializer.writeEndElement();
      }

      // field valueCharType
      xmlSerializer.writeStartElement("valueCharType");
      xmlSerializer.writeInt(object.valueCharType);
      xmlSerializer.writeEndElement();

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        xmlSerializer.writeStartElement("valueCurrency");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CurrencyUtils.write(object.valueCurrency)));
        xmlSerializer.writeEndElement();
      }

      // field valueDate
      if (object.valueDate!=null)  {
        xmlSerializer.writeStartElement("valueDate");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.valueDate)));
        xmlSerializer.writeEndElement();
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        xmlSerializer.writeStartElement("valueDouble");
        xmlSerializer.writeDouble(object.valueDouble);
        xmlSerializer.writeEndElement();
      }

      // field valueDoubleType
      xmlSerializer.writeStartElement("valueDoubleType");
      xmlSerializer.writeDouble(object.valueDoubleType);
      xmlSerializer.writeEndElement();

      // field valueEnum
      if (object.valueEnum!=null)  {
        xmlSerializer.writeStartElement("valueEnum");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueEnum.toString()));
        xmlSerializer.writeEndElement();
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        xmlSerializer.writeStartElement("valueFloat");
        xmlSerializer.writeFloat(object.valueFloat);
        xmlSerializer.writeEndElement();
      }

      // field valueFloatType
      xmlSerializer.writeStartElement("valueFloatType");
      xmlSerializer.writeFloat(object.valueFloatType);
      xmlSerializer.writeEndElement();

      // field valueInt
      if (object.valueInt!=null)  {
        xmlSerializer.writeStartElement("valueInt");
        xmlSerializer.writeInt(object.valueInt);
        xmlSerializer.writeEndElement();
      }

      // field valueIntType
      xmlSerializer.writeStartElement("valueIntType");
      xmlSerializer.writeInt(object.valueIntType);
      xmlSerializer.writeEndElement();

      // field valueLocale
      if (object.valueLocale!=null)  {
        xmlSerializer.writeStartElement("valueLocale");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(object.valueLocale)));
        xmlSerializer.writeEndElement();
      }

      // field valueLong
      if (object.valueLong!=null)  {
        xmlSerializer.writeStartElement("valueLong");
        xmlSerializer.writeLong(object.valueLong);
        xmlSerializer.writeEndElement();
      }

      // field valueLongType
      xmlSerializer.writeStartElement("valueLongType");
      xmlSerializer.writeLong(object.valueLongType);
      xmlSerializer.writeEndElement();

      // field valueShort
      if (object.valueShort!=null)  {
        xmlSerializer.writeStartElement("valueShort");
        xmlSerializer.writeInt(object.valueShort);
        xmlSerializer.writeEndElement();
      }

      // field valueShortType
      xmlSerializer.writeStartElement("valueShortType");
      xmlSerializer.writeInt(object.valueShortType);
      xmlSerializer.writeEndElement();

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
      }

      // field valueTime
      if (object.valueTime!=null)  {
        xmlSerializer.writeStartElement("valueTime");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeUtils.write(object.valueTime)));
        xmlSerializer.writeEndElement();
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        xmlSerializer.writeStartElement("valueTimeZone");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeZoneUtils.write(object.valueTimeZone)));
        xmlSerializer.writeEndElement();
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        xmlSerializer.writeStartElement("valueUrl");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.valueUrl)));
        xmlSerializer.writeEndElement();
      }

      // field id
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeLong(object.getId());
      xmlSerializer.writeEndElement();

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        xmlSerializer.writeCData(PrimitiveUtils.writeInteger(object.valueContentBoolType));
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70All parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean70All instance = createInstance();
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
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.valueBean=context.mapperFor(Bean70All.class).parseOnJackson(context, wrapper);
              }
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
              }
            break;
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
              }
            break;
            case "valueBool":
              // field valueBool
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBool=jacksonParser.getBooleanValue();
              }
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=jacksonParser.getBooleanValue();
            break;
            case "valueByte":
              // field valueByte
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByte=jacksonParser.getByteValue();
              }
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=jacksonParser.getByteValue();
            break;
            case "valueCalendar":
              // field valueCalendar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCalendar= CalendarUtils.read(jacksonParser.getText());
              }
            break;
            case "valueChar":
              // field valueChar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueChar=Character.valueOf((char)jacksonParser.getIntValue());
              }
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=Character.valueOf((char)jacksonParser.getIntValue());
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency= CurrencyUtils.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate= DateUtils.read(jacksonParser.getText());
              }
            break;
            case "valueDouble":
              // field valueDouble
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDouble=jacksonParser.getDoubleValue();
              }
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=jacksonParser.getDoubleValue();
            break;
            case "valueEnum":
              // field valueEnum
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.valueEnum=StringUtils.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
              }
            break;
            case "valueFloat":
              // field valueFloat
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueFloat=jacksonParser.getFloatValue();
              }
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=jacksonParser.getFloatValue();
            break;
            case "valueInt":
              // field valueInt
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInt=jacksonParser.getIntValue();
              }
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=jacksonParser.getIntValue();
            break;
            case "valueLocale":
              // field valueLocale
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLocale= LocaleUtils.read(jacksonParser.getText());
              }
            break;
            case "valueLong":
              // field valueLong
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLong=jacksonParser.getLongValue();
              }
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=jacksonParser.getLongValue();
            break;
            case "valueShort":
              // field valueShort
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueShort=jacksonParser.getShortValue();
              }
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=jacksonParser.getShortValue();
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            case "valueTime":
              // field valueTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTime= TimeUtils.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone= TimeZoneUtils.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl= UrlUtils.read(jacksonParser.getText());
              }
            break;
            case "id":
              // field id
              instance.setId(jacksonParser.getLongValue());
            break;
            case "valueContentBoolType":
              // field valueContentBoolType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueContentBoolType=jacksonParser.getIntValue();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70All parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean70All instance = createInstance();
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
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                instance.valueBean=context.mapperFor(Bean70All.class).parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
              }
            break;
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
              }
            break;
            case "valueBool":
              // field valueBool
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBool=PrimitiveUtils.readBoolean(jacksonParser.getText(), null);
              }
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
            break;
            case "valueByte":
              // field valueByte
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByte=PrimitiveUtils.readByte(jacksonParser.getText(), null);
              }
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=PrimitiveUtils.readByte(jacksonParser.getText(), (byte)0);
            break;
            case "valueCalendar":
              // field valueCalendar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCalendar=CalendarUtils.read(jacksonParser.getText());
              }
            break;
            case "valueChar":
              // field valueChar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueChar=PrimitiveUtils.readCharacter(jacksonParser.getText(), null);
              }
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=PrimitiveUtils.readCharacter(jacksonParser.getText(), ' ');
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency=CurrencyUtils.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate=DateUtils.read(jacksonParser.getText());
              }
            break;
            case "valueDouble":
              // field valueDouble
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDouble=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
              }
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=PrimitiveUtils.readDouble(jacksonParser.getText(), 0.0);
            break;
            case "valueEnum":
              // field valueEnum
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.valueEnum=StringUtils.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
              }
            break;
            case "valueFloat":
              // field valueFloat
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueFloat=PrimitiveUtils.readFloat(jacksonParser.getText(), null);
              }
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
            break;
            case "valueInt":
              // field valueInt
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInt=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
              }
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
            break;
            case "valueLocale":
              // field valueLocale
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLocale=LocaleUtils.read(jacksonParser.getText());
              }
            break;
            case "valueLong":
              // field valueLong
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLong=PrimitiveUtils.readLong(jacksonParser.getText(), null);
              }
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueShort":
              // field valueShort
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueShort=PrimitiveUtils.readShort(jacksonParser.getText(), null);
              }
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=PrimitiveUtils.readShort(jacksonParser.getText(), (short)0);
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            case "valueTime":
              // field valueTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTime=TimeUtils.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone=TimeZoneUtils.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl=UrlUtils.read(jacksonParser.getText());
              }
            break;
            case "id":
              // field id
              instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "valueContentBoolType":
              // field valueContentBoolType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueContentBoolType=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70All parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean70All instance = createInstance();
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
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "valueBean":
                    // property valueBean
                    instance.valueBean=context.mapperFor(Bean70All.class).parseOnXml(context, wrapper, eventType);
                  break;
                  case "valueBigDecimal":
                    // property valueBigDecimal
                    instance.valueBigDecimal=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueBigInteger":
                    // property valueBigInteger
                    instance.valueBigInteger=BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueBool":
                    // property valueBool
                    instance.valueBool=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), null);
                  break;
                  case "valueBoolType":
                    // property valueBoolType
                    instance.valueBoolType=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                  break;
                  case "valueByte":
                    // property valueByte
                    instance.valueByte=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                  break;
                  case "valueByteType":
                    // property valueByteType
                    instance.valueByteType=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), (byte)0);
                  break;
                  case "valueCalendar":
                    // property valueCalendar
                    instance.valueCalendar=CalendarUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueChar":
                    // property valueChar
                    instance.valueChar=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                  break;
                  case "valueCharType":
                    // property valueCharType
                    instance.valueCharType=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                  break;
                  case "valueCurrency":
                    // property valueCurrency
                    instance.valueCurrency=CurrencyUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueDate":
                    // property valueDate
                    instance.valueDate=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueDouble":
                    // property valueDouble
                    instance.valueDouble=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                  break;
                  case "valueDoubleType":
                    // property valueDoubleType
                    instance.valueDoubleType=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), 0.0);
                  break;
                  case "valueEnum":
                    // property valueEnum
                    instance.valueEnum=BeanEnum.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueFloat":
                    // property valueFloat
                    instance.valueFloat=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                  break;
                  case "valueFloatType":
                    // property valueFloatType
                    instance.valueFloatType=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
                  break;
                  case "valueInt":
                    // property valueInt
                    instance.valueInt=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                  break;
                  case "valueIntType":
                    // property valueIntType
                    instance.valueIntType=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                  break;
                  case "valueLocale":
                    // property valueLocale
                    instance.valueLocale=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueLong":
                    // property valueLong
                    instance.valueLong=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                  break;
                  case "valueLongType":
                    // property valueLongType
                    instance.valueLongType=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                  break;
                  case "valueShort":
                    // property valueShort
                    instance.valueShort=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                  break;
                  case "valueShortType":
                    // property valueShortType
                    instance.valueShortType=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), (short)0);
                  break;
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueTime":
                    // property valueTime
                    instance.valueTime=TimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueTimeZone":
                    // property valueTimeZone
                    instance.valueTimeZone=TimeZoneUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "valueUrl":
                    // property valueUrl
                    instance.valueUrl=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "name":
                    // property id
                    instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                if (elementName.equals(xmlParser.getName().getLocalPart())) {
                  currentTag = elementName;
                  elementName = null;
                }
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
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
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
