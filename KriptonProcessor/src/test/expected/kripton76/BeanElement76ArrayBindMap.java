package kripton76;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.PrimitiveUtil;
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
 * This class is the shared preference binder defined for BeanElement76Array
 *
 * @see BeanAttribute76Array
 */
@BindMap
public class BeanElement76ArrayBindMap extends AbstractMapper<BeanAttribute76Array> {
  /**
   * create new object instance
   */
  @Override
  public BeanAttribute76Array createInstance() {
    return new BeanAttribute76Array();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanAttribute76Array object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueByteArray
      if (object.valueByteArray!=null)  {
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanAttribute76Array object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueByteArray
      if (object.valueByteArray!=null)  {
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
  public void serializeOnXml(XmlBinderContext context, BeanAttribute76Array object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        int n=object.valueByteArray.length;
        Byte item;
        for (int i=0; i<n; i++) {
          item=object.valueByteArray[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueByteArray");
          } else {
            xmlSerializer.writeAttribute("valueByteArray", PrimitiveUtil.writeByte(item));
          }
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
  public BeanAttribute76Array parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute76Array instance = createInstance();
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
  public BeanAttribute76Array parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute76Array instance = createInstance();
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
  public BeanAttribute76Array parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanAttribute76Array instance = createInstance();
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

      // attributes 
      String attributeName = null;
      int attributesCount = xmlParser.getAttributeCount();;
      for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
        attributeName = xmlParser.getAttributeLocalName(attributeIndex);
        switch(attributeName) {
            case "valueByteArray":
              // field valueByteArray
               {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item;
                // add first element
                if (xmlParser.isEmptyElement()) {
                  item=null;
                  xmlParser.nextTag();
                } else {
                  item=(byte)PrimitiveUtil.readByte(xmlParser.getAttributeValue(attributeIndex), null);
                }
                collection.add(item);
                while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueByteArray")) {
                  if (xmlParser.isEmptyElement()) {
                    item=null;
                    xmlParser.nextTag();
                  } else {
                    item=(byte)PrimitiveUtil.readByte(xmlParser.getAttributeValue(attributeIndex), null);
                  }
                  collection.add(item);
                }
                instance.valueByteArray=CollectionUtility.asByteArray(collection);
                read=false;
              }
            break;
            default:
            break;
        }
      }

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
              // No property to manage here
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
