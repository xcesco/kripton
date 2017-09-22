package sqlite.kripton64;

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
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Character;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class is binder map for Bean64
 *
 * @see Bean64
 */
@BindMap(Bean64.class)
public class Bean64BindMap extends AbstractMapper<Bean64> {
  /**
   * Bean64BindMap */
  private Bean64BindMap bean64BindMap = this;

  @Override
  public int serializeOnJackson(Bean64 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      bean64BindMap.serializeOnJackson(object.valueBean, jacksonSerializer);
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      fieldCount++;
      int n=object.valueBeanArray.length;
      Bean64 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBeanArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean64BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
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

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
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

    // field valueCharArray (mapped with "valueCharArray")
    if (object.valueCharArray!=null)  {
      fieldCount++;
      int n=object.valueCharArray.length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueCharArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCharList (mapped with "valueCharList")
    if (object.valueCharList!=null)  {
      fieldCount++;
      int n=object.valueCharList.size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueCharList.get(i);
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
    jacksonSerializer.writeNumberField("valueCharType", object.valueCharType);

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.valueCharTypeArray!=null)  {
      fieldCount++;
      int n=object.valueCharTypeArray.length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharTypeArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueCharTypeArray[i];
        jacksonSerializer.writeNumber(item);
      }
      jacksonSerializer.writeEndArray();
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

    // field valueEnumType (mapped with "valueEnumType")
    if (object.valueEnumType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnumType", object.valueEnumType.toString());
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

    // field valueLinkedMapStringBean (mapped with "valueLinkedMapStringBean")
    if (object.valueLinkedMapStringBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueLinkedMapStringBean.size()>0) {
        jacksonSerializer.writeFieldName("valueLinkedMapStringBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Bean64> item: object.valueLinkedMapStringBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeFieldName("value");
            bean64BindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueLinkedMapStringBean");
      }
    }

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

    // field valueLongArray (mapped with "valueLongArray")
    if (object.valueLongArray!=null)  {
      fieldCount++;
      int n=object.valueLongArray.length;
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueLongArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueLongList (mapped with "valueLongList")
    if (object.valueLongList!=null)  {
      fieldCount++;
      int n=object.valueLongList.size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueLongList.get(i);
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
    jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.valueLongTypeArray!=null)  {
      fieldCount++;
      int n=object.valueLongTypeArray.length;
      long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongTypeArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueLongTypeArray[i];
        jacksonSerializer.writeNumber(item);
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueMapStringBean (mapped with "valueMapStringBean")
    if (object.valueMapStringBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringBean.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Bean64> item: object.valueMapStringBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeFieldName("value");
            bean64BindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapStringBean");
      }
    }

    // field valueSetString (mapped with "valueSetString")
    if (object.valueSetString!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueSetString");
      jacksonSerializer.writeStartArray();
      for (String item: object.valueSetString) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueShort (mapped with "valueShort")
    if (object.valueShort!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueShort", object.valueShort);
    }

    // field valueShortType (mapped with "valueShortType")
    fieldCount++;
    jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

    // field valueStrinList (mapped with "valueStrinList")
    if (object.valueStrinList!=null)  {
      fieldCount++;
      int n=object.valueStrinList.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStrinList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueStrinList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.valueStringArray!=null)  {
      fieldCount++;
      int n=object.valueStringArray.length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueStringArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueTime (mapped with "valueTime")
    if (object.valueTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.valueTime));
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.valueTimeList!=null)  {
      fieldCount++;
      int n=object.valueTimeList.size();
      Time item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueTimeList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueTimeList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(SQLTimeUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean64 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("valueBean");
      if (bean64BindMap.serializeOnJacksonAsString(object.valueBean, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("valueBean");
      }
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      fieldCount++;
      int n=object.valueBeanArray.length;
      Bean64 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanArray[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean64BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
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

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
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

    // field valueCharArray (mapped with "valueCharArray")
    if (object.valueCharArray!=null)  {
      fieldCount++;
      int n=object.valueCharArray.length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueCharArray[i];
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
    if (object.valueCharList!=null)  {
      fieldCount++;
      int n=object.valueCharList.size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueCharList.get(i);
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
    jacksonSerializer.writeStringField("valueCharType", PrimitiveUtils.writeCharacter(object.valueCharType));

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.valueCharTypeArray!=null)  {
      fieldCount++;
      int n=object.valueCharTypeArray.length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharTypeArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueCharTypeArray[i];
          jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
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

    // field valueEnumType (mapped with "valueEnumType")
    if (object.valueEnumType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnumType", object.valueEnumType.toString());
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

    // field valueLinkedMapStringBean (mapped with "valueLinkedMapStringBean")
    if (object.valueLinkedMapStringBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueLinkedMapStringBean.size()>0) {
        jacksonSerializer.writeFieldName("valueLinkedMapStringBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Bean64> item: object.valueLinkedMapStringBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeFieldName("value");
            if (bean64BindMap.serializeOnJacksonAsString(item.getValue(), jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("value");
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueLinkedMapStringBean", "null");
      }
    }

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
    }

    // field valueLong (mapped with "valueLong")
    if (object.valueLong!=null)  {
      jacksonSerializer.writeStringField("valueLong", PrimitiveUtils.writeLong(object.valueLong));
    }

    // field valueLongArray (mapped with "valueLongArray")
    if (object.valueLongArray!=null)  {
      fieldCount++;
      int n=object.valueLongArray.length;
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueLongArray[i];
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
    if (object.valueLongList!=null)  {
      fieldCount++;
      int n=object.valueLongList.size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueLongList.get(i);
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
    jacksonSerializer.writeStringField("valueLongType", PrimitiveUtils.writeLong(object.valueLongType));

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.valueLongTypeArray!=null)  {
      fieldCount++;
      int n=object.valueLongTypeArray.length;
      long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongTypeArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueLongTypeArray[i];
          jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueMapStringBean (mapped with "valueMapStringBean")
    if (object.valueMapStringBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringBean.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Bean64> item: object.valueMapStringBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeFieldName("value");
            if (bean64BindMap.serializeOnJacksonAsString(item.getValue(), jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("value");
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapStringBean", "null");
      }
    }

    // field valueSetString (mapped with "valueSetString")
    if (object.valueSetString!=null)  {
      fieldCount++;
      int n=object.valueSetString.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueSetString");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (String item: object.valueSetString) {
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

    // field valueShort (mapped with "valueShort")
    if (object.valueShort!=null)  {
      jacksonSerializer.writeStringField("valueShort", PrimitiveUtils.writeShort(object.valueShort));
    }

    // field valueShortType (mapped with "valueShortType")
    jacksonSerializer.writeStringField("valueShortType", PrimitiveUtils.writeShort(object.valueShortType));

    // field valueStrinList (mapped with "valueStrinList")
    if (object.valueStrinList!=null)  {
      fieldCount++;
      int n=object.valueStrinList.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStrinList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueStrinList.get(i);
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
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.valueStringArray!=null)  {
      fieldCount++;
      int n=object.valueStringArray.length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueStringArray[i];
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
    if (object.valueTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTime", SQLTimeUtils.write(object.valueTime));
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.valueTimeList!=null)  {
      fieldCount++;
      int n=object.valueTimeList.size();
      Time item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueTimeList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueTimeList.get(i);
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
    if (object.valueTimeZone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtils.write(object.valueTimeZone));
    }

    // field valueUrl (mapped with "valueUrl")
    if (object.valueUrl!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueUrl", UrlUtils.write(object.valueUrl));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean64 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean64");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueBean (mapped with "valueBean")
    if (object.valueBean!=null)  {
      xmlSerializer.writeStartElement("valueBean");
      bean64BindMap.serializeOnXml(object.valueBean, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      int n=object.valueBeanArray.length;
      Bean64 item;
      for (int i=0; i<n; i++) {
        item=object.valueBeanArray[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueBeanArray");
        } else {
          xmlSerializer.writeStartElement("valueBeanArray");
          bean64BindMap.serializeOnXml(item, xmlSerializer, 2);
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

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null) {
      xmlSerializer.writeStartElement("valueByteArray");
      xmlSerializer.writeBinary(object.valueByteArray);
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

    // field valueCharArray (mapped with "valueCharArray")
    if (object.valueCharArray!=null)  {
      int n=object.valueCharArray.length;
      Character item;
      for (int i=0; i<n; i++) {
        item=object.valueCharArray[i];
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
    if (object.valueCharList!=null)  {
      int n=object.valueCharList.size();
      Character item;
      for (int i=0; i<n; i++) {
        item=object.valueCharList.get(i);
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
    xmlSerializer.writeInt(object.valueCharType);
    xmlSerializer.writeEndElement();

    // field valueCharTypeArray (mapped with "valueCharTypeArray")
    if (object.valueCharTypeArray!=null)  {
      int n=object.valueCharTypeArray.length;
      char item;
      for (int i=0; i<n; i++) {
        item=object.valueCharTypeArray[i];
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

    // field valueEnumType (mapped with "valueEnumType")
    if (object.valueEnumType!=null)  {
      xmlSerializer.writeStartElement("valueEnumType");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueEnumType.toString()));
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

    // field valueLinkedMapStringBean (mapped with "valueLinkedMapStringBean")
    if (object.valueLinkedMapStringBean!=null)  {
      for (Map.Entry<String, Bean64> item: object.valueLinkedMapStringBean.entrySet()) {
        xmlSerializer.writeStartElement("valueLinkedMapStringBean");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("value");
              bean64BindMap.serializeOnXml(item.getValue(), xmlSerializer, 2);
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

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

    // field valueLongArray (mapped with "valueLongArray")
    if (object.valueLongArray!=null)  {
      int n=object.valueLongArray.length;
      Long item;
      for (int i=0; i<n; i++) {
        item=object.valueLongArray[i];
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
    if (object.valueLongList!=null)  {
      int n=object.valueLongList.size();
      Long item;
      for (int i=0; i<n; i++) {
        item=object.valueLongList.get(i);
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
    xmlSerializer.writeLong(object.valueLongType);
    xmlSerializer.writeEndElement();

    // field valueLongTypeArray (mapped with "valueLongTypeArray")
    if (object.valueLongTypeArray!=null)  {
      int n=object.valueLongTypeArray.length;
      long item;
      for (int i=0; i<n; i++) {
        item=object.valueLongTypeArray[i];
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

    // field valueMapStringBean (mapped with "valueMapStringBean")
    if (object.valueMapStringBean!=null)  {
      for (Map.Entry<String, Bean64> item: object.valueMapStringBean.entrySet()) {
        xmlSerializer.writeStartElement("valueMapStringBean");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("value");
              bean64BindMap.serializeOnXml(item.getValue(), xmlSerializer, 2);
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field valueSetString (mapped with "valueSetString")
    if (object.valueSetString!=null)  {
      int n=object.valueSetString.size();
      for (String item: object.valueSetString) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueSetString");
        } else {
          xmlSerializer.writeStartElement("valueSetString");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueSetString");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

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

    // field valueStrinList (mapped with "valueStrinList")
    if (object.valueStrinList!=null)  {
      int n=object.valueStrinList.size();
      String item;
      for (int i=0; i<n; i++) {
        item=object.valueStrinList.get(i);
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
    if (object.valueString!=null) {
      xmlSerializer.writeStartElement("valueString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
      xmlSerializer.writeEndElement();
    }

    // field valueStringArray (mapped with "valueStringArray")
    if (object.valueStringArray!=null)  {
      int n=object.valueStringArray.length;
      String item;
      for (int i=0; i<n; i++) {
        item=object.valueStringArray[i];
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
    if (object.valueTime!=null)  {
      xmlSerializer.writeStartElement("valueTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLTimeUtils.write(object.valueTime)));
      xmlSerializer.writeEndElement();
    }

    // field valueTimeList (mapped with "valueTimeList")
    if (object.valueTimeList!=null)  {
      int n=object.valueTimeList.size();
      Time item;
      for (int i=0; i<n; i++) {
        item=object.valueTimeList.get(i);
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

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean64 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean64 instance = new Bean64();
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
            instance.id=jacksonParser.getLongValue();
          break;
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.valueBean=bean64BindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean64> collection=new ArrayList<>();
              Bean64 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean64BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean64[collection.size()]);
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
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteArray=jacksonParser.getBinaryValue();
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
              instance.valueCharArray=CollectionUtils.asCharacterArray(collection);
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
              instance.valueCharList=collection;
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.valueCharType=Character.valueOf((char)jacksonParser.getIntValue());
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
              instance.valueCharTypeArray=CollectionUtils.asCharacterTypeArray(collection);
            }
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
          case "valueEnumType":
            // field valueEnumType (mapped with "valueEnumType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnumType=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
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
          case "valueLinkedMapStringBean":
            // field valueLinkedMapStringBean (mapped with "valueLinkedMapStringBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashMap<String, Bean64> collection=new LinkedHashMap<>();
              String key=null;
              Bean64 value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                  value=bean64BindMap.parseOnJackson(jacksonParser);
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueLinkedMapStringBean=collection;
            }
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
              instance.valueLongArray=CollectionUtils.asLongArray(collection);
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
              instance.valueLongList=collection;
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.valueLongType=jacksonParser.getLongValue();
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
              instance.valueLongTypeArray=CollectionUtils.asLongTypeArray(collection);
            }
          break;
          case "valueMapStringBean":
            // field valueMapStringBean (mapped with "valueMapStringBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Bean64> collection=new HashMap<>();
              String key=null;
              Bean64 value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                  value=bean64BindMap.parseOnJackson(jacksonParser);
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapStringBean=collection;
            }
          break;
          case "valueSetString":
            // field valueSetString (mapped with "valueSetString")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<String> collection=new HashSet<>();
              String item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getText();
                }
                collection.add(item);
              }
              instance.valueSetString=collection;
            }
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
              instance.valueStrinList=collection;
            }
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
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
              instance.valueStringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTime=SQLTimeUtils.read(jacksonParser.getText());
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
              instance.valueTimeList=collection;
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
  public Bean64 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean64 instance = new Bean64();
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
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueBean":
            // field valueBean (mapped with "valueBean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.valueBean=bean64BindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean64> collection=new ArrayList<>();
              Bean64 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean64BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean64[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean64> collection=new ArrayList<>();
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean64[collection.size()]);
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
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteArray=Base64Utils.decode(jacksonParser.getValueAsString());
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
              instance.valueCharArray=CollectionUtils.asCharacterArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.valueCharArray=CollectionUtils.asCharacterArray(collection);
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
              instance.valueCharList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Character> collection=new LinkedList<>();
              instance.valueCharList=collection;
            }
          break;
          case "valueCharType":
            // field valueCharType (mapped with "valueCharType")
            instance.valueCharType=PrimitiveUtils.readCharacter(jacksonParser.getText(), ' ');
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
              instance.valueCharTypeArray=CollectionUtils.asCharacterTypeArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.valueCharTypeArray=CollectionUtils.asCharacterTypeArray(collection);
            }
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
          case "valueEnumType":
            // field valueEnumType (mapped with "valueEnumType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnumType=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
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
          case "valueLinkedMapStringBean":
            // field valueLinkedMapStringBean (mapped with "valueLinkedMapStringBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashMap<String, Bean64> collection=new LinkedHashMap<>();
              String key=null;
              Bean64 value=null;
              JsonToken current;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                current=jacksonParser.currentToken();
                for (int i=0; i<2 ;i++) {
                  while (current != JsonToken.FIELD_NAME) {
                    current=jacksonParser.nextToken();
                  }
                  jacksonParser.nextValue();
                  switch(jacksonParser.getCurrentName()) {
                  case "key":
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      key=jacksonParser.getText();
                    }
                  break;
                  case "value":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                        value=bean64BindMap.parseOnJacksonAsString(jacksonParser);
                      }
                    }
                  break;
                  }
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueLinkedMapStringBean=collection;
            }
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
              instance.valueLongArray=CollectionUtils.asLongArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              instance.valueLongArray=CollectionUtils.asLongArray(collection);
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
              instance.valueLongList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Long> collection=new LinkedList<>();
              instance.valueLongList=collection;
            }
          break;
          case "valueLongType":
            // field valueLongType (mapped with "valueLongType")
            instance.valueLongType=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
              instance.valueLongTypeArray=CollectionUtils.asLongTypeArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              instance.valueLongTypeArray=CollectionUtils.asLongTypeArray(collection);
            }
          break;
          case "valueMapStringBean":
            // field valueMapStringBean (mapped with "valueMapStringBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Bean64> collection=new HashMap<>();
              String key=null;
              Bean64 value=null;
              JsonToken current;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                current=jacksonParser.currentToken();
                for (int i=0; i<2 ;i++) {
                  while (current != JsonToken.FIELD_NAME) {
                    current=jacksonParser.nextToken();
                  }
                  jacksonParser.nextValue();
                  switch(jacksonParser.getCurrentName()) {
                  case "key":
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      key=jacksonParser.getText();
                    }
                  break;
                  case "value":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                        value=bean64BindMap.parseOnJacksonAsString(jacksonParser);
                      }
                    }
                  break;
                  }
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapStringBean=collection;
            }
          break;
          case "valueSetString":
            // field valueSetString (mapped with "valueSetString")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<String> collection=new HashSet<>();
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
              instance.valueSetString=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              HashSet<String> collection=new HashSet<>();
              instance.valueSetString=collection;
            }
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
              instance.valueStrinList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<String> collection=new LinkedList<>();
              instance.valueStrinList=collection;
            }
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
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
              instance.valueStringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              instance.valueStringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
            }
          break;
          case "valueTime":
            // field valueTime (mapped with "valueTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueTime=SQLTimeUtils.read(jacksonParser.getText());
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
              instance.valueTimeList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Time> collection=new ArrayList<>();
              instance.valueTimeList=collection;
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
  public Bean64 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean64 instance = new Bean64();
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
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "valueBean":
                  // property valueBean (mapped on "valueBean")
                  instance.valueBean=bean64BindMap.parseOnXml(xmlParser, eventType);
                break;
                case "valueBeanArray":
                  // property valueBeanArray (mapped on "valueBeanArray")
                   {
                    ArrayList<Bean64> collection=new ArrayList<>();
                    Bean64 item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean64BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueBeanArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean64BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean64[collection.size()]);
                    read=false;
                  }
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
                case "valueByteArray":
                  // property valueByteArray (mapped on "valueByteArray")
                  instance.valueByteArray=xmlParser.getElementAsBinary();
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
                case "valueCharArray":
                  // property valueCharArray (mapped on "valueCharArray")
                   {
                    ArrayList<Character> collection=new ArrayList<>();
                    Character item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueCharArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueCharArray=CollectionUtils.asCharacterArray(collection);
                    read=false;
                  }
                break;
                case "valueCharList":
                  // property valueCharList (mapped on "valueCharList")
                   {
                    LinkedList<Character> collection=new LinkedList<>();
                    Character item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueCharList")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueCharList=collection;
                    read=false;
                  }
                break;
                case "valueCharType":
                  // property valueCharType (mapped on "valueCharType")
                  instance.valueCharType=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                break;
                case "valueCharTypeArray":
                  // property valueCharTypeArray (mapped on "valueCharTypeArray")
                   {
                    ArrayList<Character> collection=new ArrayList<>();
                    Character item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueCharTypeArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      }
                      collection.add(item);
                    }
                    instance.valueCharTypeArray=CollectionUtils.asCharacterTypeArray(collection);
                    read=false;
                  }
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
                case "valueEnumType":
                  // property valueEnumType (mapped on "valueEnumType")
                  instance.valueEnumType=EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
                case "valueLinkedMapStringBean":
                  // property valueLinkedMapStringBean (mapped on "valueLinkedMapStringBean")
                   {
                    LinkedHashMap<String, Bean64> collection=new LinkedHashMap<>();
                    String key;
                    Bean64 value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=bean64BindMap.parseOnXml(xmlParser, eventType);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueLinkedMapStringBean")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=bean64BindMap.parseOnXml(xmlParser, eventType);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueLinkedMapStringBean=collection;
                    read=false;
                  }
                break;
                case "valueLocale":
                  // property valueLocale (mapped on "valueLocale")
                  instance.valueLocale=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueLong":
                  // property valueLong (mapped on "valueLong")
                  instance.valueLong=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                break;
                case "valueLongArray":
                  // property valueLongArray (mapped on "valueLongArray")
                   {
                    ArrayList<Long> collection=new ArrayList<>();
                    Long item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueLongArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueLongArray=CollectionUtils.asLongArray(collection);
                    read=false;
                  }
                break;
                case "valueLongList":
                  // property valueLongList (mapped on "valueLongList")
                   {
                    LinkedList<Long> collection=new LinkedList<>();
                    Long item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueLongList")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueLongList=collection;
                    read=false;
                  }
                break;
                case "valueLongType":
                  // property valueLongType (mapped on "valueLongType")
                  instance.valueLongType=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "valueLongTypeArray":
                  // property valueLongTypeArray (mapped on "valueLongTypeArray")
                   {
                    ArrayList<Long> collection=new ArrayList<>();
                    Long item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueLongTypeArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                      }
                      collection.add(item);
                    }
                    instance.valueLongTypeArray=CollectionUtils.asLongTypeArray(collection);
                    read=false;
                  }
                break;
                case "valueMapStringBean":
                  // property valueMapStringBean (mapped on "valueMapStringBean")
                   {
                    HashMap<String, Bean64> collection=new HashMap<>();
                    String key;
                    Bean64 value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=bean64BindMap.parseOnXml(xmlParser, eventType);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapStringBean")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=bean64BindMap.parseOnXml(xmlParser, eventType);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapStringBean=collection;
                    read=false;
                  }
                break;
                case "valueSetString":
                  // property valueSetString (mapped on "valueSetString")
                   {
                    HashSet<String> collection=new HashSet<>();
                    String item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueSetString")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueSetString=collection;
                    read=false;
                  }
                break;
                case "valueShort":
                  // property valueShort (mapped on "valueShort")
                  instance.valueShort=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                break;
                case "valueShortType":
                  // property valueShortType (mapped on "valueShortType")
                  instance.valueShortType=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), (short)0);
                break;
                case "valueStrinList":
                  // property valueStrinList (mapped on "valueStrinList")
                   {
                    LinkedList<String> collection=new LinkedList<>();
                    String item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueStrinList")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueStrinList=collection;
                    read=false;
                  }
                break;
                case "valueString":
                  // property valueString (mapped on "valueString")
                  instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueStringArray":
                  // property valueStringArray (mapped on "valueStringArray")
                   {
                    ArrayList<String> collection=new ArrayList<>();
                    String item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueStringArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueStringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
                    read=false;
                  }
                break;
                case "valueTime":
                  // property valueTime (mapped on "valueTime")
                  instance.valueTime=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueTimeList":
                  // property valueTimeList (mapped on "valueTimeList")
                   {
                    ArrayList<Time> collection=new ArrayList<>();
                    Time item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueTimeList")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueTimeList=collection;
                    read=false;
                  }
                break;
                case "valueTimeZone":
                  // property valueTimeZone (mapped on "valueTimeZone")
                  instance.valueTimeZone=TimeZoneUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueUrl":
                  // property valueUrl (mapped on "valueUrl")
                  instance.valueUrl=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }
