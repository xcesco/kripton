package sqlite.kripton62;

import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * This class is the shared preference binder defined for Bean
 *
 * @see Bean
 */
@BindMap(Bean.class)
public class BeanBindMap extends AbstractMapper<Bean> {
  /**
   * BeanBindMap */
  private BeanBindMap beanBindMap;

  private BeanBindMap beanBindMap() {
    if (beanBindMap==null) {
      beanBindMap=AbstractContext.mapperFor(Bean.class);
    }
    return beanBindMap;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(Bean object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueBeanSet (mapped with "valueBeanSet")
    if (object.valueBeanSet!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanSet");
      jacksonSerializer.writeStartArray();
      for (Bean item: object.valueBeanSet) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          beanBindMap().serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueBigDecimalSet (mapped with "valueBigDecimalSet")
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

    // field valueByteSet (mapped with "valueByteSet")
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

    // field valueCharacterSet (mapped with "valueCharacterSet")
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

    // field valueDoubleSet (mapped with "valueDoubleSet")
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

    // field valueEnumTypeSet (mapped with "valueEnumTypeSet")
    if (object.valueEnumTypeSet!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumTypeSet");
      jacksonSerializer.writeStartArray();
      for (EnumType item: object.valueEnumTypeSet) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item.toString());
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueFloatSet (mapped with "valueFloatSet")
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

    // field valueIntegerSet (mapped with "valueIntegerSet")
    if (object.valueIntegerSet!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntegerSet");
      jacksonSerializer.writeStartArray();
      for (Integer item: object.valueIntegerSet) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field valueShortSet (mapped with "valueShortSet")
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

    // field valueStringSet (mapped with "valueStringSet")
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(Bean object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueBeanSet (mapped with "valueBeanSet")
    if (object.valueBeanSet!=null)  {
      fieldCount++;
      int n=object.valueBeanSet.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueBeanSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (Bean item: object.valueBeanSet) {
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (beanBindMap().serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("valueBeanSet");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field valueBigDecimalSet (mapped with "valueBigDecimalSet")
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

    // field valueByteSet (mapped with "valueByteSet")
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

    // field valueCharacterSet (mapped with "valueCharacterSet")
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

    // field valueDoubleSet (mapped with "valueDoubleSet")
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

    // field valueEnumTypeSet (mapped with "valueEnumTypeSet")
    if (object.valueEnumTypeSet!=null)  {
      fieldCount++;
      int n=object.valueEnumTypeSet.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueEnumTypeSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (EnumType item: object.valueEnumTypeSet) {
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

    // field valueFloatSet (mapped with "valueFloatSet")
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

    // field valueIntegerSet (mapped with "valueIntegerSet")
    if (object.valueIntegerSet!=null)  {
      fieldCount++;
      int n=object.valueIntegerSet.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueIntegerSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (Integer item: object.valueIntegerSet) {
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

    // field valueShortSet (mapped with "valueShortSet")
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

    // field valueStringSet (mapped with "valueStringSet")
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(Bean object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueBeanSet (mapped with "valueBeanSet")
    if (object.valueBeanSet!=null)  {
      int n=object.valueBeanSet.size();
      for (Bean item: object.valueBeanSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueBeanSet");
        } else {
          xmlSerializer.writeStartElement("valueBeanSet");
          beanBindMap().serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueBeanSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueBigDecimalSet (mapped with "valueBigDecimalSet")
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

    // field valueByteSet (mapped with "valueByteSet")
    if (object.valueByteSet!=null)  {
      int n=object.valueByteSet.size();
      for (Byte item: object.valueByteSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueByteSet");
        } else {
          xmlSerializer.writeStartElement("valueByteSet");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueByteSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueCharacterSet (mapped with "valueCharacterSet")
    if (object.valueCharacterSet!=null)  {
      int n=object.valueCharacterSet.size();
      for (Character item: object.valueCharacterSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueCharacterSet");
        } else {
          xmlSerializer.writeStartElement("valueCharacterSet");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueCharacterSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueDoubleSet (mapped with "valueDoubleSet")
    if (object.valueDoubleSet!=null)  {
      int n=object.valueDoubleSet.size();
      for (Double item: object.valueDoubleSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueDoubleSet");
        } else {
          xmlSerializer.writeStartElement("valueDoubleSet");
          xmlSerializer.writeDouble(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueDoubleSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueEnumTypeSet (mapped with "valueEnumTypeSet")
    if (object.valueEnumTypeSet!=null)  {
      int n=object.valueEnumTypeSet.size();
      for (EnumType item: object.valueEnumTypeSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueEnumTypeSet");
        } else {
          xmlSerializer.writeStartElement("valueEnumTypeSet");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueEnumTypeSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueFloatSet (mapped with "valueFloatSet")
    if (object.valueFloatSet!=null)  {
      int n=object.valueFloatSet.size();
      for (Float item: object.valueFloatSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueFloatSet");
        } else {
          xmlSerializer.writeStartElement("valueFloatSet");
          xmlSerializer.writeFloat(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueFloatSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueIntegerSet (mapped with "valueIntegerSet")
    if (object.valueIntegerSet!=null)  {
      int n=object.valueIntegerSet.size();
      for (Integer item: object.valueIntegerSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueIntegerSet");
        } else {
          xmlSerializer.writeStartElement("valueIntegerSet");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueIntegerSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueShortSet (mapped with "valueShortSet")
    if (object.valueShortSet!=null)  {
      int n=object.valueShortSet.size();
      for (Short item: object.valueShortSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueShortSet");
        } else {
          xmlSerializer.writeStartElement("valueShortSet");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueShortSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field valueStringSet (mapped with "valueStringSet")
    if (object.valueStringSet!=null)  {
      int n=object.valueStringSet.size();
      for (String item: object.valueStringSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueStringSet");
        } else {
          xmlSerializer.writeStartElement("valueStringSet");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueStringSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean instance = new Bean();
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
          case "valueBeanSet":
            // field valueBeanSet (mapped with "valueBeanSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashSet<Bean> collection=new LinkedHashSet<>();
              Bean item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=beanBindMap().parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanSet=collection;
            }
          break;
          case "valueBigDecimalSet":
            // field valueBigDecimalSet (mapped with "valueBigDecimalSet")
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
          case "valueByteSet":
            // field valueByteSet (mapped with "valueByteSet")
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
            // field valueCharacterSet (mapped with "valueCharacterSet")
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
            // field valueDoubleSet (mapped with "valueDoubleSet")
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
          case "valueEnumTypeSet":
            // field valueEnumTypeSet (mapped with "valueEnumTypeSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<EnumType> collection=new HashSet<>();
              EnumType item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumTypeSet=collection;
            }
          break;
          case "valueFloatSet":
            // field valueFloatSet (mapped with "valueFloatSet")
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
          case "valueIntegerSet":
            // field valueIntegerSet (mapped with "valueIntegerSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashSet<Integer> collection=new LinkedHashSet<>();
              Integer item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getIntValue();
                }
                collection.add(item);
              }
              instance.valueIntegerSet=collection;
            }
          break;
          case "valueShortSet":
            // field valueShortSet (mapped with "valueShortSet")
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
            // field valueStringSet (mapped with "valueStringSet")
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
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public Bean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean instance = new Bean();
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
          case "valueBeanSet":
            // field valueBeanSet (mapped with "valueBeanSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashSet<Bean> collection=new LinkedHashSet<>();
              Bean item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=beanBindMap().parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.valueBeanSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedHashSet<Bean> collection=new LinkedHashSet<>();
              instance.valueBeanSet=collection;
            }
          break;
          case "valueBigDecimalSet":
            // field valueBigDecimalSet (mapped with "valueBigDecimalSet")
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
          case "valueByteSet":
            // field valueByteSet (mapped with "valueByteSet")
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
            // field valueCharacterSet (mapped with "valueCharacterSet")
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
            // field valueDoubleSet (mapped with "valueDoubleSet")
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
          case "valueEnumTypeSet":
            // field valueEnumTypeSet (mapped with "valueEnumTypeSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<EnumType> collection=new HashSet<>();
              EnumType item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                   {
                    String tempEnum=jacksonParser.getText();
                    item=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
                  }
                }
                collection.add(item);
              }
              instance.valueEnumTypeSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              HashSet<EnumType> collection=new HashSet<>();
              instance.valueEnumTypeSet=collection;
            }
          break;
          case "valueFloatSet":
            // field valueFloatSet (mapped with "valueFloatSet")
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
          case "valueIntegerSet":
            // field valueIntegerSet (mapped with "valueIntegerSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              LinkedHashSet<Integer> collection=new LinkedHashSet<>();
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
              instance.valueIntegerSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              LinkedHashSet<Integer> collection=new LinkedHashSet<>();
              instance.valueIntegerSet=collection;
            }
          break;
          case "valueShortSet":
            // field valueShortSet (mapped with "valueShortSet")
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
            // field valueStringSet (mapped with "valueStringSet")
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
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public Bean parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean instance = new Bean();
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
                case "valueBeanSet":
                  // property valueBeanSet (mapped on "valueBeanSet")
                   {
                    LinkedHashSet<Bean> collection=new LinkedHashSet<>();
                    Bean item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=beanBindMap().parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueBeanSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=beanBindMap().parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.valueBeanSet=collection;
                    read=false;
                  }
                break;
                case "valueBigDecimalSet":
                  // property valueBigDecimalSet (mapped on "valueBigDecimalSet")
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
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueBigDecimalSet")) {
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
                case "valueByteSet":
                  // property valueByteSet (mapped on "valueByteSet")
                   {
                    HashSet<Byte> collection=new HashSet<>();
                    Byte item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueByteSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueByteSet=collection;
                    read=false;
                  }
                break;
                case "valueCharacterSet":
                  // property valueCharacterSet (mapped on "valueCharacterSet")
                   {
                    HashSet<Character> collection=new HashSet<>();
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
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueCharacterSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueCharacterSet=collection;
                    read=false;
                  }
                break;
                case "valueDoubleSet":
                  // property valueDoubleSet (mapped on "valueDoubleSet")
                   {
                    HashSet<Double> collection=new HashSet<>();
                    Double item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueDoubleSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueDoubleSet=collection;
                    read=false;
                  }
                break;
                case "valueEnumTypeSet":
                  // property valueEnumTypeSet (mapped on "valueEnumTypeSet")
                   {
                    HashSet<EnumType> collection=new HashSet<>();
                    EnumType item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueEnumTypeSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                    }
                    instance.valueEnumTypeSet=collection;
                    read=false;
                  }
                break;
                case "valueFloatSet":
                  // property valueFloatSet (mapped on "valueFloatSet")
                   {
                    HashSet<Float> collection=new HashSet<>();
                    Float item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueFloatSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueFloatSet=collection;
                    read=false;
                  }
                break;
                case "valueIntegerSet":
                  // property valueIntegerSet (mapped on "valueIntegerSet")
                   {
                    LinkedHashSet<Integer> collection=new LinkedHashSet<>();
                    Integer item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueIntegerSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueIntegerSet=collection;
                    read=false;
                  }
                break;
                case "valueShortSet":
                  // property valueShortSet (mapped on "valueShortSet")
                   {
                    HashSet<Short> collection=new HashSet<>();
                    Short item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueShortSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(short)PrimitiveUtils.readShort(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.valueShortSet=collection;
                    read=false;
                  }
                break;
                case "valueStringSet":
                  // property valueStringSet (mapped on "valueStringSet")
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
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueStringSet")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.valueStringSet=collection;
                    read=false;
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
