package sqlite.feature.typeadapter.kripton180;

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
 * This class is binder map for Address
 *
 * @see Address
 */
@BindMap(Address.class)
public class AddressBindMap extends AbstractMapper<Address> {
  @Override
  public int serializeOnJackson(Address object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field city (mapped with "city")
    if (object.city!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("city", object.city);
    }

    // field street (mapped with "street")
    if (object.street!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("street", object.street);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Address object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field city (mapped with "city")
    if (object.city!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("city", object.city);
    }

    // field street (mapped with "street")
    if (object.street!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("street", object.street);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Address object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("address");
    }

    // Persisted fields:

    // field city (mapped with "city")
    if (object.city!=null) {
      xmlSerializer.writeStartElement("city");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.city));
      xmlSerializer.writeEndElement();
    }

    // field street (mapped with "street")
    if (object.street!=null) {
      xmlSerializer.writeStartElement("street");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.street));
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
  public Address parseOnJackson(JsonParser jacksonParser) throws Exception {
    Address instance = new Address();
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
          case "city":
            // field city (mapped with "city")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.city=jacksonParser.getText();
            }
          break;
          case "street":
            // field street (mapped with "street")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.street=jacksonParser.getText();
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
  public Address parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Address instance = new Address();
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
          case "city":
            // field city (mapped with "city")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.city=jacksonParser.getText();
            }
          break;
          case "street":
            // field street (mapped with "street")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.street=jacksonParser.getText();
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
  public Address parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Address instance = new Address();
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
                case "city":
                  // property city (mapped on "city")
                  instance.city=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "street":
                  // property street (mapped on "street")
                  instance.street=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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

    public void init() {
      // binding maps initialization 
    }
  }
