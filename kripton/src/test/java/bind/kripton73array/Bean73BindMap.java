package bind.kripton73array;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This class is binder map for Bean73
 *
 * @see Bean73
 */
@BindMap(Bean73.class)
public class Bean73BindMap extends AbstractMapper<Bean73> {
  /**
   * Bean73BindMap */
  private Bean73BindMap bean73BindMap = this;

  @Override
  public int serializeOnJackson(Bean73 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      fieldCount++;
      int n=object.valueBeanArray.length;
      Bean73 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBeanArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean73BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      int n=object.valueByteArray.length;
      Byte item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueByteArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueCharacterArray (mapped with "valueCharacterArray")
    if (object.valueCharacterArray!=null)  {
      fieldCount++;
      int n=object.valueCharacterArray.length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharacterArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueCharacterArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueDoubleArray (mapped with "valueDoubleArray")
    if (object.valueDoubleArray!=null)  {
      fieldCount++;
      int n=object.valueDoubleArray.length;
      Double item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueDoubleArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueDoubleArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueEnumArray (mapped with "valueEnumArray")
    if (object.valueEnumArray!=null)  {
      fieldCount++;
      int n=object.valueEnumArray.length;
      Enum73 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueEnumArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item.toString());
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueFloatArray (mapped with "valueFloatArray")
    if (object.valueFloatArray!=null)  {
      fieldCount++;
      int n=object.valueFloatArray.length;
      Float item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueFloatArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueFloatArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueIntArray (mapped with "valueIntArray")
    if (object.getValueIntArray()!=null)  {
      fieldCount++;
      int n=object.getValueIntArray().length;
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValueIntArray()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
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

    // field valueShortArray (mapped with "valueShortArray")
    if (object.valueShortArray!=null)  {
      fieldCount++;
      int n=object.valueShortArray.length;
      Short item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueShortArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueShortArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
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

    // field valueBigDecimalArray (mapped with "valueBigDecimalArray")
    if (object.valueBigDecimalArray!=null)  {
      fieldCount++;
      int n=object.valueBigDecimalArray.length;
      BigDecimal item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigDecimalArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBigDecimalArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(BigDecimalUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueBigIntegerArray (mapped with "valueBigIntegerArray")
    if (object.valueBigIntegerArray!=null)  {
      fieldCount++;
      int n=object.valueBigIntegerArray.length;
      BigInteger item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigIntegerArray");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.valueBigIntegerArray[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(BigIntegerUtils.write(item));
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean73 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      fieldCount++;
      int n=object.valueBeanArray.length;
      Bean73 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanArray[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean73BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("valueBeanArray");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      int n=object.valueByteArray.length;
      Byte item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueByteArray[i];
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

    // field valueCharacterArray (mapped with "valueCharacterArray")
    if (object.valueCharacterArray!=null)  {
      fieldCount++;
      int n=object.valueCharacterArray.length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueCharacterArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueCharacterArray[i];
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

    // field valueDoubleArray (mapped with "valueDoubleArray")
    if (object.valueDoubleArray!=null)  {
      fieldCount++;
      int n=object.valueDoubleArray.length;
      Double item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueDoubleArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueDoubleArray[i];
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

    // field valueEnumArray (mapped with "valueEnumArray")
    if (object.valueEnumArray!=null)  {
      fieldCount++;
      int n=object.valueEnumArray.length;
      Enum73 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumArray[i];
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

    // field valueFloatArray (mapped with "valueFloatArray")
    if (object.valueFloatArray!=null)  {
      fieldCount++;
      int n=object.valueFloatArray.length;
      Float item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueFloatArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueFloatArray[i];
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

    // field valueIntArray (mapped with "valueIntArray")
    if (object.getValueIntArray()!=null)  {
      fieldCount++;
      int n=object.getValueIntArray().length;
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValueIntArray()[i];
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

    // field valueShortArray (mapped with "valueShortArray")
    if (object.valueShortArray!=null)  {
      fieldCount++;
      int n=object.valueShortArray.length;
      Short item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueShortArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueShortArray[i];
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

    // field valueBigDecimalArray (mapped with "valueBigDecimalArray")
    if (object.valueBigDecimalArray!=null)  {
      fieldCount++;
      int n=object.valueBigDecimalArray.length;
      BigDecimal item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigDecimalArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalArray[i];
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

    // field valueBigIntegerArray (mapped with "valueBigIntegerArray")
    if (object.valueBigIntegerArray!=null)  {
      fieldCount++;
      int n=object.valueBigIntegerArray.length;
      BigInteger item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBigIntegerArray");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigIntegerArray[i];
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean73 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean73");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field valueBeanArray (mapped with "valueBeanArray")
    if (object.valueBeanArray!=null)  {
      int n=object.valueBeanArray.length;
      Bean73 item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueBeanArray");
      for (int i=0; i<n; i++) {
        item=object.valueBeanArray[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          bean73BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      int n=object.valueByteArray.length;
      Byte item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueByteArray");
      for (int i=0; i<n; i++) {
        item=object.valueByteArray[i];
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

    // field valueCharacterArray (mapped with "valueCharacterArray")
    if (object.valueCharacterArray!=null)  {
      int n=object.valueCharacterArray.length;
      Character item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueCharacterArray");
      for (int i=0; i<n; i++) {
        item=object.valueCharacterArray[i];
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

    // field valueDoubleArray (mapped with "valueDoubleArray")
    if (object.valueDoubleArray!=null)  {
      int n=object.valueDoubleArray.length;
      Double item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueDoubleArray");
      for (int i=0; i<n; i++) {
        item=object.valueDoubleArray[i];
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

    // field valueEnumArray (mapped with "valueEnumArray")
    if (object.valueEnumArray!=null)  {
      int n=object.valueEnumArray.length;
      Enum73 item;
      for (int i=0; i<n; i++) {
        item=object.valueEnumArray[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueEnumArray");
        } else {
          xmlSerializer.writeStartElement("valueEnumArray");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueEnumArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueFloatArray (mapped with "valueFloatArray")
    if (object.valueFloatArray!=null)  {
      int n=object.valueFloatArray.length;
      Float item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueFloatArray");
      for (int i=0; i<n; i++) {
        item=object.valueFloatArray[i];
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

    // field valueIntArray (mapped with "valueIntArray")
    if (object.getValueIntArray()!=null)  {
      int n=object.getValueIntArray().length;
      Integer item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueIntArray");
      for (int i=0; i<n; i++) {
        item=object.getValueIntArray()[i];
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

    // field valueLongArray (mapped with "valueLongArray")
    if (object.valueLongArray!=null)  {
      int n=object.valueLongArray.length;
      Long item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueLongArray");
      for (int i=0; i<n; i++) {
        item=object.valueLongArray[i];
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

    // field valueShortArray (mapped with "valueShortArray")
    if (object.valueShortArray!=null)  {
      int n=object.valueShortArray.length;
      Short item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueShortArray");
      for (int i=0; i<n; i++) {
        item=object.valueShortArray[i];
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

    // field valueStringArray (mapped with "valueStringArray")
    if (object.valueStringArray!=null)  {
      int n=object.valueStringArray.length;
      String item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueStringArray");
      for (int i=0; i<n; i++) {
        item=object.valueStringArray[i];
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

    // field valueBigDecimalArray (mapped with "valueBigDecimalArray")
    if (object.valueBigDecimalArray!=null)  {
      int n=object.valueBigDecimalArray.length;
      BigDecimal item;
      for (int i=0; i<n; i++) {
        item=object.valueBigDecimalArray[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueBigDecimalArray");
        } else {
          xmlSerializer.writeStartElement("valueBigDecimalArray");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(item)));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueBigDecimalArray");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueBigIntegerArray (mapped with "valueBigIntegerArray")
    if (object.valueBigIntegerArray!=null)  {
      int n=object.valueBigIntegerArray.length;
      BigInteger item;
      // write wrapper tag
      xmlSerializer.writeStartElement("valueBigIntegerArray");
      for (int i=0; i<n; i++) {
        item=object.valueBigIntegerArray[i];
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

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean73 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean73 instance = new Bean73();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean73> collection=new ArrayList<>();
              Bean73 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean73BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean73[collection.size()]);
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
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
              instance.valueByteArray=CollectionUtils.asByteArray(collection);
            }
          break;
          case "valueCharacterArray":
            // field valueCharacterArray (mapped with "valueCharacterArray")
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
              instance.valueCharacterArray=CollectionUtils.asCharacterArray(collection);
            }
          break;
          case "valueDoubleArray":
            // field valueDoubleArray (mapped with "valueDoubleArray")
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
              instance.valueDoubleArray=CollectionUtils.asDoubleArray(collection);
            }
          break;
          case "valueEnumArray":
            // field valueEnumArray (mapped with "valueEnumArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Enum73> collection=new ArrayList<>();
              Enum73 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?Enum73.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumArray=CollectionUtils.asArray(collection, new Enum73[collection.size()]);
            }
          break;
          case "valueFloatArray":
            // field valueFloatArray (mapped with "valueFloatArray")
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
              instance.valueFloatArray=CollectionUtils.asFloatArray(collection);
            }
          break;
          case "valueIntArray":
            // field valueIntArray (mapped with "valueIntArray")
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
              instance.setValueIntArray(CollectionUtils.asIntegerArray(collection));
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
          case "valueShortArray":
            // field valueShortArray (mapped with "valueShortArray")
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
              instance.valueShortArray=CollectionUtils.asShortArray(collection);
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
          case "valueBigDecimalArray":
            // field valueBigDecimalArray (mapped with "valueBigDecimalArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<BigDecimal> collection=new ArrayList<>();
              BigDecimal item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=BigDecimalUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigDecimalArray=CollectionUtils.asArray(collection, new BigDecimal[collection.size()]);
            }
          break;
          case "valueBigIntegerArray":
            // field valueBigIntegerArray (mapped with "valueBigIntegerArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<BigInteger> collection=new ArrayList<>();
              BigInteger item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=BigIntegerUtils.read(jacksonParser.getText());
                }
                collection.add(item);
              }
              instance.valueBigIntegerArray=CollectionUtils.asArray(collection, new BigInteger[collection.size()]);
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
  public Bean73 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean73 instance = new Bean73();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "valueBeanArray":
            // field valueBeanArray (mapped with "valueBeanArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean73> collection=new ArrayList<>();
              Bean73 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean73BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean73[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean73> collection=new ArrayList<>();
              instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean73[collection.size()]);
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
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
              instance.valueByteArray=CollectionUtils.asByteArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Byte> collection=new ArrayList<>();
              instance.valueByteArray=CollectionUtils.asByteArray(collection);
            }
          break;
          case "valueCharacterArray":
            // field valueCharacterArray (mapped with "valueCharacterArray")
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
              instance.valueCharacterArray=CollectionUtils.asCharacterArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.valueCharacterArray=CollectionUtils.asCharacterArray(collection);
            }
          break;
          case "valueDoubleArray":
            // field valueDoubleArray (mapped with "valueDoubleArray")
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
              instance.valueDoubleArray=CollectionUtils.asDoubleArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Double> collection=new ArrayList<>();
              instance.valueDoubleArray=CollectionUtils.asDoubleArray(collection);
            }
          break;
          case "valueEnumArray":
            // field valueEnumArray (mapped with "valueEnumArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Enum73> collection=new ArrayList<>();
              Enum73 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?Enum73.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumArray=CollectionUtils.asArray(collection, new Enum73[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Enum73> collection=new ArrayList<>();
              instance.valueEnumArray=CollectionUtils.asArray(collection, new Enum73[collection.size()]);
            }
          break;
          case "valueFloatArray":
            // field valueFloatArray (mapped with "valueFloatArray")
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
              instance.valueFloatArray=CollectionUtils.asFloatArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Float> collection=new ArrayList<>();
              instance.valueFloatArray=CollectionUtils.asFloatArray(collection);
            }
          break;
          case "valueIntArray":
            // field valueIntArray (mapped with "valueIntArray")
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
              instance.setValueIntArray(CollectionUtils.asIntegerArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Integer> collection=new ArrayList<>();
              instance.setValueIntArray(CollectionUtils.asIntegerArray(collection));
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
          case "valueShortArray":
            // field valueShortArray (mapped with "valueShortArray")
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
              instance.valueShortArray=CollectionUtils.asShortArray(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Short> collection=new ArrayList<>();
              instance.valueShortArray=CollectionUtils.asShortArray(collection);
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
          case "valueBigDecimalArray":
            // field valueBigDecimalArray (mapped with "valueBigDecimalArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<BigDecimal> collection=new ArrayList<>();
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
              instance.valueBigDecimalArray=CollectionUtils.asArray(collection, new BigDecimal[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<BigDecimal> collection=new ArrayList<>();
              instance.valueBigDecimalArray=CollectionUtils.asArray(collection, new BigDecimal[collection.size()]);
            }
          break;
          case "valueBigIntegerArray":
            // field valueBigIntegerArray (mapped with "valueBigIntegerArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<BigInteger> collection=new ArrayList<>();
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
              instance.valueBigIntegerArray=CollectionUtils.asArray(collection, new BigInteger[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<BigInteger> collection=new ArrayList<>();
              instance.valueBigIntegerArray=CollectionUtils.asArray(collection, new BigInteger[collection.size()]);
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
  public Bean73 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean73 instance = new Bean73();
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
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueBeanArray":
                  // property valueBeanArray (mapped on "valueBeanArray")
                   {
                    ArrayList<Bean73> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueBeanArray);
                    Bean73 item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean73BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.valueBeanArray=CollectionUtils.asArray(collection, new Bean73[collection.size()]);
                  }
                break;
                case "valueByteArray":
                  // property valueByteArray (mapped on "valueByteArray")
                   {
                    ArrayList<Byte> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueByteArray);
                    Byte item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueByteArray=CollectionUtils.asByteArray(collection);
                  }
                break;
                case "valueCharacterArray":
                  // property valueCharacterArray (mapped on "valueCharacterArray")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueCharacterArray);
                    Character item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueCharacterArray=CollectionUtils.asCharacterArray(collection);
                  }
                break;
                case "valueDoubleArray":
                  // property valueDoubleArray (mapped on "valueDoubleArray")
                   {
                    ArrayList<Double> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueDoubleArray);
                    Double item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueDoubleArray=CollectionUtils.asDoubleArray(collection);
                  }
                break;
                case "valueEnumArray":
                  // property valueEnumArray (mapped on "valueEnumArray")
                   {
                    ArrayList<Enum73> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueEnumArray);
                    Enum73 item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=Enum73.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueEnumArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=Enum73.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueEnumArray=CollectionUtils.asArray(collection, new Enum73[collection.size()]);
                    read=false;
                  }
                break;
                case "valueFloatArray":
                  // property valueFloatArray (mapped on "valueFloatArray")
                   {
                    ArrayList<Float> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueFloatArray);
                    Float item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueFloatArray=CollectionUtils.asFloatArray(collection);
                  }
                break;
                case "valueIntArray":
                  // property valueIntArray (mapped on "valueIntArray")
                   {
                    ArrayList<Integer> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValueIntArray());
                    Integer item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValueIntArray(CollectionUtils.asIntegerArray(collection));
                  }
                break;
                case "valueLongArray":
                  // property valueLongArray (mapped on "valueLongArray")
                   {
                    ArrayList<Long> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueLongArray);
                    Long item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueLongArray=CollectionUtils.asLongArray(collection);
                  }
                break;
                case "valueShortArray":
                  // property valueShortArray (mapped on "valueShortArray")
                   {
                    ArrayList<Short> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueShortArray);
                    Short item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueShortArray=CollectionUtils.asShortArray(collection);
                  }
                break;
                case "valueStringArray":
                  // property valueStringArray (mapped on "valueStringArray")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueStringArray);
                    String item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueStringArray=CollectionUtils.asArray(collection, new String[collection.size()]);
                  }
                break;
                case "valueBigDecimalArray":
                  // property valueBigDecimalArray (mapped on "valueBigDecimalArray")
                   {
                    ArrayList<BigDecimal> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueBigDecimalArray);
                    BigDecimal item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueBigDecimalArray")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueBigDecimalArray=CollectionUtils.asArray(collection, new BigDecimal[collection.size()]);
                    read=false;
                  }
                break;
                case "valueBigIntegerArray":
                  // property valueBigIntegerArray (mapped on "valueBigIntegerArray")
                   {
                    ArrayList<BigInteger> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueBigIntegerArray);
                    BigInteger item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueBigIntegerArray=CollectionUtils.asArray(collection, new BigInteger[collection.size()]);
                  }
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
