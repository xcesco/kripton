package bind.kripton71;

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
import com.abubusoft.kripton.common.PrimitiveUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement71
 *
 * @see BeanElement71
 */
@BindMap
public class BeanElement71BindMap extends AbstractMapper<BeanElement71> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement71 createInstance() {
    return new BeanElement71();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement71 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueBigIntegerList
      if (object.valueBigIntegerList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigIntegerUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteList
      if (object.valueByteList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueCharacterList
      if (object.valueCharacterList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueDoubleList
      if (object.valueDoubleList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumList
      if (object.valueEnumList!=null)  {
        int n=object.valueEnumList.size();
        BeanEnum71 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              jacksonSerializer.writeString(item.toString());
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueFloatList
      if (object.valueFloatList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueIntList
      if (object.getValueIntList()!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueLongList
      if (object.valueLongList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueShortList
      if (object.valueShortList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueStringList
      if (object.valueStringList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBeanList
      if (object.valueBeanList!=null)  {
        int n=object.valueBeanList.size();
        BeanElement71 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement71.class).serializeOnJackson(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigDecimalUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement71 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueBigIntegerList
      if (object.valueBigIntegerList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigIntegerUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteList
      if (object.valueByteList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeByte(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueCharacterList
      if (object.valueCharacterList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeCharacter(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueDoubleList
      if (object.valueDoubleList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeDouble(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumList
      if (object.valueEnumList!=null)  {
        int n=object.valueEnumList.size();
        BeanEnum71 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              jacksonSerializer.writeString(item.toString());
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueFloatList
      if (object.valueFloatList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeFloat(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueIntList
      if (object.getValueIntList()!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeInteger(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueLongList
      if (object.valueLongList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeLong(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueShortList
      if (object.valueShortList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeShort(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueStringList
      if (object.valueStringList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBeanList
      if (object.valueBeanList!=null)  {
        int n=object.valueBeanList.size();
        BeanElement71 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement71.class).serializeOnJacksonAsString(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigDecimalUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
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
  public void serializeOnXml(XmlBinderContext context, BeanElement71 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement71");
      }

      // Persisted fields:

      // field valueBigIntegerList
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
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueByteList
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

      // field valueCharacterList
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

      // field valueDoubleList
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

      // field valueEnumList
      if (object.valueEnumList!=null)  {
        int n=object.valueEnumList.size();
        BeanEnum71 item;
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
      }

      // field valueFloatList
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

      // field valueIntList
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

      // field valueLongList
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

      // field valueShortList
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

      // field valueStringList
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

      // field valueBeanList
      if (object.valueBeanList!=null)  {
        int n=object.valueBeanList.size();
        BeanElement71 item;
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBeanList");
        for (int i=0; i<n; i++) {
          item=object.valueBeanList.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            xmlSerializer.writeStartElement("item");
            context.mapperFor(BeanElement71.class).serializeOnXml(context, item, wrapper, 1);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
        int n=object.valueBigDecimalList.size();
        BigDecimal item;
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalList.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueBigDecimalList");
          } else {
            xmlSerializer.writeStartElement("valueBigDecimalList");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null) {
        xmlSerializer.writeStartElement("zalueStringFinal");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.zalueStringFinal));
        xmlSerializer.writeEndElement();
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
  public BeanElement71 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement71 instance = createInstance();
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
              // field valueBigIntegerList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigInteger> collection=new LinkedList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigIntegerUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerList=collection;
              }
            break;
            case "valueByteList":
              // field valueByteList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getByteValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueByteList=collection;
              }
            break;
            case "valueCharacterList":
              // field valueCharacterList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=Character.valueOf((char)jacksonParser.getIntValue());
                    }
                  }
                  collection.add(item);
                }
                instance.valueCharacterList=collection;
              }
            break;
            case "valueDoubleList":
              // field valueDoubleList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Double> collection=new ArrayList<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getDoubleValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueDoubleList=collection;
              }
            break;
            case "valueEnumList":
              // field valueEnumList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanEnum71> collection=new LinkedList<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtility.hasText(tempEnum)?BeanEnum71.valueOf(tempEnum):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumList=collection;
              }
            break;
            case "valueFloatList":
              // field valueFloatList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Float> collection=new ArrayList<>();
                Float item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getFloatValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueFloatList=collection;
              }
            break;
            case "valueIntList":
              // field valueIntList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Integer> collection=new ArrayList<>();
                Integer item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getIntValue();
                    }
                  }
                  collection.add(item);
                }
                instance.setValueIntList(collection);
              }
            break;
            case "valueLongList":
              // field valueLongList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Long> collection=new ArrayList<>();
                Long item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getLongValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueLongList=collection;
              }
            break;
            case "valueShortList":
              // field valueShortList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Short> collection=new ArrayList<>();
                Short item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getShortValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueShortList=collection;
              }
            break;
            case "valueStringList":
              // field valueStringList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getText();
                    }
                  }
                  collection.add(item);
                }
                instance.valueStringList=collection;
              }
            break;
            case "valueBeanList":
              // field valueBeanList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanElement71> collection=new LinkedList<>();
                BeanElement71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                      item=context.mapperFor(BeanElement71.class).parseOnJackson(context, wrapper);
                    }
                  }
                  collection.add(item);
                }
                instance.valueBeanList=collection;
              }
            break;
            case "valueBigDecimalList":
              // field valueBigDecimalList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigDecimal> collection=new LinkedList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigDecimalUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalList=collection;
              }
            break;
            case "zalueStringFinal":
              // field zalueStringFinal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.zalueStringFinal=jacksonParser.getText();
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
  public BeanElement71 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement71 instance = createInstance();
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
              // field valueBigIntegerList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigInteger> collection=new LinkedList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigIntegerUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerList=collection;
              }
            break;
            case "valueByteList":
              // field valueByteList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readByte(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueByteList=collection;
              }
            break;
            case "valueCharacterList":
              // field valueCharacterList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readCharacter(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueCharacterList=collection;
              }
            break;
            case "valueDoubleList":
              // field valueDoubleList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Double> collection=new ArrayList<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readDouble(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueDoubleList=collection;
              }
            break;
            case "valueEnumList":
              // field valueEnumList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanEnum71> collection=new LinkedList<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtility.hasText(tempEnum)?BeanEnum71.valueOf(tempEnum):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumList=collection;
              }
            break;
            case "valueFloatList":
              // field valueFloatList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Float> collection=new ArrayList<>();
                Float item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readFloat(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueFloatList=collection;
              }
            break;
            case "valueIntList":
              // field valueIntList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Integer> collection=new ArrayList<>();
                Integer item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.setValueIntList(collection);
              }
            break;
            case "valueLongList":
              // field valueLongList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Long> collection=new ArrayList<>();
                Long item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readLong(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueLongList=collection;
              }
            break;
            case "valueShortList":
              // field valueShortList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Short> collection=new ArrayList<>();
                Short item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readShort(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueShortList=collection;
              }
            break;
            case "valueStringList":
              // field valueStringList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getText();
                    }
                  }
                  collection.add(item);
                }
                instance.valueStringList=collection;
              }
            break;
            case "valueBeanList":
              // field valueBeanList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanElement71> collection=new LinkedList<>();
                BeanElement71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                      item=context.mapperFor(BeanElement71.class).parseOnJacksonAsString(context, wrapper);
                    }
                  }
                  collection.add(item);
                }
                instance.valueBeanList=collection;
              }
            break;
            case "valueBigDecimalList":
              // field valueBigDecimalList
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigDecimal> collection=new LinkedList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigDecimalUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalList=collection;
              }
            break;
            case "zalueStringFinal":
              // field zalueStringFinal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.zalueStringFinal=jacksonParser.getText();
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
  public BeanElement71 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement71 instance = createInstance();
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
      // No attributes found

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
              switch(currentTag) {
                  case "valueBigIntegerList":
                    // property valueBigIntegerList
                     {
                      LinkedList<BigInteger> collection=new LinkedList<>();
                      BigInteger item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigIntegerUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueBigIntegerList=collection;
                    }
                  break;
                  case "valueByteList":
                    // property valueByteList
                     {
                      ArrayList<Byte> collection=new ArrayList<>();
                      Byte item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(byte)PrimitiveUtil.readByte(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueByteList=collection;
                    }
                  break;
                  case "valueCharacterList":
                    // property valueCharacterList
                     {
                      ArrayList<Character> collection=new ArrayList<>();
                      Character item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(char)PrimitiveUtil.readCharacter(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueCharacterList=collection;
                    }
                  break;
                  case "valueDoubleList":
                    // property valueDoubleList
                     {
                      ArrayList<Double> collection=new ArrayList<>();
                      Double item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtil.readDouble(xmlParser.getElementAsDouble(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueDoubleList=collection;
                    }
                  break;
                  case "valueEnumList":
                    // property valueEnumList
                     {
                      LinkedList<BeanEnum71> collection=new LinkedList<>();
                      BeanEnum71 item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BeanEnum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueEnumList")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BeanEnum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueEnumList=collection;
                      read=false;
                    }
                  break;
                  case "valueFloatList":
                    // property valueFloatList
                     {
                      ArrayList<Float> collection=new ArrayList<>();
                      Float item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtil.readFloat(xmlParser.getElementAsFloat(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueFloatList=collection;
                    }
                  break;
                  case "valueIntList":
                    // property valueIntList
                     {
                      ArrayList<Integer> collection=new ArrayList<>();
                      Integer item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtil.readInteger(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.setValueIntList(collection);
                    }
                  break;
                  case "valueLongList":
                    // property valueLongList
                     {
                      ArrayList<Long> collection=new ArrayList<>();
                      Long item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtil.readLong(xmlParser.getElementAsLong(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueLongList=collection;
                    }
                  break;
                  case "valueShortList":
                    // property valueShortList
                     {
                      ArrayList<Short> collection=new ArrayList<>();
                      Short item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(short)PrimitiveUtil.readShort(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueShortList=collection;
                    }
                  break;
                  case "valueStringList":
                    // property valueStringList
                     {
                      ArrayList<String> collection=new ArrayList<>();
                      String item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
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
                    // property valueBeanList
                     {
                      LinkedList<BeanElement71> collection=new LinkedList<>();
                      BeanElement71 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=context.mapperFor(BeanElement71.class).parseOnXml(context, wrapper, eventType);
                        }
                        collection.add(item);
                      }
                      instance.valueBeanList=collection;
                    }
                  break;
                  case "valueBigDecimalList":
                    // property valueBigDecimalList
                     {
                      LinkedList<BigDecimal> collection=new LinkedList<>();
                      BigDecimal item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueBigDecimalList")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueBigDecimalList=collection;
                      read=false;
                    }
                  break;
                  case "zalueStringFinal":
                    // property zalueStringFinal
                    instance.zalueStringFinal=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
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
