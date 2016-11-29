package kripton76;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.BigDecimalUtil;
import com.abubusoft.kripton.common.BigIntegerUtil;
import com.abubusoft.kripton.common.CalendarUtil;
import com.abubusoft.kripton.common.CurrencyUtil;
import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.common.LocaleUtil;
import com.abubusoft.kripton.common.PrimitiveUtil;
import com.abubusoft.kripton.common.TimeUtil;
import com.abubusoft.kripton.common.TimeZoneUtil;
import com.abubusoft.kripton.common.UrlUtil;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement76
 *
 * @see BeanAttribute76
 */
@BindMap
public class BeanElement76BindMap extends AbstractMapper<BeanAttribute76> {
  /**
   * create new object instance
   */
  @Override
  public BeanAttribute76 createInstance() {
    return new BeanAttribute76();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanAttribute76 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field id
      jacksonSerializer.writeNumberField("id", object.getId());

      // field valueBean
      if (object.valueBean!=null)  {
        jacksonSerializer.writeFieldName("valueBean");
        context.mapperFor(BeanAttribute76.class).serializeOnJackson(context, object.valueBean, wrapper);
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtil.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtil.write(object.valueBigInteger));
      }

      // field valueBool
      if (object.valueBool!=null)  {
        jacksonSerializer.writeBooleanField("valueBool", object.valueBool);
      }

      // field valueBoolType
      jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

      // field valueByte
      if (object.valueByte!=null)  {
        jacksonSerializer.writeNumberField("valueByte", object.valueByte);
      }

      // field valueByteType
      jacksonSerializer.writeNumberField("valueByteType", object.valueByteType);

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtil.write(object.valueCalendar));
      }

      // field valueChar
      if (object.valueChar!=null)  {
        jacksonSerializer.writeNumberField("valueChar", object.valueChar);
      }

      // field valueCharType
      jacksonSerializer.writeNumberField("valueCharType", object.valueCharType);

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        jacksonSerializer.writeNumberField("valueContentBoolType", object.valueContentBoolType);
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtil.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        jacksonSerializer.writeStringField("valueDate", DateUtil.write(object.valueDate));
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        jacksonSerializer.writeNumberField("valueDouble", object.valueDouble);
      }

      // field valueDoubleType
      jacksonSerializer.writeNumberField("valueDoubleType", object.valueDoubleType);

      // field valueEnum
      if (object.valueEnum!=null)  {
        jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        jacksonSerializer.writeNumberField("valueFloat", object.valueFloat);
      }

      // field valueFloatType
      jacksonSerializer.writeNumberField("valueFloatType", object.valueFloatType);

      // field valueInt
      if (object.valueInt!=null)  {
        jacksonSerializer.writeNumberField("valueInt", object.valueInt);
      }

      // field valueIntType
      jacksonSerializer.writeNumberField("valueIntType", object.valueIntType);

      // field valueLocale
      if (object.valueLocale!=null)  {
        jacksonSerializer.writeStringField("valueLocale", LocaleUtil.write(object.valueLocale));
      }

      // field valueLong
      if (object.valueLong!=null)  {
        jacksonSerializer.writeNumberField("valueLong", object.valueLong);
      }

      // field valueLongType
      jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

      // field valueShort
      if (object.valueShort!=null)  {
        jacksonSerializer.writeNumberField("valueShort", object.valueShort);
      }

      // field valueShortType
      jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueTime
      if (object.valueTime!=null)  {
        jacksonSerializer.writeStringField("valueTime", TimeUtil.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtil.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        jacksonSerializer.writeStringField("valueUrl", UrlUtil.write(object.valueUrl));
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJacksonAsString(JacksonContext context, BeanAttribute76 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtil.writeLong(object.getId()));

      // field valueBean
      if (object.valueBean!=null)  {
        jacksonSerializer.writeFieldName("valueBean");
        context.mapperFor(BeanAttribute76.class).serializeOnJacksonAsString(context, object.valueBean, wrapper);
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtil.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtil.write(object.valueBigInteger));
      }

      // field valueBool
      if (object.valueBool!=null)  {
        jacksonSerializer.writeStringField("valueBool", PrimitiveUtil.writeBoolean(object.valueBool));
      }

      // field valueBoolType
      jacksonSerializer.writeStringField("valueBoolType", PrimitiveUtil.writeBoolean(object.valueBoolType));

      // field valueByte
      if (object.valueByte!=null)  {
        jacksonSerializer.writeStringField("valueByte", PrimitiveUtil.writeByte(object.valueByte));
      }

      // field valueByteType
      jacksonSerializer.writeStringField("valueByteType", PrimitiveUtil.writeByte(object.valueByteType));

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtil.write(object.valueCalendar));
      }

      // field valueChar
      if (object.valueChar!=null)  {
        jacksonSerializer.writeStringField("valueChar", PrimitiveUtil.writeCharacter(object.valueChar));
      }

      // field valueCharType
      jacksonSerializer.writeStringField("valueCharType", PrimitiveUtil.writeCharacter(object.valueCharType));

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        jacksonSerializer.writeStringField("valueContentBoolType", PrimitiveUtil.writeInteger(object.valueContentBoolType));
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtil.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        jacksonSerializer.writeStringField("valueDate", DateUtil.write(object.valueDate));
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        jacksonSerializer.writeStringField("valueDouble", PrimitiveUtil.writeDouble(object.valueDouble));
      }

      // field valueDoubleType
      jacksonSerializer.writeStringField("valueDoubleType", PrimitiveUtil.writeDouble(object.valueDoubleType));

      // field valueEnum
      if (object.valueEnum!=null)  {
        jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        jacksonSerializer.writeStringField("valueFloat", PrimitiveUtil.writeFloat(object.valueFloat));
      }

      // field valueFloatType
      jacksonSerializer.writeStringField("valueFloatType", PrimitiveUtil.writeFloat(object.valueFloatType));

      // field valueInt
      if (object.valueInt!=null)  {
        jacksonSerializer.writeStringField("valueInt", PrimitiveUtil.writeInteger(object.valueInt));
      }

      // field valueIntType
      jacksonSerializer.writeStringField("valueIntType", PrimitiveUtil.writeInteger(object.valueIntType));

      // field valueLocale
      if (object.valueLocale!=null)  {
        jacksonSerializer.writeStringField("valueLocale", LocaleUtil.write(object.valueLocale));
      }

      // field valueLong
      if (object.valueLong!=null)  {
        jacksonSerializer.writeStringField("valueLong", PrimitiveUtil.writeLong(object.valueLong));
      }

      // field valueLongType
      jacksonSerializer.writeStringField("valueLongType", PrimitiveUtil.writeLong(object.valueLongType));

      // field valueShort
      if (object.valueShort!=null)  {
        jacksonSerializer.writeStringField("valueShort", PrimitiveUtil.writeShort(object.valueShort));
      }

      // field valueShortType
      jacksonSerializer.writeStringField("valueShortType", PrimitiveUtil.writeShort(object.valueShortType));

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueTime
      if (object.valueTime!=null)  {
        jacksonSerializer.writeStringField("valueTime", TimeUtil.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtil.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        jacksonSerializer.writeStringField("valueUrl", UrlUtil.write(object.valueUrl));
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, BeanAttribute76 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeAttribute("id", PrimitiveUtil.writeLong(object.getId()));

      // field valueBean
      if (object.valueBean!=null)  {
        xmlSerializer.writeBinaryAttribute(null, null, "valueBean", context.mapperFor(BeanAttribute76.class).serialize(context, object.valueBean).getBytes());
      }

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        xmlSerializer.writeDecimalAttribute(null, null,"valueBigDecimal", object.valueBigDecimal);
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        xmlSerializer.writeIntegerAttribute(null, null,"valueBigInteger", object.valueBigInteger);
      }

      // field valueBool
      if (object.valueBool!=null)  {
        xmlSerializer.writeAttribute("valueBool", PrimitiveUtil.writeBoolean(object.valueBool));
      }

      // field valueBoolType
      xmlSerializer.writeAttribute("valueBoolType", PrimitiveUtil.writeBoolean(object.valueBoolType));

      // field valueByte
      if (object.valueByte!=null)  {
        xmlSerializer.writeAttribute("valueByte", PrimitiveUtil.writeByte(object.valueByte));
      }

      // field valueByteType
      xmlSerializer.writeAttribute("valueByteType", PrimitiveUtil.writeByte(object.valueByteType));

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        xmlSerializer.writeAttribute("valueCalendar", CalendarUtil.write(object.valueCalendar));
      }

      // field valueChar
      if (object.valueChar!=null)  {
        xmlSerializer.writeAttribute("valueChar", PrimitiveUtil.writeCharacter(object.valueChar));
      }

      // field valueCharType
      xmlSerializer.writeAttribute("valueCharType", PrimitiveUtil.writeCharacter(object.valueCharType));

      // field valueContentBoolType
      if (object.valueContentBoolType!=null)  {
        xmlSerializer.writeAttribute("valueContentBoolType", PrimitiveUtil.writeInteger(object.valueContentBoolType));
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        xmlSerializer.writeAttribute("valueCurrency", CurrencyUtil.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        xmlSerializer.writeAttribute("valueDate", DateUtil.write(object.valueDate));
      }

      // field valueDouble
      if (object.valueDouble!=null)  {
        xmlSerializer.writeAttribute("valueDouble", PrimitiveUtil.writeDouble(object.valueDouble));
      }

      // field valueDoubleType
      xmlSerializer.writeAttribute("valueDoubleType", PrimitiveUtil.writeDouble(object.valueDoubleType));

      // field valueEnum
      if (object.valueEnum!=null)  {
        xmlSerializer.writeAttribute("valueEnum", object.valueEnum.toString());
      }

      // field valueFloat
      if (object.valueFloat!=null)  {
        xmlSerializer.writeAttribute("valueFloat", PrimitiveUtil.writeFloat(object.valueFloat));
      }

      // field valueFloatType
      xmlSerializer.writeAttribute("valueFloatType", PrimitiveUtil.writeFloat(object.valueFloatType));

      // field valueInt
      if (object.valueInt!=null)  {
        xmlSerializer.writeAttribute("valueInt", PrimitiveUtil.writeInteger(object.valueInt));
      }

      // field valueIntType
      xmlSerializer.writeAttribute("valueIntType", PrimitiveUtil.writeInteger(object.valueIntType));

      // field valueLocale
      if (object.valueLocale!=null)  {
        xmlSerializer.writeAttribute("valueLocale", LocaleUtil.write(object.valueLocale));
      }

      // field valueLong
      if (object.valueLong!=null)  {
        xmlSerializer.writeAttribute("valueLong", PrimitiveUtil.writeLong(object.valueLong));
      }

      // field valueLongType
      xmlSerializer.writeAttribute("valueLongType", PrimitiveUtil.writeLong(object.valueLongType));

      // field valueShort
      if (object.valueShort!=null)  {
        xmlSerializer.writeAttribute("valueShort", PrimitiveUtil.writeShort(object.valueShort));
      }

      // field valueShortType
      xmlSerializer.writeAttribute("valueShortType", PrimitiveUtil.writeShort(object.valueShortType));

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeAttribute("valueString", object.valueString);
      }

      // field valueTime
      if (object.valueTime!=null)  {
        xmlSerializer.writeAttribute("valueTime", TimeUtil.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        xmlSerializer.writeAttribute("valueTimeZone", TimeZoneUtil.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        xmlSerializer.writeAttribute("valueUrl", UrlUtil.write(object.valueUrl));
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(XMLStreamException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanAttribute76 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute76 instance = createInstance();
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
            case "id":
              // field id
              instance.setId(jacksonParser.getLongValue());
            break;
            case "valueBean":
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.valueBean=context.mapperFor(BeanAttribute76.class).parseOnJackson(context, wrapper);
              }
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtil.read(jacksonParser.getText());
              }
            break;
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtil.read(jacksonParser.getText());
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
                instance.valueCalendar= CalendarUtil.read(jacksonParser.getText());
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
            case "valueContentBoolType":
              // field valueContentBoolType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueContentBoolType=jacksonParser.getIntValue();
              }
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency= CurrencyUtil.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate= DateUtil.read(jacksonParser.getText());
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
                instance.valueEnum=StringUtility.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
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
                instance.valueLocale= LocaleUtil.read(jacksonParser.getText());
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
                instance.valueTime= TimeUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone= TimeZoneUtil.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl= UrlUtil.read(jacksonParser.getText());
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
  public BeanAttribute76 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute76 instance = createInstance();
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
            case "id":
              // field id
              instance.setId(PrimitiveUtil.readLong(jacksonParser.getText(), 0L));
            break;
            case "valueBean":
              // field valueBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.valueBean=context.mapperFor(BeanAttribute76.class).parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtil.read(jacksonParser.getText());
              }
            break;
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtil.read(jacksonParser.getText());
              }
            break;
            case "valueBool":
              // field valueBool
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBool=PrimitiveUtil.readBoolean(jacksonParser.getText(), null);
              }
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=PrimitiveUtil.readBoolean(jacksonParser.getText(), (boolean)false);
            break;
            case "valueByte":
              // field valueByte
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByte=PrimitiveUtil.readByte(jacksonParser.getText(), null);
              }
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=PrimitiveUtil.readByte(jacksonParser.getText(), (byte)0);
            break;
            case "valueCalendar":
              // field valueCalendar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCalendar=CalendarUtil.read(jacksonParser.getText());
              }
            break;
            case "valueChar":
              // field valueChar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueChar=PrimitiveUtil.readCharacter(jacksonParser.getText(), null);
              }
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=PrimitiveUtil.readCharacter(jacksonParser.getText(), ' ');
            break;
            case "valueContentBoolType":
              // field valueContentBoolType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueContentBoolType=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
              }
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency=CurrencyUtil.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate=DateUtil.read(jacksonParser.getText());
              }
            break;
            case "valueDouble":
              // field valueDouble
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDouble=PrimitiveUtil.readDouble(jacksonParser.getText(), null);
              }
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=PrimitiveUtil.readDouble(jacksonParser.getText(), 0.0);
            break;
            case "valueEnum":
              // field valueEnum
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.valueEnum=StringUtility.hasText(tempEnum)?BeanEnum.valueOf(tempEnum):null;
              }
            break;
            case "valueFloat":
              // field valueFloat
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueFloat=PrimitiveUtil.readFloat(jacksonParser.getText(), null);
              }
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=PrimitiveUtil.readFloat(jacksonParser.getText(), 0f);
            break;
            case "valueInt":
              // field valueInt
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInt=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
              }
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=PrimitiveUtil.readInteger(jacksonParser.getText(), 0);
            break;
            case "valueLocale":
              // field valueLocale
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLocale=LocaleUtil.read(jacksonParser.getText());
              }
            break;
            case "valueLong":
              // field valueLong
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLong=PrimitiveUtil.readLong(jacksonParser.getText(), null);
              }
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=PrimitiveUtil.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueShort":
              // field valueShort
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueShort=PrimitiveUtil.readShort(jacksonParser.getText(), null);
              }
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=PrimitiveUtil.readShort(jacksonParser.getText(), (short)0);
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
                instance.valueTime=TimeUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone=TimeZoneUtil.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl=UrlUtil.read(jacksonParser.getText());
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
  public BeanAttribute76 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanAttribute76 instance = createInstance();
      int eventType = currentEventType;
      boolean read=true;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      String currentTag = xmlParser.getName().toString();
      Stack<String> elementNameStack = new Stack<>();
      elementNameStack.push(currentTag);

      // attributes 
      String attributeName = null;
      int attributesCount = xmlParser.getAttributeCount();;
      for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
        attributeName = xmlParser.getAttributeLocalName(attributeIndex);
        switch(attributeName) {
            case "id":
              // field id
              instance.setId(PrimitiveUtil.readLong(xmlParser.getAttributeValue(attributeIndex), 0L));
            break;
            case "valueBean":
              // field valueBean
              instance.valueBean=context.mapperFor(BeanAttribute76.class).parse(context, xmlParser.getAttributeAsBinary(attributeIndex));
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              instance.valueBigDecimal=xmlParser.getAttributeAsDecimal(attributeIndex);
            break;
            case "valueBigInteger":
              // field valueBigInteger
              instance.valueBigInteger=xmlParser.getAttributeAsInteger(attributeIndex);
            break;
            case "valueBool":
              // field valueBool
              instance.valueBool=PrimitiveUtil.readBoolean(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=PrimitiveUtil.readBoolean(xmlParser.getAttributeValue(attributeIndex), (boolean)false);
            break;
            case "valueByte":
              // field valueByte
              instance.valueByte=(byte)PrimitiveUtil.readByte(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=(byte)PrimitiveUtil.readByte(xmlParser.getAttributeValue(attributeIndex), (byte)0);
            break;
            case "valueCalendar":
              // field valueCalendar
              instance.valueCalendar=CalendarUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueChar":
              // field valueChar
              instance.valueChar=(char)PrimitiveUtil.readCharacter(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=(char)PrimitiveUtil.readCharacter(xmlParser.getAttributeValue(attributeIndex), ' ');
            break;
            case "valueContentBoolType":
              // field valueContentBoolType
              instance.valueContentBoolType=PrimitiveUtil.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueCurrency":
              // field valueCurrency
              instance.valueCurrency=CurrencyUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueDate":
              // field valueDate
              instance.valueDate=DateUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueDouble":
              // field valueDouble
              instance.valueDouble=PrimitiveUtil.readDouble(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=PrimitiveUtil.readDouble(xmlParser.getAttributeValue(attributeIndex), 0.0);
            break;
            case "valueEnum":
              // field valueEnum
              instance.valueEnum=BeanEnum.valueOf(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueFloat":
              // field valueFloat
              instance.valueFloat=PrimitiveUtil.readFloat(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=PrimitiveUtil.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
            break;
            case "valueInt":
              // field valueInt
              instance.valueInt=PrimitiveUtil.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=PrimitiveUtil.readInteger(xmlParser.getAttributeValue(attributeIndex), 0);
            break;
            case "valueLocale":
              // field valueLocale
              instance.valueLocale=LocaleUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueLong":
              // field valueLong
              instance.valueLong=PrimitiveUtil.readLong(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=PrimitiveUtil.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
            break;
            case "valueShort":
              // field valueShort
              instance.valueShort=(short)PrimitiveUtil.readShort(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=(short)PrimitiveUtil.readShort(xmlParser.getAttributeValue(attributeIndex), (short)0);
            break;
            case "valueString":
              // field valueString
              instance.valueString=xmlParser.getAttributeValue(attributeIndex);
            break;
            case "valueTime":
              // field valueTime
              instance.valueTime=TimeUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueTimeZone":
              // field valueTimeZone
              instance.valueTimeZone=TimeZoneUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            case "valueUrl":
              // field valueUrl
              instance.valueUrl=UrlUtil.read(xmlParser.getAttributeValue(attributeIndex));
            break;
            default:
            break;
        }
      }

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        if (read) {
          eventType = xmlParser.next();
        } else {
          eventType = xmlParser.getEventType();
        }
        read=true;
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              // No property to manage here
            break;
            case XMLEvent.END_ELEMENT:
              currentTag = elementNameStack.pop();
            break;
            case XMLEvent.CDATA:
            case XMLEvent.CHARACTERS:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    } catch(XMLStreamException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }
}
