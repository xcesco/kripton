package bind.kripton42faster;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Restaurant
 *
 * @see Restaurant
 */
@BindMap(Restaurant.class)
public class RestaurantBindMap extends AbstractMapper<Restaurant> {
  @Override
  public int serializeOnJackson(Restaurant object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field latitude (mapped with "latitude")
    if (object.latitude!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("latitude", object.latitude);
    }

    // field longitude (mapped with "longitude")
    if (object.longitude!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("longitude", object.longitude);
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Restaurant object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field latitude (mapped with "latitude")
    if (object.latitude!=null)  {
      jacksonSerializer.writeStringField("latitude", PrimitiveUtils.writeDouble(object.latitude));
    }

    // field longitude (mapped with "longitude")
    if (object.longitude!=null)  {
      jacksonSerializer.writeStringField("longitude", PrimitiveUtils.writeDouble(object.longitude));
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Restaurant object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("restaurant");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeAttribute("id", PrimitiveUtils.writeLong(object.id));

    // field latitude (mapped with "latitude")
    if (object.latitude!=null)  {
      xmlSerializer.writeAttribute("latitude", PrimitiveUtils.writeDouble(object.latitude));
    }

    // field longitude (mapped with "longitude")
    if (object.longitude!=null)  {
      xmlSerializer.writeAttribute("longitude", PrimitiveUtils.writeDouble(object.longitude));
    }

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(object.name));
    }

    // field address (mapped with "address")
    if (object.address!=null) {
      xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(object.address));
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Restaurant parseOnJackson(JsonParser jacksonParser) throws Exception {
    Restaurant instance = new Restaurant();
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
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.latitude=jacksonParser.getDoubleValue();
            }
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.longitude=jacksonParser.getDoubleValue();
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
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
  public Restaurant parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Restaurant instance = new Restaurant();
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
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.latitude=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
            }
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.longitude=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
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
  public Restaurant parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Restaurant instance = new Restaurant();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "id":
            // field id (mapped by "id")
            instance.id=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
          break;
          case "latitude":
            // field latitude (mapped by "latitude")
            instance.latitude=PrimitiveUtils.readDouble(xmlParser.getAttributeValue(attributeIndex), null);
          break;
          case "longitude":
            // field longitude (mapped by "longitude")
            instance.longitude=PrimitiveUtils.readDouble(xmlParser.getAttributeValue(attributeIndex), null);
          break;
          case "name":
            // field name (mapped by "name")
            instance.name=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
          break;
          default:
          break;
      }
    }

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
            // No property to manage here
          break;
          case END_TAG:
            if (elementName.equals(xmlParser.getName())) {
              currentTag = elementName;
              elementName = null;
            }
          break;
          case CDSECT:
          case TEXT:
            if (elementName!=null && xmlParser.hasText()) {
              // property address
              instance.address=StringEscapeUtils.unescapeXml(xmlParser.getText());
            }
          break;
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
