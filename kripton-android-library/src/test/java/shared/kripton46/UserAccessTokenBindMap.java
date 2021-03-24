package shared.kripton46;

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
 * This class is binder map for UserAccessToken
 *
 * @see UserAccessToken
 */
@BindMap(UserAccessToken.class)
public class UserAccessTokenBindMap extends AbstractMapper<UserAccessToken> {
  @Override
  public int serializeOnJackson(UserAccessToken object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field creationDate (mapped with "creationDate")
    fieldCount++;
    jacksonSerializer.writeNumberField("creationDate", object.getCreationDate());

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(UserAccessToken object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field creationDate (mapped with "creationDate")
    jacksonSerializer.writeStringField("creationDate", PrimitiveUtils.writeLong(object.getCreationDate()));

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(UserAccessToken object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("userAccessToken");
    }

    // Persisted fields:

    // field creationDate (mapped with "creationDate")
    xmlSerializer.writeStartElement("creationDate");
    xmlSerializer.writeLong(object.getCreationDate());
    xmlSerializer.writeEndElement();

    // field uid (mapped with "uid")
    if (object.getUid()!=null) {
      xmlSerializer.writeStartElement("uid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUid()));
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
  public UserAccessToken parseOnJackson(JsonParser jacksonParser) throws Exception {
    UserAccessToken instance = new UserAccessToken();
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
          case "creationDate":
            // field creationDate (mapped with "creationDate")
            instance.setCreationDate(jacksonParser.getLongValue());
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUid(jacksonParser.getText());
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
  public UserAccessToken parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    UserAccessToken instance = new UserAccessToken();
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
          case "creationDate":
            // field creationDate (mapped with "creationDate")
            instance.setCreationDate(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUid(jacksonParser.getText());
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
  public UserAccessToken parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    UserAccessToken instance = new UserAccessToken();
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
                case "creationDate":
                  // property creationDate (mapped on "creationDate")
                  instance.setCreationDate(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  instance.setUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
