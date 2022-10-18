package bind.emptystream;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.time.ZonedDateTime;

/**
 * This class is binder map for Rifornimento
 *
 * @see Rifornimento
 */
@BindMap(Rifornimento.class)
public class RifornimentoBindMap extends AbstractMapper<Rifornimento> {
  @Override
  public int serializeOnJackson(Rifornimento object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field dataRifornimento (mapped with "dataRifornimento")
    if (object.getDataRifornimento()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dataRifornimento", ZonedDateTimeUtils.write(object.getDataRifornimento()));
    }

    // field targa (mapped with "targa")
    if (object.getTarga()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("targa", object.getTarga());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Rifornimento object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field dataRifornimento (mapped with "dataRifornimento")
    if (object.getDataRifornimento()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dataRifornimento", ZonedDateTimeUtils.write(object.getDataRifornimento()));
    }

    // field targa (mapped with "targa")
    if (object.getTarga()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("targa", object.getTarga());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Rifornimento object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("rifornimento");
    }

    // Persisted fields:

    // field dataRifornimento (mapped with "dataRifornimento")
    if (object.getDataRifornimento()!=null)  {
      xmlSerializer.writeStartElement("dataRifornimento");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(ZonedDateTimeUtils.write(object.getDataRifornimento())));
      xmlSerializer.writeEndElement();
    }

    // field targa (mapped with "targa")
    if (object.getTarga()!=null) {
      xmlSerializer.writeStartElement("targa");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getTarga()));
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
  public Rifornimento parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    ZonedDateTime __dataRifornimento=null;
    String __targa=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Rifornimento instance=new Rifornimento(__dataRifornimento,__targa);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "dataRifornimento":
            // field dataRifornimento (mapped with "dataRifornimento")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __dataRifornimento=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "targa":
            // field targa (mapped with "targa")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __targa=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Rifornimento instance=new Rifornimento(__dataRifornimento,__targa);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Rifornimento parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    ZonedDateTime __dataRifornimento=null;
    String __targa=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Rifornimento instance=new Rifornimento(__dataRifornimento,__targa);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "dataRifornimento":
            // field dataRifornimento (mapped with "dataRifornimento")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __dataRifornimento=ZonedDateTimeUtils.read(jacksonParser.getText());
            }
          break;
          case "targa":
            // field targa (mapped with "targa")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __targa=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Rifornimento instance=new Rifornimento(__dataRifornimento,__targa);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Rifornimento parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    ZonedDateTime __dataRifornimento=null;
    String __targa=null;
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName();
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
            currentTag = xmlParser.getName();
            switch(currentTag) {
                case "dataRifornimento":
                  // property dataRifornimento (mapped on "dataRifornimento")
                  __dataRifornimento=ZonedDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "targa":
                  // property targa (mapped on "targa")
                  __targa=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      // if document is empty, the element is null
      if (currentEventType == EventType.START_DOCUMENT && eventType == EventType.END_DOCUMENT) {
          return null;
        } else {
          // immutable object: inizialize object
          Rifornimento instance=new Rifornimento(__dataRifornimento,__targa);
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
      }
    }
