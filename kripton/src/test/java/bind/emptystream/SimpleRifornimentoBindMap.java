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

/**
 * This class is binder map for SimpleRifornimento
 *
 * @see SimpleRifornimento
 */
@BindMap(SimpleRifornimento.class)
public class SimpleRifornimentoBindMap extends AbstractMapper<SimpleRifornimento> {
  @Override
  public int serializeOnJackson(SimpleRifornimento object, JsonGenerator jacksonSerializer) throws
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
  public int serializeOnJacksonAsString(SimpleRifornimento object, JsonGenerator jacksonSerializer)
      throws Exception {
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
  public void serializeOnXml(SimpleRifornimento object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("simpleRifornimento");
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
  public SimpleRifornimento parseOnJackson(JsonParser jacksonParser) throws Exception {
    SimpleRifornimento instance = new SimpleRifornimento();
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
          case "dataRifornimento":
            // field dataRifornimento (mapped with "dataRifornimento")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDataRifornimento(ZonedDateTimeUtils.read(jacksonParser.getText()));
            }
          break;
          case "targa":
            // field targa (mapped with "targa")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setTarga(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public SimpleRifornimento parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    SimpleRifornimento instance = new SimpleRifornimento();
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
          case "dataRifornimento":
            // field dataRifornimento (mapped with "dataRifornimento")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDataRifornimento(ZonedDateTimeUtils.read(jacksonParser.getText()));
            }
          break;
          case "targa":
            // field targa (mapped with "targa")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setTarga(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public SimpleRifornimento parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    SimpleRifornimento instance = new SimpleRifornimento();
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
                  instance.setDataRifornimento(ZonedDateTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "targa":
                  // property targa (mapped on "targa")
                  instance.setTarga(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
      }
    }
