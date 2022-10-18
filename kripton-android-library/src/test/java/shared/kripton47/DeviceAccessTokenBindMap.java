package shared.kripton47;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for DeviceAccessToken
 *
 * @see DeviceAccessToken
 */
@BindMap(DeviceAccessToken.class)
public class DeviceAccessTokenBindMap extends AbstractMapper<DeviceAccessToken> {
  @Override
  public int serializeOnJackson(DeviceAccessToken object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field creationTime (mapped with "creationTime")
    fieldCount++;
    jacksonSerializer.writeNumberField("creationTime", object.getCreationTime());

    // field lastUsedTime (mapped with "lastUsedTime")
    fieldCount++;
    jacksonSerializer.writeNumberField("lastUsedTime", object.getLastUsedTime());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(DeviceAccessToken object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field creationTime (mapped with "creationTime")
    jacksonSerializer.writeStringField("creationTime", PrimitiveUtils.writeLong(object.getCreationTime()));

    // field lastUsedTime (mapped with "lastUsedTime")
    jacksonSerializer.writeStringField("lastUsedTime", PrimitiveUtils.writeLong(object.getLastUsedTime()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(DeviceAccessToken object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("deviceAccessToken");
    }

    // Persisted fields:

    // field creationTime (mapped with "creationTime")
    xmlSerializer.writeStartElement("creationTime");
    xmlSerializer.writeLong(object.getCreationTime());
    xmlSerializer.writeEndElement();

    // field lastUsedTime (mapped with "lastUsedTime")
    xmlSerializer.writeStartElement("lastUsedTime");
    xmlSerializer.writeLong(object.getLastUsedTime());
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public DeviceAccessToken parseOnJackson(JsonParser jacksonParser) throws Exception {
    DeviceAccessToken instance = new DeviceAccessToken();
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
          case "creationTime":
            // field creationTime (mapped with "creationTime")
            instance.setCreationTime(jacksonParser.getLongValue());
          break;
          case "lastUsedTime":
            // field lastUsedTime (mapped with "lastUsedTime")
            instance.setLastUsedTime(jacksonParser.getLongValue());
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
  public DeviceAccessToken parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    DeviceAccessToken instance = new DeviceAccessToken();
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
          case "creationTime":
            // field creationTime (mapped with "creationTime")
            instance.setCreationTime(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "lastUsedTime":
            // field lastUsedTime (mapped with "lastUsedTime")
            instance.setLastUsedTime(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
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
  public DeviceAccessToken parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    DeviceAccessToken instance = new DeviceAccessToken();
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
                case "creationTime":
                  // property creationTime (mapped on "creationTime")
                  instance.setCreationTime(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "lastUsedTime":
                  // property lastUsedTime (mapped on "lastUsedTime")
                  instance.setLastUsedTime(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
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
