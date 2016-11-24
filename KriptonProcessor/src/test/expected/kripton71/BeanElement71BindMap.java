package kripton71;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
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
            jacksonSerializer.writeString(item.toString());
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        int n=object.valueEnumSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (BeanEnum71 item: object.valueEnumSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item.toString());
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement71 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

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
            jacksonSerializer.writeString(item.toString());
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        int n=object.valueEnumSet.size();
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueEnumSet");
        jacksonSerializer.writeStartArray();
        for (BeanEnum71 item: object.valueEnumSet) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item.toString());
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
  public void serializeOnXml(XmlBinderContext context, BeanElement71 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueEnumList
      if (object.valueEnumList!=null)  {
        int n=object.valueEnumList.size();
        BeanEnum71 item;
        // write wrapper tag
        xmlSerializer.writeStartElement("valueEnumList");
        for (int i=0; i<n; i++) {
          item=object.valueEnumList.get(i);
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

      // field valueEnumSet
      if (object.valueEnumSet!=null)  {
        int n=object.valueEnumSet.size();
        // write wrapper tag
        xmlSerializer.writeStartElement("valueEnumSet");
        for (BeanEnum71 item: object.valueEnumSet) {
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
            case "valueEnumList":
              // field valueEnumList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanEnum71> collection=new LinkedList<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum71.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumList=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanEnum71> collection=new HashSet<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum71.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueStringList":
              // field valueStringList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=jacksonParser.getText();
                  }
                  collection.add(item);
                }
                instance.valueStringList=collection;
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
            case "valueEnumList":
              // field valueEnumList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BeanEnum71> collection=new LinkedList<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum71.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumList=collection;
              }
            break;
            case "valueEnumSet":
              // field valueEnumSet
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                HashSet<BeanEnum71> collection=new HashSet<>();
                BeanEnum71 item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=StringUtility.hasText(jacksonParser.getText())?BeanEnum71.valueOf(jacksonParser.getText()):null;
                  }
                  collection.add(item);
                }
                instance.valueEnumSet=collection;
              }
            break;
            case "valueStringList":
              // field valueStringList
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
                instance.valueStringList=collection;
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
                  case "valueEnumList":
                    // property valueEnumList
                    if (!xmlParser.isEmptyElement()) {
                      LinkedList<BeanEnum71> collection=new LinkedList<>();
                      BeanEnum71 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueEnumList")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.skipElement();
                          } else {
                            item=BeanEnum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueEnumList=collection;
                    }
                  break;
                  case "valueEnumSet":
                    // property valueEnumSet
                    if (!xmlParser.isEmptyElement()) {
                      HashSet<BeanEnum71> collection=new HashSet<>();
                      BeanEnum71 item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueEnumSet")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.skipElement();
                          } else {
                            item=BeanEnum71.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueEnumSet=collection;
                    }
                  break;
                  case "valueStringList":
                    // property valueStringList
                    if (!xmlParser.isEmptyElement()) {
                      ArrayList<String> collection=new ArrayList<>();
                      String item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueStringList")) {
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
                      instance.valueStringList=collection;
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
