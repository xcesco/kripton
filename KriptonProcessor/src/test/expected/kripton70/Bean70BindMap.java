package kripton70;

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
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.lang.Override;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for Bean70
 *
 * @see Bean70
 */
@BindMap
public class Bean70BindMap extends AbstractMapper<Bean70> {
  /**
   * create new object instance
   */
  @Override
  public Bean70 createInstance() {
    return new Bean70();
  }

  /**
   * reset shared preferences
   */
  public void serializeOnJackson(JacksonContext context, Bean70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

      // Serialized Field:

      // field id
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueBool2
      if (object.valueBool2!=null)  {
        jacksonSerializer.writeBooleanField("valueBool2", object.valueBool2);
      }

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueBool1
      if (object.valueBool1!=null)  {
        jacksonSerializer.writeBooleanField("valueBool1", object.valueBool1);
      }

      // field valueBoolType
      jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

      if (writeStartAndEnd) {
        jacksonSerializer.writeEndObject();
      }
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  public void serializeOnJacksonAsString(JacksonContext context, Bean70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", String.valueOf(object.id));

      // field valueBool2
      if (object.valueBool2!=null)  {
        jacksonSerializer.writeStringField("valueBool2", String.valueOf(object.valueBool2));
      }

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueBool1
      if (object.valueBool1!=null)  {
        jacksonSerializer.writeStringField("valueBool1", String.valueOf(object.valueBool1));
      }

      // field valueBoolType
      jacksonSerializer.writeStringField("valueBoolType", String.valueOf(object.valueBoolType));

      if (writeStartAndEnd) {
        jacksonSerializer.writeEndObject();
      }
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  public void serializeOnXml(XmlBinderContext context, Bean70 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeAttribute("name", String.valueOf(object.id));

      // field valueBool2
      if (object.valueBool2!=null)  {
        xmlSerializer.writeAttribute("valueBool2", String.valueOf(object.valueBool2));
      }

      // field valueBoolType
      xmlSerializer.writeAttribute("nameaa", String.valueOf(object.valueBoolType));

      // field valueBool1
      if (object.valueBool1!=null)  {
        xmlSerializer.writeStartElement("valueBool1");
        xmlSerializer.writeBoolean(object.valueBool1);
        xmlSerializer.writeEndElement();
      }

      // field valueString
      if (object.valueString!=null)  {
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
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
  public Bean70 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new Bean70();
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new Bean70();
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      Bean70 instance = createInstance();
      int eventType = currentEventType;
      String currentTag = null;
      String attributeName = null;
      String attributeValue = null;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        currentTag = "root";
      }
      do {
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              if (currentTag==null) {
                currentTag = xmlParser.getName().toString();
                switch(currentTag) {
                    case "root":
                      // tag root of entity
                      int attributes = xmlParser.getAttributeCount();;
                      for (int i = 0; i < attributes; i++) {
                        attributeName = xmlParser.getAttributeLocalName(i);
                        attributeValue = StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(i));
                        switch(attributeName) {
                            case "name":
                              // field id
                              instance.id=Long.valueOf(attributeValue);
                            break;
                            case "valueBool2":
                              // field valueBool2
                              instance.valueBool2=Boolean.valueOf(attributeValue);
                            break;
                            case "nameaa":
                              // field valueBoolType
                              instance.valueBoolType=Boolean.valueOf(attributeValue);
                            break;
                            default:
                            break;
                          }
                        }
                      break;
                      case "valueBool1":
                        // property valueBool1
                      break;
                      default:
                      break;
                    }
                  }
              break;
              case XMLEvent.END_ELEMENT:
                currentTag=null;
              break;
              case XMLEvent.CDATA:
              case XMLEvent.CHARACTERS:
                // property valueString
                instance.valueString = StringEscapeUtils.unescapeXml(xmlParser.getText());
              break;
              default:
              break;
          }
          if (xmlParser.hasNext()) {
            eventType = xmlParser.next();
          }
        } while (xmlParser.hasNext());
        return instance;
      } catch(XMLStreamException e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
