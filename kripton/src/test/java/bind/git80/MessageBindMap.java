package bind.git80;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Message
 *
 * @see Message
 */
@BindMap(Message.class)
public class MessageBindMap extends AbstractMapper<Message> {
  /**
   * binder for type Errori
   */
  private ErroriBindMap erroriBindMap;

  @Override
  public int serializeOnJackson(Message object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field codiceRitorno (mapped with "codiceRitorno")
    fieldCount++;
    jacksonSerializer.writeNumberField("codiceRitorno", object.getCodiceRitorno());

    // field errori (mapped with "errori")
    if (object.getErrori()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("errori");
      erroriBindMap.serializeOnJackson(object.getErrori(), jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Message object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field codiceRitorno (mapped with "codiceRitorno")
    jacksonSerializer.writeStringField("codiceRitorno", PrimitiveUtils.writeInteger(object.getCodiceRitorno()));

    // field errori (mapped with "errori")
    if (object.getErrori()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("errori");
      if (erroriBindMap.serializeOnJacksonAsString(object.getErrori(), jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("errori");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Message object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("message");
    }

    // Persisted fields:

    // field codiceRitorno (mapped with "codiceRitorno")
    xmlSerializer.writeStartElement("codiceRitorno");
    xmlSerializer.writeInt(object.getCodiceRitorno());
    xmlSerializer.writeEndElement();

    // field errori (mapped with "errori")
    if (object.getErrori()!=null)  {
      xmlSerializer.writeStartElement("errori");
      erroriBindMap.serializeOnXml(object.getErrori(), xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Message parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    Errori __errori=null;
    int __codiceRitorno=0;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Message instance=new Message(__errori,__codiceRitorno);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "codiceRitorno":
            // field codiceRitorno (mapped with "codiceRitorno")
            __codiceRitorno=jacksonParser.getIntValue();
          break;
          case "errori":
            // field errori (mapped with "errori")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              __errori=erroriBindMap.parseOnJackson(jacksonParser);
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Message instance=new Message(__errori,__codiceRitorno);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Message parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    Errori __errori=null;
    int __codiceRitorno=0;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Message instance=new Message(__errori,__codiceRitorno);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "codiceRitorno":
            // field codiceRitorno (mapped with "codiceRitorno")
            __codiceRitorno=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "errori":
            // field errori (mapped with "errori")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              __errori=erroriBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Message instance=new Message(__errori,__codiceRitorno);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Message parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    Errori __errori=null;
    int __codiceRitorno=0;
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
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
          case START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "codiceRitorno":
                  // property codiceRitorno (mapped on "codiceRitorno")
                  __codiceRitorno=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "errori":
                  // property errori (mapped on "errori")
                  __errori=erroriBindMap.parseOnXml(xmlParser, eventType);
                break;
                default:
                  xmlParser.skipChildren();
                break;
              }
            break;
            case END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case CDSECT:
            case TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      // immutable object: inizialize object
      Message instance=new Message(__errori,__codiceRitorno);
      return instance;
    }

    public void init() {
      // binding maps initialization 
      erroriBindMap=BinderUtils.mapperFor(Errori.class);
    }
  }
