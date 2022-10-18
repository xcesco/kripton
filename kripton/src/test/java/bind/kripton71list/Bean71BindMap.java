package bind.kripton71list;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is binder map for Bean71
 *
 * @see Bean71
 */
@BindMap(Bean71.class)
public class Bean71BindMap extends AbstractMapper<Bean71> {
  /**
   * Bean71BindMap */
  private Bean71BindMap bean71BindMap = this;

  @Override
  public int serializeOnJackson(Bean71 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueBigIntegerList (mapped with "valueBigIntegerList")
    if (object.valueBigIntegerList!=null)  {
      fieldCount++;
      int n=object.valueBigIntegerList.size();
      BigInteger item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigIntegerList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBigIntegerList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(BigIntegerUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueByteList (mapped with "valueByteList")
    if (object.valueByteList!=null)  {
      fieldCount++;
      int n=object.valueByteList.size();
      Byte item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueByteList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCharacterList (mapped with "valueCharacterList")
    if (object.valueCharacterList!=null)  {
      fieldCount++;
      int n=object.valueCharacterList.size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharacterList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueCharacterList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueDoubleList (mapped with "valueDoubleList")
    if (object.valueDoubleList!=null)  {
      fieldCount++;
      int n=object.valueDoubleList.size();
      Double item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueDoubleList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueDoubleList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueEnumList (mapped with "valueEnumList")
    if (object.valueEnumList!=null)  {
      fieldCount++;
      int n=object.valueEnumList.size();
      Enum71 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueEnumList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item.toString());
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueFloatList (mapped with "valueFloatList")
    if (object.valueFloatList!=null)  {
      fieldCount++;
      int n=object.valueFloatList.size();
      Float item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueFloatList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueFloatList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueIntList (mapped with "valueIntList")
    if (object.getValueIntList()!=null)  {
      fieldCount++;
      int n=object.getValueIntList().size();
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueIntList().get(i);
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

    // field valueShortList (mapped with "valueShortList")
    if (object.valueShortList!=null)  {
      fieldCount++;
      int n=object.valueShortList.size();
      Short item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueShortList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueShortList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueStringList (mapped with "valueStringList")
    if (object.valueStringList!=null)  {
      fieldCount++;
      int n=object.valueStringList.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueStringList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueBeanList (mapped with "valueBeanList")
    if (object.valueBeanList!=null)  {
      fieldCount++;
      int n=object.valueBeanList.size();
      Bean71 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBeanList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean71BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueBigDecimalList (mapped with "valueBigDecimalList")
    if (object.valueBigDecimalList!=null)  {
      fieldCount++;
      int n=object.valueBigDecimalList.size();
      BigDecimal item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigDecimalList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBigDecimalList.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(BigDecimalUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field zalueStringFinal (mapped with "zalueStringFinal")
    if (object.zalueStringFinal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean71 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueBigIntegerList (mapped with "valueBigIntegerList")
    if (object.valueBigIntegerList!=null)  {
      fieldCount++;
      int n=object.valueBigIntegerList.size();
      BigInteger item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigIntegerList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigIntegerList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(BigIntegerUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueByteList (mapped with "valueByteList")
    if (object.valueByteList!=null)  {
      fieldCount++;
      int n=object.valueByteList.size();
      Byte item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueByteList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeByte(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueCharacterList (mapped with "valueCharacterList")
    if (object.valueCharacterList!=null)  {
      fieldCount++;
      int n=object.valueCharacterList.size();
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharacterList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueCharacterList.get(i);
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

    // field valueDoubleList (mapped with "valueDoubleList")
    if (object.valueDoubleList!=null)  {
      fieldCount++;
      int n=object.valueDoubleList.size();
      Double item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueDoubleList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueDoubleList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeDouble(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueEnumList (mapped with "valueEnumList")
    if (object.valueEnumList!=null)  {
      fieldCount++;
      int n=object.valueEnumList.size();
      Enum71 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(item.toString());
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueFloatList (mapped with "valueFloatList")
    if (object.valueFloatList!=null)  {
      fieldCount++;
      int n=object.valueFloatList.size();
      Float item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueFloatList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueFloatList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeFloat(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueIntList (mapped with "valueIntList")
    if (object.getValueIntList()!=null)  {
      fieldCount++;
      int n=object.getValueIntList().size();
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueIntList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeInteger(item));
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

    // field valueShortList (mapped with "valueShortList")
    if (object.valueShortList!=null)  {
      fieldCount++;
      int n=object.valueShortList.size();
      Short item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueShortList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueShortList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeShort(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueStringList (mapped with "valueStringList")
    if (object.valueStringList!=null)  {
      fieldCount++;
      int n=object.valueStringList.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueStringList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueStringList.get(i);
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

    // field valueBeanList (mapped with "valueBeanList")
    if (object.valueBeanList!=null)  {
      fieldCount++;
      int n=object.valueBeanList.size();
      Bean71 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean71BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("valueBeanList");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueBigDecimalList (mapped with "valueBigDecimalList")
    if (object.valueBigDecimalList!=null)  {
      fieldCount++;
      int n=object.valueBigDecimalList.size();
      BigDecimal item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigDecimalList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalList.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(BigDecimalUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field zalueStringFinal (mapped with "zalueStringFinal")
    if (object.zalueStringFinal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean71 object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean71");
    }

    // Persisted fields:

    // field valueBigIntegerList (mapped with "valueBigIntegerList")
    if (object.valueBigIntegerList!=null)  {
      int n=object.valueBigIntegerList.size();
      BigInteger item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueBigIntegerList");
      for (int i=0; i<n; i++) {
        item=object.valueBigIntegerList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtils.write(item)));
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueByteList (mapped with "valueByteList")
    if (object.valueByteList!=null)  {
      int n=object.valueByteList.size();
      Byte item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueByteList");
      for (int i=0; i<n; i++) {
        item=object.valueByteList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueCharacterList (mapped with "valueCharacterList")
    if (object.valueCharacterList!=null)  {
      int n=object.valueCharacterList.size();
      Character item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueCharacterList");
      for (int i=0; i<n; i++) {
        item=object.valueCharacterList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueDoubleList (mapped with "valueDoubleList")
    if (object.valueDoubleList!=null)  {
      int n=object.valueDoubleList.size();
      Double item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueDoubleList");
      for (int i=0; i<n; i++) {
        item=object.valueDoubleList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeDouble(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueEnumList (mapped with "valueEnumList")
    if (object.valueEnumList!=null)  {
      int n=object.valueEnumList.size();
      Enum71 item;
      for (int i=0; i<n; i++) {
        item=object.valueEnumList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueEnumList");
        } else {
          xmlSerializer.writeStartElement("valueEnumList");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueEnumList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueFloatList (mapped with "valueFloatList")
    if (object.valueFloatList!=null)  {
      int n=object.valueFloatList.size();
      Float item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueFloatList");
      for (int i=0; i<n; i++) {
        item=object.valueFloatList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeFloat(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueIntList (mapped with "valueIntList")
    if (object.getValueIntList()!=null)  {
      int n=object.getValueIntList().size();
      Integer item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueIntList");
      for (int i=0; i<n; i++) {
        item=object.getValueIntList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueLongList (mapped with "valueLongList")
    if (object.valueLongList!=null)  {
      int n=object.valueLongList.size();
      Long item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueLongList");
      for (int i=0; i<n; i++) {
        item=object.valueLongList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeLong(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueShortList (mapped with "valueShortList")
    if (object.valueShortList!=null)  {
      int n=object.valueShortList.size();
      Short item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueShortList");
      for (int i=0; i<n; i++) {
        item=object.valueShortList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueStringList (mapped with "valueStringList")
    if (object.valueStringList!=null)  {
      int n=object.valueStringList.size();
      String item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueStringList");
      for (int i=0; i<n; i++) {
        item=object.valueStringList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueBeanList (mapped with "valueBeanList")
    if (object.valueBeanList!=null)  {
      int n=object.valueBeanList.size();
      Bean71 item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueBeanList");
      for (int i=0; i<n; i++) {
        item=object.valueBeanList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          bean71BindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueBigDecimalList (mapped with "valueBigDecimalList")
    if (object.valueBigDecimalList!=null)  {
      int n=object.valueBigDecimalList.size();
      BigDecimal item;
      for (int i=0; i<n; i++) {
        item=object.valueBigDecimalList.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueBigDecimalList");
        } else {
          xmlSerializer.writeStartElement("valueBigDecimalList");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(item)));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueBigDecimalList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field zalueStringFinal (mapped with "zalueStringFinal")
    if (object.zalueStringFinal!=null) {
      xmlSerializer.writeStartElement("zalueStringFinal");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.zalueStringFinal));
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
  public Bean71 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean71 instance = new Bean71();
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
          case "valueBigIntegerList":
            // field valueBigIntegerList (mapped with "valueBigIntegerList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<BigInteger> collection=new LinkedList<>();
              BigInteger item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=BigIntegerUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigIntegerList=collection;
            }
          break;
          case "valueByteList":
            // field valueByteList (mapped with "valueByteList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Byte> collection=new ArrayList<>();
              Byte item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getByteValue();
                }
                collection.add(item);
              }
              instance.valueByteList=collection;
            }
          break;
          case "valueCharacterList":
            // field valueCharacterList (mapped with "valueCharacterList")
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
              instance.valueCharacterList=collection;
            }
          break;
          case "valueDoubleList":
            // field valueDoubleList (mapped with "valueDoubleList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Double> collection=new ArrayList<>();
              Double item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getDoubleValue();
                }
                collection.add(item);
              }
              instance.valueDoubleList=collection;
            }
          break;
          case "valueEnumList":
            // field valueEnumList (mapped with "valueEnumList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Enum71> collection=new LinkedList<>();
              Enum71 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?Enum71.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumList=collection;
            }
          break;
          case "valueFloatList":
            // field valueFloatList (mapped with "valueFloatList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Float> collection=new ArrayList<>();
              Float item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getFloatValue();
                }
                collection.add(item);
              }
              instance.valueFloatList=collection;
            }
          break;
          case "valueIntList":
            // field valueIntList (mapped with "valueIntList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Integer> collection=new ArrayList<>();
              Integer item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getIntValue();
                }
                collection.add(item);
              }
              instance.setValueIntList(collection);
            }
          break;
          case "valueLongList":
            // field valueLongList (mapped with "valueLongList")
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
              instance.valueLongList=collection;
            }
          break;
          case "valueShortList":
            // field valueShortList (mapped with "valueShortList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Short> collection=new ArrayList<>();
              Short item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getShortValue();
                }
                collection.add(item);
              }
              instance.valueShortList=collection;
            }
          break;
          case "valueStringList":
            // field valueStringList (mapped with "valueStringList")
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
              instance.valueStringList=collection;
            }
          break;
          case "valueBeanList":
            // field valueBeanList (mapped with "valueBeanList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Bean71> collection=new LinkedList<>();
              Bean71 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean71BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanList=collection;
            }
          break;
          case "valueBigDecimalList":
            // field valueBigDecimalList (mapped with "valueBigDecimalList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<BigDecimal> collection=new LinkedList<>();
              BigDecimal item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=BigDecimalUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigDecimalList=collection;
            }
          break;
          case "zalueStringFinal":
            // field zalueStringFinal (mapped with "zalueStringFinal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zalueStringFinal=jacksonParser.getText();
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
  public Bean71 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean71 instance = new Bean71();
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
          case "valueBigIntegerList":
            // field valueBigIntegerList (mapped with "valueBigIntegerList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<BigInteger> collection=new LinkedList<>();
              BigInteger item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=BigIntegerUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigIntegerList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<BigInteger> collection=new LinkedList<>();
              instance.valueBigIntegerList=collection;
            }
          break;
          case "valueByteList":
            // field valueByteList (mapped with "valueByteList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Byte> collection=new ArrayList<>();
              Byte item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readByte(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueByteList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Byte> collection=new ArrayList<>();
              instance.valueByteList=collection;
            }
          break;
          case "valueCharacterList":
            // field valueCharacterList (mapped with "valueCharacterList")
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
              instance.valueCharacterList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.valueCharacterList=collection;
            }
          break;
          case "valueDoubleList":
            // field valueDoubleList (mapped with "valueDoubleList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Double> collection=new ArrayList<>();
              Double item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueDoubleList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Double> collection=new ArrayList<>();
              instance.valueDoubleList=collection;
            }
          break;
          case "valueEnumList":
            // field valueEnumList (mapped with "valueEnumList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Enum71> collection=new LinkedList<>();
              Enum71 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?Enum71.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Enum71> collection=new LinkedList<>();
              instance.valueEnumList=collection;
            }
          break;
          case "valueFloatList":
            // field valueFloatList (mapped with "valueFloatList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Float> collection=new ArrayList<>();
              Float item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readFloat(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueFloatList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Float> collection=new ArrayList<>();
              instance.valueFloatList=collection;
            }
          break;
          case "valueIntList":
            // field valueIntList (mapped with "valueIntList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Integer> collection=new ArrayList<>();
              Integer item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.setValueIntList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Integer> collection=new ArrayList<>();
              instance.setValueIntList(collection);
            }
          break;
          case "valueLongList":
            // field valueLongList (mapped with "valueLongList")
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
              instance.valueLongList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              instance.valueLongList=collection;
            }
          break;
          case "valueShortList":
            // field valueShortList (mapped with "valueShortList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Short> collection=new ArrayList<>();
              Short item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readShort(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueShortList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Short> collection=new ArrayList<>();
              instance.valueShortList=collection;
            }
          break;
          case "valueStringList":
            // field valueStringList (mapped with "valueStringList")
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
              instance.valueStringList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              instance.valueStringList=collection;
            }
          break;
          case "valueBeanList":
            // field valueBeanList (mapped with "valueBeanList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<Bean71> collection=new LinkedList<>();
              Bean71 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean71BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<Bean71> collection=new LinkedList<>();
              instance.valueBeanList=collection;
            }
          break;
          case "valueBigDecimalList":
            // field valueBigDecimalList (mapped with "valueBigDecimalList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedList<BigDecimal> collection=new LinkedList<>();
              BigDecimal item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=BigDecimalUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigDecimalList=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedList<BigDecimal> collection=new LinkedList<>();
              instance.valueBigDecimalList=collection;
            }
          break;
          case "zalueStringFinal":
            // field zalueStringFinal (mapped with "zalueStringFinal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.zalueStringFinal=jacksonParser.getText();
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
  public Bean71 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean71 instance = new Bean71();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName();
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
            currentTag = xmlParser.getName();
            switch(currentTag) {
                case "valueBigIntegerList":
                  // property valueBigIntegerList (mapped on "valueBigIntegerList")
                   {
                    LinkedList<BigInteger> collection=CollectionUtils.merge(new LinkedList<>(), instance.valueBigIntegerList);
                    BigInteger item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueBigIntegerList=collection;
                  }
                break;
                case "valueByteList":
                  // property valueByteList (mapped on "valueByteList")
                   {
                    ArrayList<Byte> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueByteList);
                    Byte item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueByteList=collection;
                  }
                break;
                case "valueCharacterList":
                  // property valueCharacterList (mapped on "valueCharacterList")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueCharacterList);
                    Character item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueCharacterList=collection;
                  }
                break;
                case "valueDoubleList":
                  // property valueDoubleList (mapped on "valueDoubleList")
                   {
                    ArrayList<Double> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueDoubleList);
                    Double item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueDoubleList=collection;
                  }
                break;
                case "valueEnumList":
                  // property valueEnumList (mapped on "valueEnumList")
                   {
                    LinkedList<Enum71> collection=CollectionUtils.merge(new LinkedList<>(), instance.valueEnumList);
                    Enum71 item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=Enum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueEnumList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=Enum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueEnumList=collection;
                    read=false;
                  }
                break;
                case "valueFloatList":
                  // property valueFloatList (mapped on "valueFloatList")
                   {
                    ArrayList<Float> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueFloatList);
                    Float item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueFloatList=collection;
                  }
                break;
                case "valueIntList":
                  // property valueIntList (mapped on "valueIntList")
                   {
                    ArrayList<Integer> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueIntList());
                    Integer item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueIntList(collection);
                  }
                break;
                case "valueLongList":
                  // property valueLongList (mapped on "valueLongList")
                   {
                    ArrayList<Long> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueLongList);
                    Long item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueLongList=collection;
                  }
                break;
                case "valueShortList":
                  // property valueShortList (mapped on "valueShortList")
                   {
                    ArrayList<Short> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueShortList);
                    Short item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueShortList=collection;
                  }
                break;
                case "valueStringList":
                  // property valueStringList (mapped on "valueStringList")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueStringList);
                    String item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueStringList=collection;
                  }
                break;
                case "valueBeanList":
                  // property valueBeanList (mapped on "valueBeanList")
                   {
                    LinkedList<Bean71> collection=CollectionUtils.merge(new LinkedList<>(), instance.valueBeanList);
                    Bean71 item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean71BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.valueBeanList=collection;
                  }
                break;
                case "valueBigDecimalList":
                  // property valueBigDecimalList (mapped on "valueBigDecimalList")
                   {
                    LinkedList<BigDecimal> collection=CollectionUtils.merge(new LinkedList<>(), instance.valueBigDecimalList);
                    BigDecimal item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueBigDecimalList")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueBigDecimalList=collection;
                    read=false;
                  }
                break;
                case "zalueStringFinal":
                  // property zalueStringFinal (mapped on "zalueStringFinal")
                  instance.zalueStringFinal=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      // if document is empty, the element is null
      if (currentEventType == EventType.START_DOCUMENT && eventType == EventType.END_DOCUMENT) {
          return null;
        } else {
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
      }
    }
