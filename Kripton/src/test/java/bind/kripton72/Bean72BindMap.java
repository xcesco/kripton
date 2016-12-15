package bind.kripton72;

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
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

/**
 * This class is the shared preference binder defined for Bean72
 *
 * @see Bean72
 */
@BindMap
public class Bean72BindMap extends AbstractMapper<Bean72> {
  /**
   * create new object instance
   */
  @Override
  public Bean72 createInstance() {
    return new Bean72();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean72 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanSet
      if (object.valueBeanSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanSet");
        jacksonSerializer.writeStartArray();
        for (Bean72 item: object.valueBeanSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            context.mapperFor(Bean72.class).serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteSet");
        jacksonSerializer.writeStartArray();
        for (Byte item: object.valueByteSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueCharacterSet
      if (object.valueCharacterSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharacterSet");
        jacksonSerializer.writeStartArray();
        for (Character item: object.valueCharacterSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueDoubleSet");
        jacksonSerializer.writeStartArray();
        for (Double item: object.valueDoubleSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (Enum72 item: object.valueEnumSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item.toString());
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueFloatSet");
        jacksonSerializer.writeStartArray();
        for (Float item: object.valueFloatSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueIntSet
      if (object.getValueIntSet()!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueIntSet");
        jacksonSerializer.writeStartArray();
        for (Integer item: object.getValueIntSet()) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueLongSet
      if (object.valueLongSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongSet");
        jacksonSerializer.writeStartArray();
        for (Long item: object.valueLongSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueShortSet
      if (object.valueShortSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueShortSet");
        jacksonSerializer.writeStartArray();
        for (Short item: object.valueShortSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueStringSet
      if (object.valueStringSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringSet");
        jacksonSerializer.writeStartArray();
        for (String item: object.valueStringSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigDecimalSet
      if (object.valueBigDecimalSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalSet");
        jacksonSerializer.writeStartArray();
        for (BigDecimal item: object.valueBigDecimalSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerSet");
        jacksonSerializer.writeStartArray();
        for (BigInteger item: object.valueBigIntegerSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigIntegerUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
      }

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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean72 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueBeanSet
      if (object.valueBeanSet!=null)  {
        fieldCount++;
        int n=object.valueBeanSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Bean72 item: object.valueBeanSet) {
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              if (context.mapperFor(Bean72.class).serializeOnJacksonAsString(context, item, wrapper)==0) {
                jacksonSerializer.writeNullField("valueBeanSet");
              }
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        fieldCount++;
        int n=object.valueByteSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Byte item: object.valueByteSet) {
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

      // field valueCharacterSet
      if (object.valueCharacterSet!=null)  {
        fieldCount++;
        int n=object.valueCharacterSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharacterSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Character item: object.valueCharacterSet) {
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

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        fieldCount++;
        int n=object.valueDoubleSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueDoubleSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Double item: object.valueDoubleSet) {
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

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        fieldCount++;
        int n=object.valueEnumSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Enum72 item: object.valueEnumSet) {
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

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        fieldCount++;
        int n=object.valueFloatSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueFloatSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Float item: object.valueFloatSet) {
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

      // field valueIntSet
      if (object.getValueIntSet()!=null)  {
        fieldCount++;
        int n=object.getValueIntSet().size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueIntSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Integer item: object.getValueIntSet()) {
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

      // field valueLongSet
      if (object.valueLongSet!=null)  {
        fieldCount++;
        int n=object.valueLongSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Long item: object.valueLongSet) {
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

      // field valueShortSet
      if (object.valueShortSet!=null)  {
        fieldCount++;
        int n=object.valueShortSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueShortSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (Short item: object.valueShortSet) {
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

      // field valueStringSet
      if (object.valueStringSet!=null)  {
        fieldCount++;
        int n=object.valueStringSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (String item: object.valueStringSet) {
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

      // field valueBigDecimalSet
      if (object.valueBigDecimalSet!=null)  {
        fieldCount++;
        int n=object.valueBigDecimalSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (BigDecimal item: object.valueBigDecimalSet) {
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

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        fieldCount++;
        int n=object.valueBigIntegerSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerSet");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (BigInteger item: object.valueBigIntegerSet) {
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

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
      }

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
  public void serializeOnXml(KriptonXmlContext context, Bean72 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean72");
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
        int n=object.valueBeanSet.size();
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBeanSet");
        for (Bean72 item: object.valueBeanSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            xmlSerializer.writeStartElement("item");
            context.mapperFor(Bean72.class).serializeOnXml(context, item, wrapper, 1);
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field valueByteSet
      if (object.valueByteSet!=null)  {
        int n=object.valueByteSet.size();
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
        int n=object.valueCharacterSet.size();
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
        int n=object.valueDoubleSet.size();
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
        int n=object.valueEnumSet.size();
        for (Enum72 item: object.valueEnumSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueEnumSet");
          } else {
            xmlSerializer.writeStartElement("valueEnumSet");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("valueEnumSet");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field valueFloatSet
      if (object.valueFloatSet!=null)  {
        int n=object.valueFloatSet.size();
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
        int n=object.getValueIntSet().size();
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
        int n=object.valueLongSet.size();
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
        int n=object.valueShortSet.size();
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
        int n=object.valueStringSet.size();
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
        int n=object.valueBigDecimalSet.size();
        for (BigDecimal item: object.valueBigDecimalSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueBigDecimalSet");
          } else {
            xmlSerializer.writeStartElement("valueBigDecimalSet");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("valueBigDecimalSet");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field valueBigIntegerSet
      if (object.valueBigIntegerSet!=null)  {
        int n=object.valueBigIntegerSet.size();
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBigIntegerSet");
        for (BigInteger item: object.valueBigIntegerSet) {
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

      // field zalueStringFinal
      if (object.zalueStringFinal!=null) {
        xmlSerializer.writeStartElement("zalueStringFinal");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.zalueStringFinal));
        xmlSerializer.writeEndElement();
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
  public Bean72 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean72 instance = createInstance();
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
                HashSet<Bean72> collection=new HashSet<>();
                Bean72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=context.mapperFor(Bean72.class).parseOnJackson(context, wrapper);
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
                    item=jacksonParser.getByteValue();
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
                    item=Character.valueOf((char)jacksonParser.getIntValue());
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
                    item=jacksonParser.getDoubleValue();
                  }
                  collection.add(item);
                }
                instance.valueDoubleSet=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Enum72> collection=new HashSet<>();
                Enum72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                     {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtils.hasText(tempEnum)?Enum72.valueOf(tempEnum):null;
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
                    item=jacksonParser.getFloatValue();
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
                    item=jacksonParser.getIntValue();
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
                    item=jacksonParser.getLongValue();
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
                    item=jacksonParser.getShortValue();
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
                    item=jacksonParser.getText();
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
                    item=BigDecimalUtils.read(jacksonParser.getText());
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
                    item=BigIntegerUtils.read(jacksonParser.getText());
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
  public Bean72 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean72 instance = createInstance();
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
                HashSet<Bean72> collection=new HashSet<>();
                Bean72 item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=context.mapperFor(Bean72.class).parseOnJacksonAsString(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.valueBeanSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Bean72> collection=new HashSet<>();
                instance.valueBeanSet=collection;
              }
            break;
            case "valueByteSet":
              // field valueByteSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Byte> collection=new HashSet<>();
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
                instance.valueByteSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Byte> collection=new HashSet<>();
                instance.valueByteSet=collection;
              }
            break;
            case "valueCharacterSet":
              // field valueCharacterSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Character> collection=new HashSet<>();
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
                instance.valueCharacterSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Character> collection=new HashSet<>();
                instance.valueCharacterSet=collection;
              }
            break;
            case "valueDoubleSet":
              // field valueDoubleSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Double> collection=new HashSet<>();
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
                instance.valueDoubleSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Double> collection=new HashSet<>();
                instance.valueDoubleSet=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Enum72> collection=new HashSet<>();
                Enum72 item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                     {
                      String tempEnum=jacksonParser.getText();
                      item=StringUtils.hasText(tempEnum)?Enum72.valueOf(tempEnum):null;
                    }
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Enum72> collection=new HashSet<>();
                instance.valueEnumSet=collection;
              }
            break;
            case "valueFloatSet":
              // field valueFloatSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Float> collection=new HashSet<>();
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
                instance.valueFloatSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Float> collection=new HashSet<>();
                instance.valueFloatSet=collection;
              }
            break;
            case "valueIntSet":
              // field valueIntSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Integer> collection=new HashSet<>();
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
                instance.setValueIntSet(collection);
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Integer> collection=new HashSet<>();
                instance.setValueIntSet(collection);
              }
            break;
            case "valueLongSet":
              // field valueLongSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Long> collection=new HashSet<>();
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
                instance.valueLongSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Long> collection=new HashSet<>();
                instance.valueLongSet=collection;
              }
            break;
            case "valueShortSet":
              // field valueShortSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<Short> collection=new HashSet<>();
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
                instance.valueShortSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<Short> collection=new HashSet<>();
                instance.valueShortSet=collection;
              }
            break;
            case "valueStringSet":
              // field valueStringSet
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
                instance.valueStringSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<String> collection=new HashSet<>();
                instance.valueStringSet=collection;
              }
            break;
            case "valueBigDecimalSet":
              // field valueBigDecimalSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigDecimal> collection=new HashSet<>();
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
                instance.valueBigDecimalSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<BigDecimal> collection=new HashSet<>();
                instance.valueBigDecimalSet=collection;
              }
            break;
            case "valueBigIntegerSet":
              // field valueBigIntegerSet
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashSet<BigInteger> collection=new HashSet<>();
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
                instance.valueBigIntegerSet=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<BigInteger> collection=new HashSet<>();
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
  public Bean72 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean72 instance = createInstance();
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
                  case "name":
                    // property name
                    instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueBeanSet":
                    // property valueBeanSet
                     {
                      HashSet<Bean72> collection=new HashSet<>();
                      Bean72 item;
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=context.mapperFor(Bean72.class).parseOnXml(context, wrapper, eventType);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueDoubleSet=collection;
                    }
                  break;
                  case "valueEnumSet":
                    // property valueEnumSet
                     {
                      HashSet<Enum72> collection=new HashSet<>();
                      Enum72 item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=Enum72.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueEnumSet")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=Enum72.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueBigDecimalSet")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=BigIntegerUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
              case XMLEventConstants.END_ELEMENT:
                if (elementName.equals(xmlParser.getName().getLocalPart())) {
                  currentTag = elementName;
                  elementName = null;
                }
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
                // no property is binded to VALUE o CDATA break;
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
