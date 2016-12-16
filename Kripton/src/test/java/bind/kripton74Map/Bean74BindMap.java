package bind.kripton74Map;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class is the shared preference binder defined for Bean74
 *
 * @see Bean74
 */
@BindMap
public class Bean74BindMap extends AbstractMapper<Bean74> {
  /**
   * create new object instance
   */
  @Override
  public Bean74 createInstance() {
    return new Bean74();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapBeanLocale.size()>0) {
          jacksonSerializer.writeFieldName("valueMapBeanLocale");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeFieldName("k");
            context.mapperFor(Bean74.class).serializeOnJackson(context, item.getKey(), wrapper);
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("v");
            } else {
              jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapBeanLocale");
        }
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapEnumBean.size()>0) {
          jacksonSerializer.writeFieldName("valueMapEnumBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("k", item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("v");
            } else {
              jacksonSerializer.writeFieldName("v");
              context.mapperFor(Bean74.class).serializeOnJackson(context, item.getValue(), wrapper);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapEnumBean");
        }
      }

      // field valueMapIntByteArray
      if (object.valueMapIntByteArray!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapIntByteArray.size()>0) {
          jacksonSerializer.writeFieldName("valueMapIntByteArray");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeNumberField("k", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("v");
            } else {
              jacksonSerializer.writeBinaryField("v", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapIntByteArray");
        }
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapStringInteger.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringInteger");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("k", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("v");
            } else {
              jacksonSerializer.writeNumberField("v", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringInteger");
        }
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapBeanLocale.size()>0) {
          jacksonSerializer.writeFieldName("valueMapBeanLocale");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeFieldName("k");
            if (context.mapperFor(Bean74.class).serializeOnJacksonAsString(context, item.getKey(), wrapper)==0) {
              jacksonSerializer.writeNullField("k");
            }
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("v", "null");
            } else {
              jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("valueMapBeanLocale", "null");
        }
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapEnumBean.size()>0) {
          jacksonSerializer.writeFieldName("valueMapEnumBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("k", item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("v", "null");
            } else {
              jacksonSerializer.writeFieldName("v");
              if (context.mapperFor(Bean74.class).serializeOnJacksonAsString(context, item.getValue(), wrapper)==0) {
                jacksonSerializer.writeNullField("v");
              }
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("valueMapEnumBean", "null");
        }
      }

      // field valueMapIntByteArray
      if (object.valueMapIntByteArray!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapIntByteArray.size()>0) {
          jacksonSerializer.writeFieldName("valueMapIntByteArray");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("k", PrimitiveUtils.writeInteger(item.getKey()));
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("v", "null");
            } else {
              jacksonSerializer.writeBinaryField("v", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("valueMapIntByteArray", "null");
        }
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapStringInteger.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringInteger");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("k", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("v", "null");
            } else {
              jacksonSerializer.writeStringField("v", PrimitiveUtils.writeInteger(item.getValue()));
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("valueMapStringInteger", "null");
        }
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
  public void serializeOnXml(KriptonXmlContext context, Bean74 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean74");
      }

      // Persisted fields:

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
      }

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          xmlSerializer.writeStartElement("valueMapBeanLocale");
            xmlSerializer.writeStartElement("k");
            context.mapperFor(Bean74.class).serializeOnXml(context, item.getKey(), wrapper, 1);
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              if (item.getValue()!=null)  {
                xmlSerializer.writeStartElement("v");
                xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(item.getValue())));
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueMapEnumBean");
        for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
          xmlSerializer.writeStartElement("item");
            xmlSerializer.writeStartElement("k");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey().toString()));
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              if (item.getValue()!=null)  {
                xmlSerializer.writeStartElement("v");
                context.mapperFor(Bean74.class).serializeOnXml(context, item.getValue(), wrapper, 1);
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
        xmlSerializer.writeEndElement();
      }

      // field valueMapIntByteArray
      if (object.valueMapIntByteArray!=null)  {
        for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
          xmlSerializer.writeStartElement("valueMapIntByteArray");
            xmlSerializer.writeStartElement("k");
            xmlSerializer.writeInt(item.getKey());
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              if (item.getValue()!=null) {
                xmlSerializer.writeStartElement("v");
                xmlSerializer.writeBinary(item.getValue(), 0, item.getValue().length);
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueMapStringInteger");
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          xmlSerializer.writeStartElement("rutto");
            if (item.getKey()!=null) {
              xmlSerializer.writeStartElement("k");
              xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
              xmlSerializer.writeEndElement();
            }
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              if (item.getValue()!=null)  {
                xmlSerializer.writeStartElement("v");
                xmlSerializer.writeInt(item.getValue());
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
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
  public Bean74 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean74 instance = createInstance();
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
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            case "valueMapBeanLocale":
              // field valueMapBeanLocale
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Bean74, Locale> collection=new HashMap<>();
                Bean74 key=null;
                Locale value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=context.mapperFor(Bean74.class).parseOnJackson(context, wrapper);
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    value= LocaleUtils.read(jacksonParser.getText());
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.valueMapBeanLocale=collection;
              }
            break;
            case "valueMapEnumBean":
              // field valueMapEnumBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Enum74, Bean74> collection=new HashMap<>();
                Enum74 key=null;
                Bean74 value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                   {
                    String tempEnum=jacksonParser.getText();
                    key=StringUtils.hasText(tempEnum)?Enum74.valueOf(tempEnum):null;
                  }
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                    value=context.mapperFor(Bean74.class).parseOnJackson(context, wrapper);
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.valueMapEnumBean=collection;
              }
            break;
            case "valueMapIntByteArray":
              // field valueMapIntByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Integer, byte[]> collection=new HashMap<>();
                Integer key=null;
                byte[] value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=jacksonParser.getIntValue();
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    value=jacksonParser.getBinaryValue();
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.valueMapIntByteArray=collection;
              }
            break;
            case "valueMapStringInteger":
              // field valueMapStringInteger
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<String, Integer> collection=new HashMap<>();
                String key=null;
                Integer value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=jacksonParser.getText();
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    value=jacksonParser.getIntValue();
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.valueMapStringInteger=collection;
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
  public Bean74 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean74 instance = createInstance();
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
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
              }
            break;
            case "valueMapBeanLocale":
              // field valueMapBeanLocale
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Bean74, Locale> collection=new HashMap<>();
                Bean74 key=null;
                Locale value=null;
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
                    case "k":
                      key=context.mapperFor(Bean74.class).parseOnJacksonAsString(context, wrapper);
                    break;
                    case "v":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                          value=LocaleUtils.read(jacksonParser.getText());
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
                instance.valueMapBeanLocale=collection;
              }
            break;
            case "valueMapEnumBean":
              // field valueMapEnumBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Enum74, Bean74> collection=new HashMap<>();
                Enum74 key=null;
                Bean74 value=null;
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
                    case "k":
                       {
                        String tempEnum=jacksonParser.getText();
                        key=StringUtils.hasText(tempEnum)?Enum74.valueOf(tempEnum):null;
                      }
                    break;
                    case "v":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                          value=context.mapperFor(Bean74.class).parseOnJacksonAsString(context, wrapper);
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
                instance.valueMapEnumBean=collection;
              }
            break;
            case "valueMapIntByteArray":
              // field valueMapIntByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Integer, byte[]> collection=new HashMap<>();
                Integer key=null;
                byte[] value=null;
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
                    case "k":
                      key=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                    break;
                    case "v":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                          value=Base64Utils.decode(jacksonParser.getValueAsString());
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
                instance.valueMapIntByteArray=collection;
              }
            break;
            case "valueMapStringInteger":
              // field valueMapStringInteger
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<String, Integer> collection=new HashMap<>();
                String key=null;
                Integer value=null;
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
                    case "k":
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        key=jacksonParser.getText();
                      }
                    break;
                    case "v":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                          value=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
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
                instance.valueMapStringInteger=collection;
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
  public Bean74 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean74 instance = createInstance();
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
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueMapBeanLocale":
                    // property valueMapBeanLocale
                     {
                      HashMap<Bean74, Locale> collection=new HashMap<>();
                      Bean74 key;
                      Locale value;
                      // add first element
                      xmlParser.nextTag();
                      key=context.mapperFor(Bean74.class).parseOnXml(context, wrapper, eventType);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueMapBeanLocale")) {
                        xmlParser.nextTag();
                        key=context.mapperFor(Bean74.class).parseOnXml(context, wrapper, eventType);
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.valueMapBeanLocale=collection;
                      read=false;
                    }
                  break;
                  case "valueMapEnumBean":
                    // property valueMapEnumBean
                     {
                      HashMap<Enum74, Bean74> collection=new HashMap<>();
                      Enum74 key;
                      Bean74 value;
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        xmlParser.nextTag();
                        key=Enum74.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=context.mapperFor(Bean74.class).parseOnXml(context, wrapper, eventType);
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.valueMapEnumBean=collection;
                    }
                  break;
                  case "valueMapIntByteArray":
                    // property valueMapIntByteArray
                     {
                      HashMap<Integer, byte[]> collection=new HashMap<>();
                      Integer key;
                      byte[] value;
                      // add first element
                      xmlParser.nextTag();
                      key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=xmlParser.getElementAsBinary();
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueMapIntByteArray")) {
                        xmlParser.nextTag();
                        key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=xmlParser.getElementAsBinary();
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.valueMapIntByteArray=collection;
                      read=false;
                    }
                  break;
                  case "valueMapStringInteger":
                    // property valueMapStringInteger
                     {
                      HashMap<String, Integer> collection=new HashMap<>();
                      String key;
                      Integer value;
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("rutto")) {
                        xmlParser.nextTag();
                        key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.valueMapStringInteger=collection;
                    }
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
