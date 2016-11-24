package kripton72;

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
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
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
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (BeanEnum72 item: object.valueEnumSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item.toString());
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
            jacksonSerializer.writeString(item);
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement72 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueDoubleSet");
        jacksonSerializer.writeStartArray();
        for (Double item: object.valueDoubleSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(PrimitiveUtil.writeDouble(item));
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
            jacksonSerializer.writeString(item.toString());
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
            jacksonSerializer.writeString(item);
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
  public void serializeOnXml(XmlBinderContext context, BeanElement72 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueDoubleSet
      if (object.valueDoubleSet!=null)  {
        for (Double item: object.valueDoubleSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueDoubleSet");
          } else {
            xmlSerializer.writeStartElement("valueDoubleSet");
            xmlSerializer.writeDouble(item);
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueEnumSet");
        for (BeanEnum72 item: object.valueEnumSet) {
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            if (item!=null)  {
              xmlSerializer.writeStartElement("item");
              xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.toString()));
              xmlSerializer.writeEndElement();
            }
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
            case "valueDoubleSet":
              // field valueDoubleSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<Double> collection=new HashSet<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
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
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedHashSet<BeanEnum72> collection=new LinkedHashSet<>();
                BeanEnum72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum72.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueStringSet":
              // field valueStringSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<String> collection=new HashSet<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
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
            case "valueDoubleSet":
              // field valueDoubleSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<Double> collection=new HashSet<>();
                Double item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=PrimitiveUtil.readDouble(jacksonParser.getText(), null);
                  }
                  collection.add(item);
                }
                instance.valueDoubleSet=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedHashSet<BeanEnum72> collection=new LinkedHashSet<>();
                BeanEnum72 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum72.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueStringSet":
              // field valueStringSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<String> collection=new HashSet<>();
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
                instance.valueStringSet=collection;
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

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      }
      String currentTag = xmlParser.getName().toString();
      Stack<String> elementNameStack = new Stack<>();
      elementNameStack.push(currentTag);
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        eventType = xmlParser.next();
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "valueDoubleSet":
                    // property valueDoubleSet
                     {
                      HashSet<Double> collection=new HashSet<>();
                      Double item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.skipElement();
                      } else {
                        item=PrimitiveUtil.readDouble(xmlParser.getElementAsDouble(), null);
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueDoubleSet")) {
                        if (xmlParser.getName().toString().equals("valueDoubleSet")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                          } else {
                            item=PrimitiveUtil.readDouble(xmlParser.getElementAsDouble(), null);
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueDoubleSet=collection;
                    }
                  break;
                  case "valueEnumSet":
                    // property valueEnumSet
                     {
                      LinkedHashSet<BeanEnum72> collection=new LinkedHashSet<>();
                      BeanEnum72 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueEnumSet")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.skipElement();
                          } else {
                            item=BeanEnum72.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueEnumSet=collection;
                    }
                  break;
                  case "valueStringSet":
                    // property valueStringSet
                     {
                      HashSet<String> collection=new HashSet<>();
                      String item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueStringSet")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.skipElement();
                          } else {
                            item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueStringSet=collection;
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
