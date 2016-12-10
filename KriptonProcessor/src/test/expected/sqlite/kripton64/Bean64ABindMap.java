package sqlite.kripton64;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for Bean64A
 *
 * @see Bean64A
 */
@BindMap
public class Bean64ABindMap extends AbstractMapper<Bean64A> {
  /**
   * create new object instance
   */
  @Override
  public Bean64A createInstance() {
    return new Bean64A();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean64A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueMapStringBean
      if (object.valueMapStringBean!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapStringBean.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean64A> item: object.valueMapStringBean.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              context.mapperFor(Bean64A.class).serializeOnJackson(context, item.getValue(), wrapper);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringBean");
        }
      }

      // field valueSetString
      if (object.valueSetString!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueSetString");
        jacksonSerializer.writeStartArray();
        for (String item: object.valueSetString) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public int serializeOnJacksonAsString(JacksonContext context, Bean64A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      // field valueMapStringBean
      if (object.valueMapStringBean!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.valueMapStringBean.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean64A> item: object.valueMapStringBean.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("value", "null");
            } else {
              jacksonSerializer.writeFieldName("value");
              if (context.mapperFor(Bean64A.class).serializeOnJacksonAsString(context, item.getValue(), wrapper)==0) {
                jacksonSerializer.writeNullField("value");
              }
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("valueMapStringBean", "null");
        }
      }

      // field valueSetString
      if (object.valueSetString!=null)  {
        fieldCount++;
        int n=object.valueSetString.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueSetString");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (String item: object.valueSetString) {
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

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public void serializeOnXml(XmlBinderContext context, Bean64A object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean64A");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

      // field valueMapStringBean
      if (object.valueMapStringBean!=null)  {
        for (Map.Entry<String, Bean64A> item: object.valueMapStringBean.entrySet()) {
          xmlSerializer.writeStartElement("valueMapStringBean");
            if (item.getKey()!=null) {
              xmlSerializer.writeStartElement("key");
              xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
              xmlSerializer.writeEndElement();
            }
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("value");
            } else {
              if (item.getValue()!=null)  {
                xmlSerializer.writeStartElement("value");
                context.mapperFor(Bean64A.class).serializeOnXml(context, item.getValue(), wrapper, 1);
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
      }

      // field valueSetString
      if (object.valueSetString!=null)  {
        for (String item: object.valueSetString) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueSetString");
          } else {
            xmlSerializer.writeStartElement("valueSetString");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
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
  public Bean64A parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean64A instance = createInstance();
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
              // field id
              instance.id=jacksonParser.getLongValue();
            break;
            case "valueMapStringBean":
              // field valueMapStringBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<String, Bean64A> collection=new HashMap<>();
                String key=null;
                Bean64A value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=jacksonParser.getText();
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                    value=context.mapperFor(Bean64A.class).parseOnJackson(context, wrapper);
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.valueMapStringBean=collection;
              }
            break;
            case "valueSetString":
              // field valueSetString
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
                instance.valueSetString=collection;
              }
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public Bean64A parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean64A instance = createInstance();
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
              // field id
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueMapStringBean":
              // field valueMapStringBean
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<String, Bean64A> collection=new HashMap<>();
                String key=null;
                Bean64A value=null;
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
                    case "key":
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        key=jacksonParser.getText();
                      }
                    break;
                    case "value":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                          value=context.mapperFor(Bean64A.class).parseOnJacksonAsString(context, wrapper);
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
                instance.valueMapStringBean=collection;
              }
            break;
            case "valueSetString":
              // field valueSetString
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
                instance.valueSetString=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                HashSet<String> collection=new HashSet<>();
                instance.valueSetString=collection;
              }
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public Bean64A parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      Bean64A instance = createInstance();
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
                  case "id":
                    // property id
                    instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                  break;
                  case "valueMapStringBean":
                    // property valueMapStringBean
                     {
                      HashMap<String, Bean64A> collection=new HashMap<>();
                      String key;
                      Bean64A value;
                      // add first element
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=context.mapperFor(Bean64A.class).parseOnXml(context, wrapper, eventType);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueMapStringBean")) {
                        xmlParser.nextTag();
                        key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=context.mapperFor(Bean64A.class).parseOnXml(context, wrapper, eventType);
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.valueMapStringBean=collection;
                      read=false;
                    }
                  break;
                  case "valueSetString":
                    // property valueSetString
                     {
                      HashSet<String> collection=new HashSet<>();
                      String item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueSetString")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        }
                        collection.add(item);
                      }
                      instance.valueSetString=collection;
                      read=false;
                    }
                  break;
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
