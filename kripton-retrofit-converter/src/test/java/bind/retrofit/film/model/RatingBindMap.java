package bind.retrofit.film.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Rating
 *
 * @see Rating
 */
@BindMap(Rating.class)
public class RatingBindMap extends AbstractMapper<Rating> {
  @Override
  public int serializeOnJackson(Rating object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field source (mapped with "source")
    if (object.getSource()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("source", object.getSource());
    }

    // field value (mapped with "value")
    if (object.getValue()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.getValue());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Rating object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field source (mapped with "source")
    if (object.getSource()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("source", object.getSource());
    }

    // field value (mapped with "value")
    if (object.getValue()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.getValue());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Rating object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("rating");
    }

    // Persisted fields:

    // field source (mapped with "source")
    if (object.getSource()!=null) {
      xmlSerializer.writeStartElement("source");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getSource()));
      xmlSerializer.writeEndElement();
    }

    // field value (mapped with "value")
    if (object.getValue()!=null) {
      xmlSerializer.writeStartElement("value");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getValue()));
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
  public Rating parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __source=null;
    String __value=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Rating instance=new Rating(__source,__value);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "source":
            // field source (mapped with "source")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __source=jacksonParser.getText();
            }
          break;
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __value=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Rating instance=new Rating(__source,__value);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Rating parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __source=null;
    String __value=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Rating instance=new Rating(__source,__value);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "source":
            // field source (mapped with "source")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __source=jacksonParser.getText();
            }
          break;
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __value=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Rating instance=new Rating(__source,__value);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Rating parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __source=null;
    String __value=null;
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
                case "source":
                  // property source (mapped on "source")
                  __source=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "value":
                  // property value (mapped on "value")
                  __value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                default:
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
      Rating instance=new Rating(__source,__value);
      return instance;
    }
  }
