package bind.kripton75;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.Base64Util;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.PrimitiveUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Byte;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement75
 *
 * @see BeanElement75
 */
@BindMap
public class BeanElement75BindMap extends AbstractMapper<BeanElement75> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement75 createInstance() {
    return new BeanElement75();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, BeanElement75 object, JacksonWrapperSerializer wrapper) {
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

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
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

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
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
  public int serializeOnJacksonAsString(JacksonContext context, BeanElement75 object, JacksonWrapperSerializer wrapper) {
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

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
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

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
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
  public void serializeOnXml(XmlBinderContext context, BeanElement75 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement75");
      }

      // Persisted fields:

      // field name
      if (object.name!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
        xmlSerializer.writeEndElement();
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        int n=object.valueByteArray.length;
        Byte item;
        for (int i=0; i<n; i++) {
          item=object.valueByteArray[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueByteArray");
          } else {
            xmlSerializer.writeStartElement("valueByteArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null) {
        xmlSerializer.writeStartElement("valueByteTypeArray");
        xmlSerializer.writeBinary(object.valueByteTypeArray, 0, object.valueByteTypeArray.length);
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
  public BeanElement75 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement75 instance = createInstance();
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
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
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
                instance.valueByteArray=CollectionUtility.asByteArray(collection);
              }
            break;
            case "valueByteTypeArray":
              // field valueByteTypeArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteTypeArray=jacksonParser.getBinaryValue();
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
  public BeanElement75 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement75 instance = createInstance();
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
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
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
                instance.valueByteArray=CollectionUtility.asByteArray(collection);
              }
            break;
            case "valueByteTypeArray":
              // field valueByteTypeArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteTypeArray=Base64Util.decode(jacksonParser.getValueAsString());
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
  public BeanElement75 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement75 instance = createInstance();
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
                  case "valueByteArray":
                    // property valueByteArray
                     {
                      ArrayList<Byte> collection=new ArrayList<>();
                      Byte item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtil.readByte(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueByteArray")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(byte)PrimitiveUtil.readByte(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueByteArray=CollectionUtility.asByteArray(collection);
                      read=false;
                    }
                  break;
                  case "valueByteTypeArray":
                    // property valueByteTypeArray
                    instance.valueByteTypeArray=xmlParser.getElementAsBinary();
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
