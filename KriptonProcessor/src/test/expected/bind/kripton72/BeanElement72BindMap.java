package bind.kripton72;

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
import java.util.HashSet;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement72
 *
 * @see BeanElement72
 */
@BindMap
public class BeanElement72BindMap extends AbstractMapper<BeanElement72> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement72 createInstance() {
    return new BeanElement72();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement72 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanSet
      if (object.valueBeanSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanSet");
        jacksonSerializer.writeStartArray();
        for (BeanElement72 item: object.valueBeanSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement72.class).serializeOnJackson(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteSet");
        jacksonSerializer.writeStartArray();
        for (Byte item: object.valueByteSet) {
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

      // field valueCharacterSet
      if (object.valueCharacterSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharacterSet");
        jacksonSerializer.writeStartArray();
        for (Character item: object.valueCharacterSet) {
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

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueDoubleSet");
        jacksonSerializer.writeStartArray();
        for (Double item: object.valueDoubleSet) {
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

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (BeanEnum72 item: object.valueEnumSet) {
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

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueFloatSet");
        jacksonSerializer.writeStartArray();
        for (Float item: object.valueFloatSet) {
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

      // field valueIntSet
      if (object.getValueIntSet()!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueIntSet");
        jacksonSerializer.writeStartArray();
        for (Integer item: object.getValueIntSet()) {
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

      // field valueLongSet
      if (object.valueLongSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongSet");
        jacksonSerializer.writeStartArray();
        for (Long item: object.valueLongSet) {
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

      // field valueShortSet
      if (object.valueShortSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueShortSet");
        jacksonSerializer.writeStartArray();
        for (Short item: object.valueShortSet) {
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

      // field valueStringSet
      if (object.valueStringSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringSet");
        jacksonSerializer.writeStartArray();
        for (String item: object.valueStringSet) {
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

      // field valueBigDecimalSet
      if (object.valueBigDecimalSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalSet");
        jacksonSerializer.writeStartArray();
        for (BigDecimal item: object.valueBigDecimalSet) {
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

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerSet");
        jacksonSerializer.writeStartArray();
        for (BigInteger item: object.valueBigIntegerSet) {
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement72 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanSet
      if (object.valueBeanSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanSet");
        jacksonSerializer.writeStartArray();
        for (BeanElement72 item: object.valueBeanSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            if (item!=null)  {
              context.mapperFor(BeanElement72.class).serializeOnJacksonAsString(context, item, wrapper);
            }
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteSet");
        jacksonSerializer.writeStartArray();
        for (Byte item: object.valueByteSet) {
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

      // field valueCharacterSet
      if (object.valueCharacterSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharacterSet");
        jacksonSerializer.writeStartArray();
        for (Character item: object.valueCharacterSet) {
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

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueDoubleSet");
        jacksonSerializer.writeStartArray();
        for (Double item: object.valueDoubleSet) {
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

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (BeanEnum72 item: object.valueEnumSet) {
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

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueFloatSet");
        jacksonSerializer.writeStartArray();
        for (Float item: object.valueFloatSet) {
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

      // field valueIntSet
      if (object.getValueIntSet()!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueIntSet");
        jacksonSerializer.writeStartArray();
        for (Integer item: object.getValueIntSet()) {
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

      // field valueLongSet
      if (object.valueLongSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongSet");
        jacksonSerializer.writeStartArray();
        for (Long item: object.valueLongSet) {
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

      // field valueShortSet
      if (object.valueShortSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueShortSet");
        jacksonSerializer.writeStartArray();
        for (Short item: object.valueShortSet) {
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

      // field valueStringSet
      if (object.valueStringSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringSet");
        jacksonSerializer.writeStartArray();
        for (String item: object.valueStringSet) {
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

      // field valueBigDecimalSet
      if (object.valueBigDecimalSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalSet");
        jacksonSerializer.writeStartArray();
        for (BigDecimal item: object.valueBigDecimalSet) {
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

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerSet");
        jacksonSerializer.writeStartArray();
        for (BigInteger item: object.valueBigIntegerSet) {
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
  public void serializeOnXml(XmlBinderContext context, BeanElement72 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement72");
      }

      // Persisted fields:

      // field name
      if (object.name!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
        xmlSerializer.writeEndElement();
      }

      // field valueBeanSet
      if (object.valueBeanSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBeanSet");
        for (BeanElement72 item: object.valueBeanSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            xmlSerializer.writeStartElement("item");
            context.mapperFor(BeanElement72.class).serializeOnXml(context, item, wrapper, 1);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueByteSet");
        for (Byte item: object.valueByteSet) {
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

      // field valueCharacterSet
      if (object.valueCharacterSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueCharacterSet");
        for (Character item: object.valueCharacterSet) {
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

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueDoubleSet");
        for (Double item: object.valueDoubleSet) {
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

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        for (BeanEnum72 item: object.valueEnumSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueEnumSet");
          } else {
            xmlSerializer.writeStartElement("valueEnumSet");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueFloatSet");
        for (Float item: object.valueFloatSet) {
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

      // field valueIntSet
      if (object.getValueIntSet()!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueIntSet");
        for (Integer item: object.getValueIntSet()) {
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

      // field valueLongSet
      if (object.valueLongSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueLongSet");
        for (Long item: object.valueLongSet) {
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

      // field valueShortSet
      if (object.valueShortSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueShortSet");
        for (Short item: object.valueShortSet) {
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

      // field valueStringSet
      if (object.valueStringSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueStringSet");
        for (String item: object.valueStringSet) {
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

      // field valueBigDecimalSet
      if (object.valueBigDecimalSet!=null)  {
        for (BigDecimal item: object.valueBigDecimalSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueBigDecimalSet");
          } else {
            xmlSerializer.writeStartElement("valueBigDecimalSet");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBigIntegerSet");
        for (BigInteger item: object.valueBigIntegerSet) {
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
  public BeanElement72 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement72 instance = createInstance();
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
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "valueBeanSet":
              // field valueBeanSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanElement72> collection=new HashSet<>();
                BeanElement72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                      item=context.mapperFor(BeanElement72.class).parseOnJackson(context, wrapper);
                    }
                  }
                  collection.add(item);
                }
                instance.valueBeanSet=collection;
              }
            break;
            case "valueByteSet":
              // field valueByteSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Byte> collection=new HashSet<>();
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
                instance.valueByteSet=collection;
              }
            break;
            case "valueCharacterSet":
              // field valueCharacterSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Character> collection=new HashSet<>();
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
                instance.valueCharacterSet=collection;
              }
            break;
            case "valueDoubleSet":
              // field valueDoubleSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Double> collection=new HashSet<>();
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
                instance.valueDoubleSet=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanEnum72> collection=new HashSet<>();
                BeanEnum72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtility.hasText(tempEnum)?BeanEnum72.valueOf(tempEnum):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueFloatSet":
              // field valueFloatSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Float> collection=new HashSet<>();
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
                instance.valueFloatSet=collection;
              }
            break;
            case "valueIntSet":
              // field valueIntSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Integer> collection=new HashSet<>();
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
                instance.setValueIntSet(collection);
              }
            break;
            case "valueLongSet":
              // field valueLongSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Long> collection=new HashSet<>();
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
                instance.valueLongSet=collection;
              }
            break;
            case "valueShortSet":
              // field valueShortSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Short> collection=new HashSet<>();
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
                instance.valueShortSet=collection;
              }
            break;
            case "valueStringSet":
              // field valueStringSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<String> collection=new HashSet<>();
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
                instance.valueStringSet=collection;
              }
            break;
            case "valueBigDecimalSet":
              // field valueBigDecimalSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigDecimal> collection=new HashSet<>();
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
                instance.valueBigDecimalSet=collection;
              }
            break;
            case "valueBigIntegerSet":
              // field valueBigIntegerSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigInteger> collection=new HashSet<>();
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
                instance.valueBigIntegerSet=collection;
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
  public BeanElement72 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement72 instance = createInstance();
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
            case "valueBeanSet":
              // field valueBeanSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanElement72> collection=new HashSet<>();
                BeanElement72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                      item=context.mapperFor(BeanElement72.class).parseOnJacksonAsString(context, wrapper);
                    }
                  }
                  collection.add(item);
                }
                instance.valueBeanSet=collection;
              }
            break;
            case "valueByteSet":
              // field valueByteSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Byte> collection=new HashSet<>();
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
                instance.valueByteSet=collection;
              }
            break;
            case "valueCharacterSet":
              // field valueCharacterSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Character> collection=new HashSet<>();
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
                instance.valueCharacterSet=collection;
              }
            break;
            case "valueDoubleSet":
              // field valueDoubleSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Double> collection=new HashSet<>();
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
                instance.valueDoubleSet=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanEnum72> collection=new HashSet<>();
                BeanEnum72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtility.hasText(tempEnum)?BeanEnum72.valueOf(tempEnum):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueFloatSet":
              // field valueFloatSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Float> collection=new HashSet<>();
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
                instance.valueFloatSet=collection;
              }
            break;
            case "valueIntSet":
              // field valueIntSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Integer> collection=new HashSet<>();
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
                instance.setValueIntSet(collection);
              }
            break;
            case "valueLongSet":
              // field valueLongSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Long> collection=new HashSet<>();
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
                instance.valueLongSet=collection;
              }
            break;
            case "valueShortSet":
              // field valueShortSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Short> collection=new HashSet<>();
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
                instance.valueShortSet=collection;
              }
            break;
            case "valueStringSet":
              // field valueStringSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<String> collection=new HashSet<>();
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
                instance.valueStringSet=collection;
              }
            break;
            case "valueBigDecimalSet":
              // field valueBigDecimalSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigDecimal> collection=new HashSet<>();
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
                instance.valueBigDecimalSet=collection;
              }
            break;
            case "valueBigIntegerSet":
              // field valueBigIntegerSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigInteger> collection=new HashSet<>();
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
                instance.valueBigIntegerSet=collection;
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
  public BeanElement72 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement72 instance = createInstance();
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
                  case "valueBeanSet":
                    // property valueBeanSet
                     {
                      HashSet<BeanElement72> collection=new HashSet<>();
                      BeanElement72 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=context.mapperFor(BeanElement72.class).parseOnXml(context, wrapper, eventType);
                        }
                        collection.add(item);
                      }
                      instance.valueBeanSet=collection;
                    }
                  break;
                  case "valueByteSet":
                    // property valueByteSet
                     {
                      HashSet<Byte> collection=new HashSet<>();
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
                      instance.valueByteSet=collection;
                    }
                  break;
                  case "valueCharacterSet":
                    // property valueCharacterSet
                     {
                      HashSet<Character> collection=new HashSet<>();
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
                      instance.valueCharacterSet=collection;
                    }
                  break;
                  case "valueDoubleSet":
                    // property valueDoubleSet
                     {
                      HashSet<Double> collection=new HashSet<>();
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
                      instance.valueDoubleSet=collection;
                    }
                  break;
                  case "valueEnumSet":
                    // property valueEnumSet
                     {
                      HashSet<BeanEnum72> collection=new HashSet<>();
                      BeanEnum72 item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BeanEnum72.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueEnumSet")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BeanEnum72.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueEnumSet=collection;
                      read=false;
                    }
                  break;
                  case "valueFloatSet":
                    // property valueFloatSet
                     {
                      HashSet<Float> collection=new HashSet<>();
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
                      instance.valueFloatSet=collection;
                    }
                  break;
                  case "valueIntSet":
                    // property valueIntSet
                     {
                      HashSet<Integer> collection=new HashSet<>();
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
                      instance.setValueIntSet(collection);
                    }
                  break;
                  case "valueLongSet":
                    // property valueLongSet
                     {
                      HashSet<Long> collection=new HashSet<>();
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
                      instance.valueLongSet=collection;
                    }
                  break;
                  case "valueShortSet":
                    // property valueShortSet
                     {
                      HashSet<Short> collection=new HashSet<>();
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
                      instance.valueShortSet=collection;
                    }
                  break;
                  case "valueStringSet":
                    // property valueStringSet
                     {
                      HashSet<String> collection=new HashSet<>();
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
                      instance.valueStringSet=collection;
                    }
                  break;
                  case "valueBigDecimalSet":
                    // property valueBigDecimalSet
                     {
                      HashSet<BigDecimal> collection=new HashSet<>();
                      BigDecimal item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueBigDecimalSet")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        collection.add(item);
                      }
                      instance.valueBigDecimalSet=collection;
                      read=false;
                    }
                  break;
                  case "valueBigIntegerSet":
                    // property valueBigIntegerSet
                     {
                      HashSet<BigInteger> collection=new HashSet<>();
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
                      instance.valueBigIntegerSet=collection;
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
