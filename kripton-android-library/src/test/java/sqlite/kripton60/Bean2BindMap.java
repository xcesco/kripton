package sqlite.kripton60;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is binder map for Bean2
 *
 * @see Bean2
 */
@BindMap(Bean2.class)
public class Bean2BindMap extends AbstractMapper<Bean2> {
  /**
   * Bean2BindMap */
  private Bean2BindMap bean2BindMap = this;

  @Override
  public int serializeOnJackson(Bean2 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field valueBean (mapped with "valueBean")
    if (object.getValueBean()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      bean2BindMap.serializeOnJackson(object.getValueBean(), jacksonSerializer);
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.getValueBeanArray()!=null)  {
      fieldCount++;
      int n=object.getValueBeanArray().length;
      Bean2 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueBeanArray()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean2BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.getValueBigDecimal()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.getValueBigDecimal()));
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.getValueBigInteger()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.getValueBigInteger()));
    }

    // field valueBool (mapped with "valueBool")
    if (object.getValueBool()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBooleanField("valueBool", object.getValueBool());
    }

    // field valueBoolType (mapped with "valueBoolType")
    fieldCount++;
    jacksonSerializer.writeBooleanField("valueBoolType", object.isValueBoolType());

    // field valueByte (mapped with "valueByte")
    if (object.getValueByte()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueByte", object.getValueByte());
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.getValueByteArray()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.getValueByteArray());
    }

    // field valueByteType (mapped with "valueByteType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueByteType", object.getValueByteType());

    // field valueCalendar (mapped with "valueCalendar")
    if (object.getValueCalendar()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.getValueCalendar()));
    }

    // field valueChar (mapped with "valueChar")
    if (object.getValueChar()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueChar", object.getValueChar());
    }

    // field valueCharArray (mapped with "valueCharArray")
    if (object.getValueCharArray()!=null)  {
      fieldCount++;
      int n=object.getValueCharArray().length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueCharArray()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCharList (mapped with "valueCharList")
    if (object.getValueCharList()!=null)  {
      fieldCount++;
      int n=object.getValueCharList().size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueCharList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCharType (mapped with "valueCharType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueCharType", object.getValueCharType());

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.getValueCharTypeArray()!=null)  {
      fieldCount++;
      int n=object.getValueCharTypeArray().length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharTypeArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueCharTypeArray()[i];
        jacksonSerializer.writeNumber(item);
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCurrency (mapped with "valueCurrency")
    if (object.getValueCurrency()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.getValueCurrency()));
    }

    // field valueDate (mapped with "valueDate")
    if (object.getValueDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.getValueDate()));
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.getValueDouble()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueDouble", object.getValueDouble());
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueDoubleType", object.getValueDoubleType());

    // field valueEnumType (mapped with "valueEnumType")
    if (object.getValueEnumType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnumType", object.getValueEnumType().toString());
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.getValueFloat()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueFloat", object.getValueFloat());
    }

    // field valueFloatType (mapped with "valueFloatType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueFloatType", object.getValueFloatType());

    // field valueInt (mapped with "valueInt")
    if (object.getValueInt()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueInt", object.getValueInt());
    }

    // field valueIntType (mapped with "valueIntType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueIntType", object.getValueIntType());

    // field valueLocale (mapped with "valueLocale")
    if (object.getValueLocale()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.getValueLocale()));
    }

    // field valueLong (mapped with "valueLong")
    if (object.getValueLong()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueLong", object.getValueLong());
    }

    // field valueLongArray (mapped with "valueLongArray")
    if (object.getValueLongArray()!=null)  {
      fieldCount++;
      int n=object.getValueLongArray().length;
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueLongArray()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueLongList (mapped with "valueLongList")
    if (object.getValueLongList()!=null)  {
      fieldCount++;
      int n=object.getValueLongList().size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueLongList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueLongType (mapped with "valueLongType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueLongType", object.getValueLongType());

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.getValueLongTypeArray()!=null)  {
      fieldCount++;
      int n=object.getValueLongTypeArray().length;
      long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongTypeArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueLongTypeArray()[i];
        jacksonSerializer.writeNumber(item);
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueShort (mapped with "valueShort")
    if (object.getValueShort()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueShort", object.getValueShort());
    }

    // field valueShortType (mapped with "valueShortType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueShortType", object.getValueShortType());

    // field valueStrinList (mapped with "valueStrinList")
    if (object.getValueStrinList()!=null)  {
      fieldCount++;
      int n=object.getValueStrinList().size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStrinList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueStrinList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueString (mapped with "valueString")
    if (object.getValueString()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.getValueString());
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.getValueStringArray()!=null)  {
      fieldCount++;
      int n=object.getValueStringArray().length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueStringArray()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueTime (mapped with "valueTime")
    if (object.getValueTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.getValueTime()));
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.getValueTimeList()!=null)  {
      fieldCount++;
      int n=object.getValueTimeList().size();
      Time item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueTimeList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueTimeList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(SQLTimeUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.getValueTimeZone()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.getValueTimeZone()));
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.getValueUrl()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.getValueUrl()));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean2 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field valueBean (mapped with "valueBean")
    if (object.getValueBean()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      if (bean2BindMap.serializeOnJacksonAsString(object.getValueBean(), jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("valueBean");
      }
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.getValueBeanArray()!=null)  {
      fieldCount++;
      int n=object.getValueBeanArray().length;
      Bean2 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueBeanArray()[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean2BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("valueBeanArray");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.getValueBigDecimal()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.getValueBigDecimal()));
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.getValueBigInteger()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.getValueBigInteger()));
    }

    // field valueBool (mapped with "valueBool")
    if (object.getValueBool()!=null)  {
      jacksonSerializer.writeStringField("valueBool", PrimitiveUtils.writeBoolean(object.getValueBool()));
    }

    // field valueBoolType (mapped with "valueBoolType")
    jacksonSerializer.writeStringField("valueBoolType", PrimitiveUtils.writeBoolean(object.isValueBoolType()));

    // field valueByte (mapped with "valueByte")
    if (object.getValueByte()!=null)  {
      jacksonSerializer.writeStringField("valueByte", PrimitiveUtils.writeByte(object.getValueByte()));
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.getValueByteArray()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.getValueByteArray());
    }

    // field valueByteType (mapped with "valueByteType")
    jacksonSerializer.writeStringField("valueByteType", PrimitiveUtils.writeByte(object.getValueByteType()));

    // field valueCalendar (mapped with "valueCalendar")
    if (object.getValueCalendar()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCalendar", CalendarUtils.write(object.getValueCalendar()));
    }

    // field valueChar (mapped with "valueChar")
    if (object.getValueChar()!=null)  {
      jacksonSerializer.writeStringField("valueChar", PrimitiveUtils.writeCharacter(object.getValueChar()));
    }

    // field valueCharArray (mapped with "valueCharArray")
    if (object.getValueCharArray()!=null)  {
      fieldCount++;
      int n=object.getValueCharArray().length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueCharArray()[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueCharList (mapped with "valueCharList")
    if (object.getValueCharList()!=null)  {
      fieldCount++;
      int n=object.getValueCharList().size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueCharList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueCharType (mapped with "valueCharType")
    jacksonSerializer.writeStringField("valueCharType", PrimitiveUtils.writeCharacter(object.getValueCharType()));

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.getValueCharTypeArray()!=null)  {
      fieldCount++;
      int n=object.getValueCharTypeArray().length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharTypeArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueCharTypeArray()[i];
          jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueCurrency (mapped with "valueCurrency")
    if (object.getValueCurrency()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.getValueCurrency()));
    }

    // field valueDate (mapped with "valueDate")
    if (object.getValueDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueDate", DateUtils.write(object.getValueDate()));
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.getValueDouble()!=null)  {
      jacksonSerializer.writeStringField("valueDouble", PrimitiveUtils.writeDouble(object.getValueDouble()));
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    jacksonSerializer.writeStringField("valueDoubleType", PrimitiveUtils.writeDouble(object.getValueDoubleType()));

    // field valueEnumType (mapped with "valueEnumType")
    if (object.getValueEnumType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnumType", object.getValueEnumType().toString());
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.getValueFloat()!=null)  {
      jacksonSerializer.writeStringField("valueFloat", PrimitiveUtils.writeFloat(object.getValueFloat()));
    }

    // field valueFloatType (mapped with "valueFloatType")
    jacksonSerializer.writeStringField("valueFloatType", PrimitiveUtils.writeFloat(object.getValueFloatType()));

    // field valueInt (mapped with "valueInt")
    if (object.getValueInt()!=null)  {
      jacksonSerializer.writeStringField("valueInt", PrimitiveUtils.writeInteger(object.getValueInt()));
    }

    // field valueIntType (mapped with "valueIntType")
    jacksonSerializer.writeStringField("valueIntType", PrimitiveUtils.writeInteger(object.getValueIntType()));

    // field valueLocale (mapped with "valueLocale")
    if (object.getValueLocale()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.getValueLocale()));
    }

    // field valueLong (mapped with "valueLong")
    if (object.getValueLong()!=null)  {
      jacksonSerializer.writeStringField("valueLong", PrimitiveUtils.writeLong(object.getValueLong()));
    }

    // field valueLongArray (mapped with "valueLongArray")
    if (object.getValueLongArray()!=null)  {
      fieldCount++;
      int n=object.getValueLongArray().length;
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueLongArray()[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueLongList (mapped with "valueLongList")
    if (object.getValueLongList()!=null)  {
      fieldCount++;
      int n=object.getValueLongList().size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueLongList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueLongType (mapped with "valueLongType")
    jacksonSerializer.writeStringField("valueLongType", PrimitiveUtils.writeLong(object.getValueLongType()));

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.getValueLongTypeArray()!=null)  {
      fieldCount++;
      int n=object.getValueLongTypeArray().length;
      long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongTypeArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueLongTypeArray()[i];
          jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueShort (mapped with "valueShort")
    if (object.getValueShort()!=null)  {
      jacksonSerializer.writeStringField("valueShort", PrimitiveUtils.writeShort(object.getValueShort()));
    }

    // field valueShortType (mapped with "valueShortType")
    jacksonSerializer.writeStringField("valueShortType", PrimitiveUtils.writeShort(object.getValueShortType()));

    // field valueStrinList (mapped with "valueStrinList")
    if (object.getValueStrinList()!=null)  {
      fieldCount++;
      int n=object.getValueStrinList().size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStrinList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueStrinList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueString (mapped with "valueString")
    if (object.getValueString()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.getValueString());
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.getValueStringArray()!=null)  {
      fieldCount++;
      int n=object.getValueStringArray().length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueStringArray()[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueTime (mapped with "valueTime")
    if (object.getValueTime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.getValueTime()));
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.getValueTimeList()!=null)  {
      fieldCount++;
      int n=object.getValueTimeList().size();
      Time item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueTimeList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueTimeList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(SQLTimeUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.getValueTimeZone()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.getValueTimeZone()));
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.getValueUrl()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.getValueUrl()));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean2 object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean2");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field valueBean (mapped with "valueBean")
    if (object.getValueBean()!=null)  {
      xmlSerializer.writeStartElement("valueBean");
      bean2BindMap.serializeOnXml(object.getValueBean(), xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.getValueBeanArray()!=null)  {
      int n=object.getValueBeanArray().length;
      Bean2 item;
      for (int i=0; i<n; i++) {
        item=object.getValueBeanArray()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueBeanArray");
        } else {
          xmlSerializer.writeStartElement("valueBeanArray");
          bean2BindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueBeanArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueBigDecimal (mapped with "valueBigDecimal")
    if (object.getValueBigDecimal()!=null)  {
      xmlSerializer.writeStartElement("valueBigDecimal");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(object.getValueBigDecimal())));
      xmlSerializer.writeEndElement();
    }

    // field valueBigInteger (mapped with "valueBigInteger")
    if (object.getValueBigInteger()!=null)  {
      xmlSerializer.writeStartElement("valueBigInteger");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtils.write(object.getValueBigInteger())));
      xmlSerializer.writeEndElement();
    }

    // field valueBool (mapped with "valueBool")
    if (object.getValueBool()!=null)  {
      xmlSerializer.writeStartElement("valueBool");
      xmlSerializer.writeBoolean(object.getValueBool());
      xmlSerializer.writeEndElement();
    }

    // field valueBoolType (mapped with "valueBoolType")
    xmlSerializer.writeStartElement("valueBoolType");
    xmlSerializer.writeBoolean(object.isValueBoolType());
    xmlSerializer.writeEndElement();

    // field valueByte (mapped with "valueByte")
    if (object.getValueByte()!=null)  {
      xmlSerializer.writeStartElement("valueByte");
      xmlSerializer.writeInt(object.getValueByte());
      xmlSerializer.writeEndElement();
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.getValueByteArray()!=null) {
      xmlSerializer.writeStartElement("valueByteArray");
      xmlSerializer.writeBinary(object.getValueByteArray());
      xmlSerializer.writeEndElement();
    }

    // field valueByteType (mapped with "valueByteType")
    xmlSerializer.writeStartElement("valueByteType");
    xmlSerializer.writeInt(object.getValueByteType());
    xmlSerializer.writeEndElement();

    // field valueCalendar (mapped with "valueCalendar")
    if (object.getValueCalendar()!=null)  {
      xmlSerializer.writeStartElement("valueCalendar");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CalendarUtils.write(object.getValueCalendar())));
      xmlSerializer.writeEndElement();
    }

    // field valueChar (mapped with "valueChar")
    if (object.getValueChar()!=null)  {
      xmlSerializer.writeStartElement("valueChar");
      xmlSerializer.writeInt(object.getValueChar());
      xmlSerializer.writeEndElement();
    }

    // field valueCharArray (mapped with "valueCharArray")
    if (object.getValueCharArray()!=null)  {
      int n=object.getValueCharArray().length;
      Character item;
      for (int i=0; i<n; i++) {
        item=object.getValueCharArray()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueCharArray");
        } else {
          xmlSerializer.writeStartElement("valueCharArray");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueCharArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueCharList (mapped with "valueCharList")
    if (object.getValueCharList()!=null)  {
      int n=object.getValueCharList().size();
      Character item;
      for (int i=0; i<n; i++) {
        item=object.getValueCharList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueCharList");
        } else {
          xmlSerializer.writeStartElement("valueCharList");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueCharList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueCharType (mapped with "valueCharType")
    xmlSerializer.writeStartElement("valueCharType");
    xmlSerializer.writeInt(object.getValueCharType());
    xmlSerializer.writeEndElement();

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.getValueCharTypeArray()!=null)  {
      int n=object.getValueCharTypeArray().length;
      char item;
      for (int i=0; i<n; i++) {
        item=object.getValueCharTypeArray()[i];
        xmlSerializer.writeStartElement("valueCharTypeArray");
        xmlSerializer.writeInt(item);
        xmlSerializer.writeEndElement();
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueCharTypeArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueCurrency (mapped with "valueCurrency")
    if (object.getValueCurrency()!=null)  {
      xmlSerializer.writeStartElement("valueCurrency");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CurrencyUtils.write(object.getValueCurrency())));
      xmlSerializer.writeEndElement();
    }

    // field valueDate (mapped with "valueDate")
    if (object.getValueDate()!=null)  {
      xmlSerializer.writeStartElement("valueDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.getValueDate())));
      xmlSerializer.writeEndElement();
    }

    // field valueDouble (mapped with "valueDouble")
    if (object.getValueDouble()!=null)  {
      xmlSerializer.writeStartElement("valueDouble");
      xmlSerializer.writeDouble(object.getValueDouble());
      xmlSerializer.writeEndElement();
    }

    // field valueDoubleType (mapped with "valueDoubleType")
    xmlSerializer.writeStartElement("valueDoubleType");
    xmlSerializer.writeDouble(object.getValueDoubleType());
    xmlSerializer.writeEndElement();

    // field valueEnumType (mapped with "valueEnumType")
    if (object.getValueEnumType()!=null)  {
      xmlSerializer.writeStartElement("valueEnumType");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getValueEnumType().toString()));
      xmlSerializer.writeEndElement();
    }

    // field valueFloat (mapped with "valueFloat")
    if (object.getValueFloat()!=null)  {
      xmlSerializer.writeStartElement("valueFloat");
      xmlSerializer.writeFloat(object.getValueFloat());
      xmlSerializer.writeEndElement();
    }

    // field valueFloatType (mapped with "valueFloatType")
    xmlSerializer.writeStartElement("valueFloatType");
    xmlSerializer.writeFloat(object.getValueFloatType());
    xmlSerializer.writeEndElement();

    // field valueInt (mapped with "valueInt")
    if (object.getValueInt()!=null)  {
      xmlSerializer.writeStartElement("valueInt");
      xmlSerializer.writeInt(object.getValueInt());
      xmlSerializer.writeEndElement();
    }

    // field valueIntType (mapped with "valueIntType")
    xmlSerializer.writeStartElement("valueIntType");
    xmlSerializer.writeInt(object.getValueIntType());
    xmlSerializer.writeEndElement();

    // field valueLocale (mapped with "valueLocale")
    if (object.getValueLocale()!=null)  {
      xmlSerializer.writeStartElement("valueLocale");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(object.getValueLocale())));
      xmlSerializer.writeEndElement();
    }

    // field valueLong (mapped with "valueLong")
    if (object.getValueLong()!=null)  {
      xmlSerializer.writeStartElement("valueLong");
      xmlSerializer.writeLong(object.getValueLong());
      xmlSerializer.writeEndElement();
    }

    // field valueLongArray (mapped with "valueLongArray")
    if (object.getValueLongArray()!=null)  {
      int n=object.getValueLongArray().length;
      Long item;
      for (int i=0; i<n; i++) {
        item=object.getValueLongArray()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueLongArray");
        } else {
          xmlSerializer.writeStartElement("valueLongArray");
          xmlSerializer.writeLong(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueLongArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueLongList (mapped with "valueLongList")
    if (object.getValueLongList()!=null)  {
      int n=object.getValueLongList().size();
      Long item;
      for (int i=0; i<n; i++) {
        item=object.getValueLongList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueLongList");
        } else {
          xmlSerializer.writeStartElement("valueLongList");
          xmlSerializer.writeLong(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueLongList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueLongType (mapped with "valueLongType")
    xmlSerializer.writeStartElement("valueLongType");
    xmlSerializer.writeLong(object.getValueLongType());
    xmlSerializer.writeEndElement();

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.getValueLongTypeArray()!=null)  {
      int n=object.getValueLongTypeArray().length;
      long item;
      for (int i=0; i<n; i++) {
        item=object.getValueLongTypeArray()[i];
        xmlSerializer.writeStartElement("valueLongTypeArray");
        xmlSerializer.writeLong(item);
        xmlSerializer.writeEndElement();
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueLongTypeArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueShort (mapped with "valueShort")
    if (object.getValueShort()!=null)  {
      xmlSerializer.writeStartElement("valueShort");
      xmlSerializer.writeInt(object.getValueShort());
      xmlSerializer.writeEndElement();
    }

    // field valueShortType (mapped with "valueShortType")
    xmlSerializer.writeStartElement("valueShortType");
    xmlSerializer.writeInt(object.getValueShortType());
    xmlSerializer.writeEndElement();

    // field valueStrinList (mapped with "valueStrinList")
    if (object.getValueStrinList()!=null)  {
      int n=object.getValueStrinList().size();
      String item;
      for (int i=0; i<n; i++) {
        item=object.getValueStrinList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueStrinList");
        } else {
          xmlSerializer.writeStartElement("valueStrinList");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueStrinList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueString (mapped with "valueString")
    if (object.getValueString()!=null) {
      xmlSerializer.writeStartElement("valueString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getValueString()));
      xmlSerializer.writeEndElement();
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.getValueStringArray()!=null)  {
      int n=object.getValueStringArray().length;
      String item;
      for (int i=0; i<n; i++) {
        item=object.getValueStringArray()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueStringArray");
        } else {
          xmlSerializer.writeStartElement("valueStringArray");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueStringArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueTime (mapped with "valueTime")
    if (object.getValueTime()!=null)  {
      xmlSerializer.writeStartElement("valueTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLTimeUtils.write(object.getValueTime())));
      xmlSerializer.writeEndElement();
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.getValueTimeList()!=null)  {
      int n=object.getValueTimeList().size();
      Time item;
      for (int i=0; i<n; i++) {
        item=object.getValueTimeList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueTimeList");
        } else {
          xmlSerializer.writeStartElement("valueTimeList");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLTimeUtils.write(item)));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueTimeList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueTimeZone (mapped with "valueTimeZone")
    if (object.getValueTimeZone()!=null)  {
      xmlSerializer.writeStartElement("valueTimeZone");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeZoneUtils.write(object.getValueTimeZone())));
      xmlSerializer.writeEndElement();
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.getValueUrl()!=null)  {
      xmlSerializer.writeStartElement("valueUrl");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.getValueUrl())));
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
  public Bean2 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean2 instance = new Bean2();
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
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.setValueBean(bean2BindMap.parseOnJackson(jacksonParser));
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean2> collection=new ArrayList<>();
              Bean2 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean2BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setValueBeanArray(CollectionUtils.asArray(collection, new Bean2[collection.size()]));
            }
          break;
          case "valueBigDecimal":
            // field valueBigDecimal (mapped with "valueBigDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBigDecimal(BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueBigInteger":
            // field valueBigInteger (mapped with "valueBigInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBigInteger(BigIntegerUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueBool":
            // field valueBool (mapped with "valueBool")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBool(jacksonParser.getBooleanValue());
            }
          break;
          case "valueBoolType":
            // field valueBoolType (mapped with "valueBoolType")
            instance.setValueBoolType(jacksonParser.getBooleanValue());
          break;
          case "valueByte":
            // field valueByte (mapped with "valueByte")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueByte(jacksonParser.getByteValue());
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueByteArray(jacksonParser.getBinaryValue());
            }
          break;
          case "valueByteType":
            // field valueByteType (mapped with "valueByteType")
            instance.setValueByteType(jacksonParser.getByteValue());
          break;
          case "valueCalendar":
            // field valueCalendar (mapped with "valueCalendar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueCalendar(CalendarUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueChar":
            // field valueChar (mapped with "valueChar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueChar(Character.valueOf((char)jacksonParser.getIntValue()));
            }
          break;
          case "valueCharArray":
            // field valueCharArray (mapped with "valueCharArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Character> collection=new ArrayList<>();
              Character item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=Character.valueOf((char)jacksonParser.getIntValue());
                }
                collection.add(item);
              }
              instance.setValueCharArray(CollectionUtils.asCharacterArray(collection));
            }
          break;
          case "valueCharList":
            // field valueCharList (mapped with "valueCharList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Character> collection=new LinkedList<>();
              Character item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=Character.valueOf((char)jacksonParser.getIntValue());
                }
                collection.add(item);
              }
              instance.setValueCharList(collection);
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.setValueCharType(Character.valueOf((char)jacksonParser.getIntValue()));
          break;
          case "valueCharTypeArray":
            // field valueCharTypeArray (mapped with "valueCharTypeArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Character> collection=new ArrayList<>();
              Character item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=Character.valueOf((char)jacksonParser.getIntValue());
                }
                collection.add(item);
              }
              instance.setValueCharTypeArray(CollectionUtils.asCharacterTypeArray(collection));
            }
          break;
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueCurrency(CurrencyUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueDate(DateUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueDouble":
            // field valueDouble (mapped with "valueDouble")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueDouble(jacksonParser.getDoubleValue());
            }
          break;
          case "valueDoubleType":
            // field valueDoubleType (mapped with "valueDoubleType")
            instance.setValueDoubleType(jacksonParser.getDoubleValue());
          break;
          case "valueEnumType":
            // field valueEnumType (mapped with "valueEnumType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setValueEnumType(StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null);
            }
          break;
          case "valueFloat":
            // field valueFloat (mapped with "valueFloat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueFloat(jacksonParser.getFloatValue());
            }
          break;
          case "valueFloatType":
            // field valueFloatType (mapped with "valueFloatType")
            instance.setValueFloatType(jacksonParser.getFloatValue());
          break;
          case "valueInt":
            // field valueInt (mapped with "valueInt")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueInt(jacksonParser.getIntValue());
            }
          break;
          case "valueIntType":
            // field valueIntType (mapped with "valueIntType")
            instance.setValueIntType(jacksonParser.getIntValue());
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueLocale(LocaleUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueLong":
            // field valueLong (mapped with "valueLong")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueLong(jacksonParser.getLongValue());
            }
          break;
          case "valueLongArray":
            // field valueLongArray (mapped with "valueLongArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getLongValue();
                }
                collection.add(item);
              }
              instance.setValueLongArray(CollectionUtils.asLongArray(collection));
            }
          break;
          case "valueLongList":
            // field valueLongList (mapped with "valueLongList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Long> collection=new LinkedList<>();
              Long item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getLongValue();
                }
                collection.add(item);
              }
              instance.setValueLongList(collection);
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.setValueLongType(jacksonParser.getLongValue());
          break;
          case "valueLongTypeArray":
            // field valueLongTypeArray (mapped with "valueLongTypeArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getLongValue();
                }
                collection.add(item);
              }
              instance.setValueLongTypeArray(CollectionUtils.asLongTypeArray(collection));
            }
          break;
          case "valueShort":
            // field valueShort (mapped with "valueShort")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueShort(jacksonParser.getShortValue());
            }
          break;
          case "valueShortType":
            // field valueShortType (mapped with "valueShortType")
            instance.setValueShortType(jacksonParser.getShortValue());
          break;
          case "valueStrinList":
            // field valueStrinList (mapped with "valueStrinList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<String> collection=new LinkedList<>();
              String item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getText();
                }
                collection.add(item);
              }
              instance.setValueStrinList(collection);
            }
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueString(jacksonParser.getText());
            }
          break;
          case "valueStringArray":
            // field valueStringArray (mapped with "valueStringArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<String> collection=new ArrayList<>();
              String item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getText();
                }
                collection.add(item);
              }
              instance.setValueStringArray(CollectionUtils.asArray(collection, new String[collection.size()]));
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueTime(SQLTimeUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueTimeList":
            // field valueTimeList (mapped with "valueTimeList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Time> collection=new ArrayList<>();
              Time item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=SQLTimeUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.setValueTimeList(collection);
            }
          break;
          case "valueTimeZone":
            // field valueTimeZone (mapped with "valueTimeZone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueTimeZone(TimeZoneUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueUrl":
            // field valueUrl (mapped with "valueUrl")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueUrl(UrlUtils.read(jacksonParser.getText()));
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
  public Bean2 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean2 instance = new Bean2();
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
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.setValueBean(bean2BindMap.parseOnJacksonAsString(jacksonParser));
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean2> collection=new ArrayList<>();
              Bean2 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean2BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.setValueBeanArray(CollectionUtils.asArray(collection, new Bean2[collection.size()]));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean2> collection=new ArrayList<>();
              instance.setValueBeanArray(CollectionUtils.asArray(collection, new Bean2[collection.size()]));
            }
          break;
          case "valueBigDecimal":
            // field valueBigDecimal (mapped with "valueBigDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBigDecimal(BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueBigInteger":
            // field valueBigInteger (mapped with "valueBigInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBigInteger(BigIntegerUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueBool":
            // field valueBool (mapped with "valueBool")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueBool(PrimitiveUtils.readBoolean(jacksonParser.getText(), null));
            }
          break;
          case "valueBoolType":
            // field valueBoolType (mapped with "valueBoolType")
            instance.setValueBoolType(PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false));
          break;
          case "valueByte":
            // field valueByte (mapped with "valueByte")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueByte(PrimitiveUtils.readByte(jacksonParser.getText(), null));
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueByteArray(Base64Utils.decode(jacksonParser.getValueAsString()));
            }
          break;
          case "valueByteType":
            // field valueByteType (mapped with "valueByteType")
            instance.setValueByteType(PrimitiveUtils.readByte(jacksonParser.getText(), (byte)0));
          break;
          case "valueCalendar":
            // field valueCalendar (mapped with "valueCalendar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueCalendar(CalendarUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueChar":
            // field valueChar (mapped with "valueChar")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueChar(PrimitiveUtils.readCharacter(jacksonParser.getText(), null));
            }
          break;
          case "valueCharArray":
            // field valueCharArray (mapped with "valueCharArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Character> collection=new ArrayList<>();
              Character item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readCharacter(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.setValueCharArray(CollectionUtils.asCharacterArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.setValueCharArray(CollectionUtils.asCharacterArray(collection));
            }
          break;
          case "valueCharList":
            // field valueCharList (mapped with "valueCharList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Character> collection=new LinkedList<>();
              Character item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readCharacter(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.setValueCharList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Character> collection=new LinkedList<>();
              instance.setValueCharList(collection);
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.setValueCharType(PrimitiveUtils.readCharacter(jacksonParser.getText(), ' '));
          break;
          case "valueCharTypeArray":
            // field valueCharTypeArray (mapped with "valueCharTypeArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Character> collection=new ArrayList<>();
              Character item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readCharacter(jacksonParser.getText(), ' ');
                }
                collection.add(item);
              }
              instance.setValueCharTypeArray(CollectionUtils.asCharacterTypeArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.setValueCharTypeArray(CollectionUtils.asCharacterTypeArray(collection));
            }
          break;
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueCurrency(CurrencyUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueDate(DateUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueDouble":
            // field valueDouble (mapped with "valueDouble")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueDouble(PrimitiveUtils.readDouble(jacksonParser.getText(), null));
            }
          break;
          case "valueDoubleType":
            // field valueDoubleType (mapped with "valueDoubleType")
            instance.setValueDoubleType(PrimitiveUtils.readDouble(jacksonParser.getText(), 0.0));
          break;
          case "valueEnumType":
            // field valueEnumType (mapped with "valueEnumType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setValueEnumType(StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null);
            }
          break;
          case "valueFloat":
            // field valueFloat (mapped with "valueFloat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueFloat(PrimitiveUtils.readFloat(jacksonParser.getText(), null));
            }
          break;
          case "valueFloatType":
            // field valueFloatType (mapped with "valueFloatType")
            instance.setValueFloatType(PrimitiveUtils.readFloat(jacksonParser.getText(), 0f));
          break;
          case "valueInt":
            // field valueInt (mapped with "valueInt")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueInt(PrimitiveUtils.readInteger(jacksonParser.getText(), null));
            }
          break;
          case "valueIntType":
            // field valueIntType (mapped with "valueIntType")
            instance.setValueIntType(PrimitiveUtils.readInteger(jacksonParser.getText(), 0));
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueLocale(LocaleUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueLong":
            // field valueLong (mapped with "valueLong")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueLong(PrimitiveUtils.readLong(jacksonParser.getText(), null));
            }
          break;
          case "valueLongArray":
            // field valueLongArray (mapped with "valueLongArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readLong(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.setValueLongArray(CollectionUtils.asLongArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              instance.setValueLongArray(CollectionUtils.asLongArray(collection));
            }
          break;
          case "valueLongList":
            // field valueLongList (mapped with "valueLongList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Long> collection=new LinkedList<>();
              Long item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readLong(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.setValueLongList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Long> collection=new LinkedList<>();
              instance.setValueLongList(collection);
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.setValueLongType(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "valueLongTypeArray":
            // field valueLongTypeArray (mapped with "valueLongTypeArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
                }
                collection.add(item);
              }
              instance.setValueLongTypeArray(CollectionUtils.asLongTypeArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              instance.setValueLongTypeArray(CollectionUtils.asLongTypeArray(collection));
            }
          break;
          case "valueShort":
            // field valueShort (mapped with "valueShort")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueShort(PrimitiveUtils.readShort(jacksonParser.getText(), null));
            }
          break;
          case "valueShortType":
            // field valueShortType (mapped with "valueShortType")
            instance.setValueShortType(PrimitiveUtils.readShort(jacksonParser.getText(), (short)0));
          break;
          case "valueStrinList":
            // field valueStrinList (mapped with "valueStrinList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<String> collection=new LinkedList<>();
              String item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    item=jacksonParser.getText();
                  }
                }
                collection.add(item);
              }
              instance.setValueStrinList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<String> collection=new LinkedList<>();
              instance.setValueStrinList(collection);
            }
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueString(jacksonParser.getText());
            }
          break;
          case "valueStringArray":
            // field valueStringArray (mapped with "valueStringArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<String> collection=new ArrayList<>();
              String item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    item=jacksonParser.getText();
                  }
                }
                collection.add(item);
              }
              instance.setValueStringArray(CollectionUtils.asArray(collection, new String[collection.size()]));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              instance.setValueStringArray(CollectionUtils.asArray(collection, new String[collection.size()]));
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueTime(SQLTimeUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueTimeList":
            // field valueTimeList (mapped with "valueTimeList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Time> collection=new ArrayList<>();
              Time item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=SQLTimeUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.setValueTimeList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Time> collection=new ArrayList<>();
              instance.setValueTimeList(collection);
            }
          break;
          case "valueTimeZone":
            // field valueTimeZone (mapped with "valueTimeZone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueTimeZone(TimeZoneUtils.read(jacksonParser.getText()));
            }
          break;
          case "valueUrl":
            // field valueUrl (mapped with "valueUrl")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setValueUrl(UrlUtils.read(jacksonParser.getText()));
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
  public Bean2 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean2 instance = new Bean2();
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
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "valueBean":
                  // property valueBean (mapped on "valueBean")
                  instance.setValueBean(bean2BindMap.parseOnXml(xmlParser, eventType));
                break;
                case "valueBeanArray":
                  // property valueBeanArray (mapped on "valueBeanArray")
                   {
                    ArrayList<Bean2> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueBeanArray());
                    Bean2 item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean2BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueBeanArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean2BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setValueBeanArray(CollectionUtils.asArray(collection, new Bean2[collection.size()]));
                    read=false;
                  }
                break;
                case "valueBigDecimal":
                  // property valueBigDecimal (mapped on "valueBigDecimal")
                  instance.setValueBigDecimal(BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueBigInteger":
                  // property valueBigInteger (mapped on "valueBigInteger")
                  instance.setValueBigInteger(BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueBool":
                  // property valueBool (mapped on "valueBool")
                  instance.setValueBool(PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), null));
                break;
                case "valueBoolType":
                  // property valueBoolType (mapped on "valueBoolType")
                  instance.setValueBoolType(PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false));
                break;
                case "valueByte":
                  // property valueByte (mapped on "valueByte")
                  instance.setValueByte((byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null));
                break;
                case "valueByteArray":
                  // property valueByteArray (mapped on "valueByteArray")
                  instance.setValueByteArray(xmlParser.getElementAsBinary());
                break;
                case "valueByteType":
                  // property valueByteType (mapped on "valueByteType")
                  instance.setValueByteType((byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), (byte)0));
                break;
                case "valueCalendar":
                  // property valueCalendar (mapped on "valueCalendar")
                  instance.setValueCalendar(CalendarUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueChar":
                  // property valueChar (mapped on "valueChar")
                  instance.setValueChar((char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null));
                break;
                case "valueCharArray":
                  // property valueCharArray (mapped on "valueCharArray")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueCharArray());
                    Character item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueCharArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueCharArray(CollectionUtils.asCharacterArray(collection));
                    read=false;
                  }
                break;
                case "valueCharList":
                  // property valueCharList (mapped on "valueCharList")
                   {
                    LinkedList<Character> collection=CollectionUtils.merge(new LinkedList<>(), instance.getValueCharList());
                    Character item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueCharList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueCharList(collection);
                    read=false;
                  }
                break;
                case "valueCharType":
                  // property valueCharType (mapped on "valueCharType")
                  instance.setValueCharType((char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' '));
                break;
                case "valueCharTypeArray":
                  // property valueCharTypeArray (mapped on "valueCharTypeArray")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueCharTypeArray());
                    Character item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueCharTypeArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      }
                      collection.add(item);
                    }
                    instance.setValueCharTypeArray(CollectionUtils.asCharacterTypeArray(collection));
                    read=false;
                  }
                break;
                case "valueCurrency":
                  // property valueCurrency (mapped on "valueCurrency")
                  instance.setValueCurrency(CurrencyUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueDate":
                  // property valueDate (mapped on "valueDate")
                  instance.setValueDate(DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueDouble":
                  // property valueDouble (mapped on "valueDouble")
                  instance.setValueDouble(PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null));
                break;
                case "valueDoubleType":
                  // property valueDoubleType (mapped on "valueDoubleType")
                  instance.setValueDoubleType(PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), 0.0));
                break;
                case "valueEnumType":
                  // property valueEnumType (mapped on "valueEnumType")
                  instance.setValueEnumType(EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueFloat":
                  // property valueFloat (mapped on "valueFloat")
                  instance.setValueFloat(PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null));
                break;
                case "valueFloatType":
                  // property valueFloatType (mapped on "valueFloatType")
                  instance.setValueFloatType(PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f));
                break;
                case "valueInt":
                  // property valueInt (mapped on "valueInt")
                  instance.setValueInt(PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null));
                break;
                case "valueIntType":
                  // property valueIntType (mapped on "valueIntType")
                  instance.setValueIntType(PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0));
                break;
                case "valueLocale":
                  // property valueLocale (mapped on "valueLocale")
                  instance.setValueLocale(LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueLong":
                  // property valueLong (mapped on "valueLong")
                  instance.setValueLong(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                break;
                case "valueLongArray":
                  // property valueLongArray (mapped on "valueLongArray")
                   {
                    ArrayList<Long> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueLongArray());
                    Long item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueLongArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueLongArray(CollectionUtils.asLongArray(collection));
                    read=false;
                  }
                break;
                case "valueLongList":
                  // property valueLongList (mapped on "valueLongList")
                   {
                    LinkedList<Long> collection=CollectionUtils.merge(new LinkedList<>(), instance.getValueLongList());
                    Long item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueLongList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueLongList(collection);
                    read=false;
                  }
                break;
                case "valueLongType":
                  // property valueLongType (mapped on "valueLongType")
                  instance.setValueLongType(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "valueLongTypeArray":
                  // property valueLongTypeArray (mapped on "valueLongTypeArray")
                   {
                    ArrayList<Long> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueLongTypeArray());
                    Long item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueLongTypeArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                      }
                      collection.add(item);
                    }
                    instance.setValueLongTypeArray(CollectionUtils.asLongTypeArray(collection));
                    read=false;
                  }
                break;
                case "valueShort":
                  // property valueShort (mapped on "valueShort")
                  instance.setValueShort((short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null));
                break;
                case "valueShortType":
                  // property valueShortType (mapped on "valueShortType")
                  instance.setValueShortType((short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), (short)0));
                break;
                case "valueStrinList":
                  // property valueStrinList (mapped on "valueStrinList")
                   {
                    LinkedList<String> collection=CollectionUtils.merge(new LinkedList<>(), instance.getValueStrinList());
                    String item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueStrinList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.setValueStrinList(collection);
                    read=false;
                  }
                break;
                case "valueString":
                  // property valueString (mapped on "valueString")
                  instance.setValueString(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueStringArray":
                  // property valueStringArray (mapped on "valueStringArray")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueStringArray());
                    String item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueStringArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.setValueStringArray(CollectionUtils.asArray(collection, new String[collection.size()]));
                    read=false;
                  }
                break;
                case "valueTime":
                  // property valueTime (mapped on "valueTime")
                  instance.setValueTime(SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueTimeList":
                  // property valueTimeList (mapped on "valueTimeList")
                   {
                    ArrayList<Time> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueTimeList());
                    Time item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueTimeList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.setValueTimeList(collection);
                    read=false;
                  }
                break;
                case "valueTimeZone":
                  // property valueTimeZone (mapped on "valueTimeZone")
                  instance.setValueTimeZone(TimeZoneUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "valueUrl":
                  // property valueUrl (mapped on "valueUrl")
                  instance.setValueUrl(UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
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

    public void init() {
      // binding maps initialization 
    }
  }
