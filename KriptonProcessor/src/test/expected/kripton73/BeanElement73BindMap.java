package kripton73;

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
import com.abubusoft.kripton.common.CollectionUtility;
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
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement73
 *
 * @see BeanElement73
 */
@BindMap
public class BeanElement73BindMap extends AbstractMapper<BeanElement73> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement73 createInstance() {
    return new BeanElement73();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement73 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanArray
      if (object.valueBeanArray!=null)  {
        int n=object.valueBeanArray.length;
        BeanElement73 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanArray[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement73.class).serializeOnJackson(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueCharacterArray
      if (object.valueCharacterArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueDoubleArray
      if (object.valueDoubleArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumArray
      if (object.valueEnumArray!=null)  {
        int n=object.valueEnumArray.length;
        BeanEnum73 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumArray[i];
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

      // field valueFloatArray
      if (object.valueFloatArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueIntArray
      if (object.getValueIntArray()!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueLongArray
      if (object.valueLongArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueShortArray
      if (object.valueShortArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeNumber(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueStringArray
      if (object.valueStringArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigDecimalArray
      if (object.valueBigDecimalArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigDecimalUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigIntegerArray
      if (object.valueBigIntegerArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigIntegerUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement73 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanArray
      if (object.valueBeanArray!=null)  {
        int n=object.valueBeanArray.length;
        BeanElement73 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBeanArray[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement73.class).serializeOnJacksonAsString(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeByte(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueCharacterArray
      if (object.valueCharacterArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeCharacter(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueDoubleArray
      if (object.valueDoubleArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeDouble(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumArray
      if (object.valueEnumArray!=null)  {
        int n=object.valueEnumArray.length;
        BeanEnum73 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueEnumArray[i];
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

      // field valueFloatArray
      if (object.valueFloatArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeFloat(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueIntArray
      if (object.getValueIntArray()!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeInteger(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueLongArray
      if (object.valueLongArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeLong(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueShortArray
      if (object.valueShortArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(PrimitiveUtil.writeShort(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueStringArray
      if (object.valueStringArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(item);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigDecimalArray
      if (object.valueBigDecimalArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigDecimalUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigIntegerArray
      if (object.valueBigIntegerArray!=null)  {
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
            if (item!=null)  {
              jacksonSerializer.writeString(BigIntegerUtil.write(item));
            }
          }
        }
        jacksonSerializer.writeEndArray();
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
  public void serializeOnXml(XmlBinderContext context, BeanElement73 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement73");
      }

      // Persisted fields:

      // field name
      if (object.name!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
        xmlSerializer.writeEndElement();
      }

      // field valueBeanArray
      if (object.valueBeanArray!=null)  {
        int n=object.valueBeanArray.length;
        BeanElement73 item;
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBeanArray");
        for (int i=0; i<n; i++) {
          item=object.valueBeanArray[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            xmlSerializer.writeStartElement("valueBeanArray");
            context.mapperFor(BeanElement73.class).serializeOnXml(context, item, wrapper, 1);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueByteArray
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
            xmlSerializer.writeStartElement("valueByteArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueCharacterArray
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
            xmlSerializer.writeStartElement("valueCharacterArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueDoubleArray
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
            xmlSerializer.writeStartElement("valueDoubleArray");
            xmlSerializer.writeDouble(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueEnumArray
      if (object.valueEnumArray!=null)  {
        int n=object.valueEnumArray.length;
        BeanEnum73 item;
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
      }

      // field valueFloatArray
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
            xmlSerializer.writeStartElement("valueFloatArray");
            xmlSerializer.writeFloat(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueIntArray
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
            xmlSerializer.writeStartElement("valueIntArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueLongArray
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
            xmlSerializer.writeStartElement("valueLongArray");
            xmlSerializer.writeLong(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueShortArray
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
            xmlSerializer.writeStartElement("valueShortArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueStringArray
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
            xmlSerializer.writeStartElement("valueStringArray");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueBigDecimalArray
      if (object.valueBigDecimalArray!=null)  {
        int n=object.valueBigDecimalArray.length;
        BigDecimal item;
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalArray[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueBigDecimalArray");
          } else {
            xmlSerializer.writeStartElement("valueBigDecimalArray");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueBigIntegerArray
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
            xmlSerializer.writeStartElement("valueBigIntegerArray");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
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
  public BeanElement73 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement73 instance = createInstance();
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
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "valueBeanArray":
              // field valueBeanArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanElement73> collection=new ArrayList<>();
                BeanElement73 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=context.mapperFor(BeanElement73.class).parseOnJackson(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.valueBeanArray=CollectionUtility.asArray(collection, new BeanElement73[collection.size()]);
              }
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getByteValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueByteArray=CollectionUtility.asByteArray(collection);
              }
            break;
            case "valueCharacterArray":
              // field valueCharacterArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=Character.valueOf((char)jacksonParser.getIntValue());
                    }
                  }
                  collection.add(item);
                }
                instance.valueCharacterArray=CollectionUtility.asCharacterArray(collection);
              }
            break;
            case "valueDoubleArray":
              // field valueDoubleArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Double> collection=new ArrayList<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getDoubleValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueDoubleArray=CollectionUtility.asDoubleArray(collection);
              }
            break;
            case "valueEnumArray":
              // field valueEnumArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanEnum73> collection=new ArrayList<>();
                BeanEnum73 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=StringUtility.hasText(jacksonParser.getText())?BeanEnum73.valueOf(jacksonParser.getText()):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumArray=CollectionUtility.asArray(collection, new BeanEnum73[collection.size()]);
              }
            break;
            case "valueFloatArray":
              // field valueFloatArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Float> collection=new ArrayList<>();
                Float item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getFloatValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueFloatArray=CollectionUtility.asFloatArray(collection);
              }
            break;
            case "valueIntArray":
              // field valueIntArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Integer> collection=new ArrayList<>();
                Integer item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getIntValue();
                    }
                  }
                  collection.add(item);
                }
                instance.setValueIntArray(CollectionUtility.asIntegerArray(collection));
              }
            break;
            case "valueLongArray":
              // field valueLongArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Long> collection=new ArrayList<>();
                Long item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getLongValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueLongArray=CollectionUtility.asLongArray(collection);
              }
            break;
            case "valueShortArray":
              // field valueShortArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Short> collection=new ArrayList<>();
                Short item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getShortValue();
                    }
                  }
                  collection.add(item);
                }
                instance.valueShortArray=CollectionUtility.asShortArray(collection);
              }
            break;
            case "valueStringArray":
              // field valueStringArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getText();
                    }
                  }
                  collection.add(item);
                }
                instance.valueStringArray=CollectionUtility.asArray(collection, new String[collection.size()]);
              }
            break;
            case "valueBigDecimalArray":
              // field valueBigDecimalArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BigDecimal> collection=new ArrayList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigDecimalUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalArray=CollectionUtility.asArray(collection, new BigDecimal[collection.size()]);
              }
            break;
            case "valueBigIntegerArray":
              // field valueBigIntegerArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BigInteger> collection=new ArrayList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigIntegerUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerArray=CollectionUtility.asArray(collection, new BigInteger[collection.size()]);
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
  public BeanElement73 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement73 instance = createInstance();
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
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "valueBeanArray":
              // field valueBeanArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanElement73> collection=new ArrayList<>();
                BeanElement73 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=context.mapperFor(BeanElement73.class).parseOnJacksonAsString(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.valueBeanArray=CollectionUtility.asArray(collection, new BeanElement73[collection.size()]);
              }
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readByte(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueByteArray=CollectionUtility.asByteArray(collection);
              }
            break;
            case "valueCharacterArray":
              // field valueCharacterArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readCharacter(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueCharacterArray=CollectionUtility.asCharacterArray(collection);
              }
            break;
            case "valueDoubleArray":
              // field valueDoubleArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Double> collection=new ArrayList<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readDouble(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueDoubleArray=CollectionUtility.asDoubleArray(collection);
              }
            break;
            case "valueEnumArray":
              // field valueEnumArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanEnum73> collection=new ArrayList<>();
                BeanEnum73 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=StringUtility.hasText(jacksonParser.getText())?BeanEnum73.valueOf(jacksonParser.getText()):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumArray=CollectionUtility.asArray(collection, new BeanEnum73[collection.size()]);
              }
            break;
            case "valueFloatArray":
              // field valueFloatArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Float> collection=new ArrayList<>();
                Float item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readFloat(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueFloatArray=CollectionUtility.asFloatArray(collection);
              }
            break;
            case "valueIntArray":
              // field valueIntArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Integer> collection=new ArrayList<>();
                Integer item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.setValueIntArray(CollectionUtility.asIntegerArray(collection));
              }
            break;
            case "valueLongArray":
              // field valueLongArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Long> collection=new ArrayList<>();
                Long item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readLong(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueLongArray=CollectionUtility.asLongArray(collection);
              }
            break;
            case "valueShortArray":
              // field valueShortArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<Short> collection=new ArrayList<>();
                Short item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=PrimitiveUtil.readShort(jacksonParser.getText(), null);
                    }
                  }
                  collection.add(item);
                }
                instance.valueShortArray=CollectionUtility.asShortArray(collection);
              }
            break;
            case "valueStringArray":
              // field valueStringArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getText();
                    }
                  }
                  collection.add(item);
                }
                instance.valueStringArray=CollectionUtility.asArray(collection, new String[collection.size()]);
              }
            break;
            case "valueBigDecimalArray":
              // field valueBigDecimalArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BigDecimal> collection=new ArrayList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigDecimalUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalArray=CollectionUtility.asArray(collection, new BigDecimal[collection.size()]);
              }
            break;
            case "valueBigIntegerArray":
              // field valueBigIntegerArray
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<BigInteger> collection=new ArrayList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=BigIntegerUtil.read(jacksonParser.getText());
                    }
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerArray=CollectionUtility.asArray(collection, new BigInteger[collection.size()]);
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
  public BeanElement73 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement73 instance = createInstance();
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
                  case "name":
                    // property name
                    instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueBeanArray":
                    // property valueBeanArray
                     {
                      ArrayList<BeanElement73> collection=new ArrayList<>();
                      BeanElement73 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=context.mapperFor(BeanElement73.class).parseOnXml(context, wrapper, eventType);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueBeanArray=CollectionUtility.asArray(collection, new BeanElement73[collection.size()]);
                    }
                  break;
                  case "valueByteArray":
                    // property valueByteArray
                     {
                      ArrayList<Byte> collection=new ArrayList<>();
                      Byte item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=(byte)PrimitiveUtil.readByte(xmlParser.getElementAsInt(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueByteArray=CollectionUtility.asByteArray(collection);
                    }
                  break;
                  case "valueCharacterArray":
                    // property valueCharacterArray
                     {
                      ArrayList<Character> collection=new ArrayList<>();
                      Character item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=(char)PrimitiveUtil.readCharacter(xmlParser.getElementAsInt(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueCharacterArray=CollectionUtility.asCharacterArray(collection);
                    }
                  break;
                  case "valueDoubleArray":
                    // property valueDoubleArray
                     {
                      ArrayList<Double> collection=new ArrayList<>();
                      Double item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=PrimitiveUtil.readDouble(xmlParser.getElementAsDouble(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueDoubleArray=CollectionUtility.asDoubleArray(collection);
                    }
                  break;
                  case "valueEnumArray":
                    // property valueEnumArray
                     {
                      ArrayList<BeanEnum73> collection=new ArrayList<>();
                      BeanEnum73 item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BeanEnum73.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueEnumArray")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BeanEnum73.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueEnumArray=CollectionUtility.asArray(collection, new BeanEnum73[collection.size()]);
                      read=false;
                    }
                  break;
                  case "valueFloatArray":
                    // property valueFloatArray
                     {
                      ArrayList<Float> collection=new ArrayList<>();
                      Float item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=PrimitiveUtil.readFloat(xmlParser.getElementAsFloat(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueFloatArray=CollectionUtility.asFloatArray(collection);
                    }
                  break;
                  case "valueIntArray":
                    // property valueIntArray
                     {
                      ArrayList<Integer> collection=new ArrayList<>();
                      Integer item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=PrimitiveUtil.readInteger(xmlParser.getElementAsInt(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.setValueIntArray(CollectionUtility.asIntegerArray(collection));
                    }
                  break;
                  case "valueLongArray":
                    // property valueLongArray
                     {
                      ArrayList<Long> collection=new ArrayList<>();
                      Long item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=PrimitiveUtil.readLong(xmlParser.getElementAsLong(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueLongArray=CollectionUtility.asLongArray(collection);
                    }
                  break;
                  case "valueShortArray":
                    // property valueShortArray
                     {
                      ArrayList<Short> collection=new ArrayList<>();
                      Short item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=(short)PrimitiveUtil.readShort(xmlParser.getElementAsInt(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueShortArray=CollectionUtility.asShortArray(collection);
                    }
                  break;
                  case "valueStringArray":
                    // property valueStringArray
                     {
                      ArrayList<String> collection=new ArrayList<>();
                      String item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueStringArray=CollectionUtility.asArray(collection, new String[collection.size()]);
                    }
                  break;
                  case "valueBigDecimalArray":
                    // property valueBigDecimalArray
                     {
                      ArrayList<BigDecimal> collection=new ArrayList<>();
                      BigDecimal item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueBigDecimalArray")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueBigDecimalArray=CollectionUtility.asArray(collection, new BigDecimal[collection.size()]);
                      read=false;
                    }
                  break;
                  case "valueBigIntegerArray":
                    // property valueBigIntegerArray
                     {
                      ArrayList<BigInteger> collection=new ArrayList<>();
                      BigInteger item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.nextTag();
                          } else {
                            item=BigIntegerUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueBigIntegerArray=CollectionUtility.asArray(collection, new BigInteger[collection.size()]);
                    }
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
