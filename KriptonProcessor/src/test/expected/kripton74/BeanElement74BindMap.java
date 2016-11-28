package kripton74;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.PrimitiveUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement74
 *
 * @see BeanElement74
 */
@BindMap
public class BeanElement74BindMap extends AbstractMapper<BeanElement74> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement74 createInstance() {
    return new BeanElement74();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapEnumBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<BeanEnum74, BeanElement74> item: object.valueMapEnumBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeFieldName("v");
              context.mapperFor(BeanElement74.class).serializeOnJackson(context, item.getValue(), wrapper);
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueMapIntByteArray
      if (object.valueMapIntByteArray!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapIntByteArray");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeNumberField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeBinaryField("v", item.getValue());
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapStringInteger");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeNumberField("v", item.getValue());
            }
          }
          jacksonSerializer.writeEndObject();
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapEnumBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<BeanEnum74, BeanElement74> item: object.valueMapEnumBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeFieldName("v");
              context.mapperFor(BeanElement74.class).serializeOnJacksonAsString(context, item.getValue(), wrapper);
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueMapIntByteArray
      if (object.valueMapIntByteArray!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapIntByteArray");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", PrimitiveUtil.writeInteger(item.getKey()));
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeBinaryField("v", item.getValue());
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapStringInteger");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            if (item.getValue()!=null)  {
              jacksonSerializer.writeStringField("v", PrimitiveUtil.writeInteger(item.getValue()));
            }
          }
          jacksonSerializer.writeEndObject();
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
  public void serializeOnXml(XmlBinderContext context, BeanElement74 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement74");
      }

      // Persisted fields:

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
      }

      // field valueMapEnumBean
      if (object.valueMapEnumBean!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueMapEnumBean");
        for (Map.Entry<BeanEnum74, BeanElement74> item: object.valueMapEnumBean.entrySet()) {
          xmlSerializer.writeStartElement("item");
            xmlSerializer.writeStartElement("k");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey().toString()));
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              xmlSerializer.writeStartElement("v");
              context.mapperFor(BeanElement74.class).serializeOnXml(context, item.getValue(), wrapper, 1);
              xmlSerializer.writeEndElement();
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
              xmlSerializer.writeStartElement("v");
              xmlSerializer.writeBinary(item.getValue(), 0, item.getValue().length);
              xmlSerializer.writeEndElement();
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
            xmlSerializer.writeStartElement("k");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("v");
            } else {
              xmlSerializer.writeStartElement("v");
              xmlSerializer.writeInt(item.getValue());
              xmlSerializer.writeEndElement();
            }
          xmlSerializer.writeEndElement();
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
  public BeanElement74 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement74 instance = createInstance();
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
            case "valueMapEnumBean":
              // field valueMapEnumBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<BeanEnum74, BeanElement74> collection=new HashMap<>();
                BeanEnum74 key=null;
                BeanElement74 value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                   {
                    String tempEnum=jacksonParser.getText();
                    key=StringUtility.hasText(tempEnum)?BeanEnum74.valueOf(tempEnum):null;
                  }
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    value=context.mapperFor(BeanElement74.class).parseOnJackson(context, wrapper);
                  }
                  collection.put(key, value);
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
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    value=jacksonParser.getBinaryValue();
                  }
                  collection.put(key, value);
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
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    value=jacksonParser.getIntValue();
                  }
                  collection.put(key, value);
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
  public BeanElement74 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement74 instance = createInstance();
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
            case "valueMapEnumBean":
              // field valueMapEnumBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<BeanEnum74, BeanElement74> collection=new HashMap<>();
                BeanEnum74 key=null;
                BeanElement74 value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                   {
                    String tempEnum=jacksonParser.getText();
                    key=StringUtility.hasText(tempEnum)?BeanEnum74.valueOf(tempEnum):null;
                  }
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    value=context.mapperFor(BeanElement74.class).parseOnJacksonAsString(context, wrapper);
                  }
                  collection.put(key, value);
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
                  key=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      value=jacksonParser.getBinaryValue();
                    }
                  }
                  collection.put(key, value);
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
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    key=jacksonParser.getText();
                  }
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    value=null;
                  } else {
                    value=PrimitiveUtil.readInteger(jacksonParser.getText(), null);
                  }
                  collection.put(key, value);
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
  public BeanElement74 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement74 instance = createInstance();
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
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueMapEnumBean":
                    // property valueMapEnumBean
                     {
                      HashMap<BeanEnum74, BeanElement74> collection=new HashMap<>();
                      BeanEnum74 key;
                      BeanElement74 value;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("item")) {
                        xmlParser.nextTag();
                        key=BeanEnum74.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=context.mapperFor(BeanElement74.class).parseOnXml(context, wrapper, eventType);
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
                      key=PrimitiveUtil.readInteger(xmlParser.getElementAsInt(), null);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=xmlParser.getElementAsBinary();
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueMapIntByteArray")) {
                        xmlParser.nextTag();
                        key=PrimitiveUtil.readInteger(xmlParser.getElementAsInt(), null);
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
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("rutto")) {
                        xmlParser.nextTag();
                        key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=PrimitiveUtil.readInteger(xmlParser.getElementAsInt(), null);
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
