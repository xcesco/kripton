package bind.kripton74Map;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.util.HashMap;
import java.util.Locale;
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
  public int serializeOnJackson(JacksonContext context, BeanElement74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapBeanLocale");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<BeanElement74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeFieldName("k");
          context.mapperFor(BeanElement74.class).serializeOnJackson(context, item.getKey(), wrapper);
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
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
  public int serializeOnJacksonAsString(JacksonContext context, BeanElement74 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueMapBeanLocale");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<BeanElement74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeFieldName("k");
          if (context.mapperFor(BeanElement74.class).serializeOnJacksonAsString(context, item.getKey(), wrapper)==0) {
            jacksonSerializer.writeNullField("k");
          }
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
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
  public void serializeOnXml(XmlBinderContext context, BeanElement74 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement74");
      }

      // Persisted fields:

      // field valueMapBeanLocale
      if (object.valueMapBeanLocale!=null)  {
        for (Map.Entry<BeanElement74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          xmlSerializer.writeStartElement("valueMapBeanLocale");
            xmlSerializer.writeStartElement("k");
            context.mapperFor(BeanElement74.class).serializeOnXml(context, item.getKey(), wrapper, 1);
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
            case "valueMapBeanLocale":
              // field valueMapBeanLocale
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<BeanElement74, Locale> collection=new HashMap<>();
                BeanElement74 key=null;
                Locale value=null;
                JsonToken current;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=context.mapperFor(BeanElement74.class).parseOnJackson(context, wrapper);
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
            case "valueMapBeanLocale":
              // field valueMapBeanLocale
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<BeanElement74, Locale> collection=new HashMap<>();
                BeanElement74 key=null;
                Locale value=null;
                JsonToken current;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  current=jacksonParser.currentToken();
                  for (int i=0; i<2 ;i++) {
                    while (current != JsonToken.FIELD_NAME) {
                      current=jacksonParser.nextToken();
                    }
                    jacksonParser.nextValue();
                    switch(jacksonParser.getCurrentName()) {
                    case "k":
                      key=context.mapperFor(BeanElement74.class).parseOnJacksonAsString(context, wrapper);
                    break;
                    case "v":
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=LocaleUtils.read(jacksonParser.getText());
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
                  case "valueMapBeanLocale":
                    // property valueMapBeanLocale
                     {
                      HashMap<BeanElement74, Locale> collection=new HashMap<>();
                      BeanElement74 key;
                      Locale value;
                      // add first element
                      xmlParser.nextTag();
                      key=context.mapperFor(BeanElement74.class).parseOnXml(context, wrapper, eventType);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueMapBeanLocale")) {
                        xmlParser.nextTag();
                        key=context.mapperFor(BeanElement74.class).parseOnXml(context, wrapper, eventType);
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
