package bind.feature.git45;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean02
 *
 * @see Bean02
 */
@BindMap(Bean02.class)
public class Bean02BindMap extends AbstractMapper<Bean02> {
  @Override
  public int serializeOnJackson(Bean02 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field date (mapped with "date")
    if (object.getDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("date", DateUtils.write(object.getDate()));
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean02 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field date (mapped with "date")
    if (object.getDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("date", DateUtils.write(object.getDate()));
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean02 object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean02");
    }

    // Persisted fields:

    // field date (mapped with "date")
    if (object.getDate()!=null)  {
      xmlSerializer.writeStartElement("date");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.getDate())));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    if (object.getType()!=null) {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getType()));
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
  public Bean02 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean02 instance = new Bean02();
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
          case "date":
            // field date (mapped with "date")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDate(DateUtils.read(jacksonParser.getText()));
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setType(jacksonParser.getText());
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
  public Bean02 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean02 instance = new Bean02();
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
          case "date":
            // field date (mapped with "date")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDate(DateUtils.read(jacksonParser.getText()));
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setType(jacksonParser.getText());
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
  public Bean02 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean02 instance = new Bean02();
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
                case "date":
                  // property date (mapped on "date")
                  instance.setDate(DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "type":
                  // property type (mapped on "type")
                  instance.setType(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
      return instance;
    }

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
