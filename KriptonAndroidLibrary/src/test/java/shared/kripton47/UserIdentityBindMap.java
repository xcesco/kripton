package shared.kripton47;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for UserIdentity
 *
 * @see UserIdentity
 */
@BindMap(UserIdentity.class)
public class UserIdentityBindMap extends AbstractMapper<UserIdentity> {
  @Override
  public int serializeOnJackson(UserIdentity object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field email (mapped with "email")
    if (object.getEmail()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.getEmail());
    }

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field username (mapped with "username")
    if (object.getUsername()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("username", object.getUsername());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(UserIdentity object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field email (mapped with "email")
    if (object.getEmail()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.getEmail());
    }

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field username (mapped with "username")
    if (object.getUsername()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("username", object.getUsername());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(UserIdentity object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("userIdentity");
    }

    // Persisted fields:

    // field email (mapped with "email")
    if (object.getEmail()!=null) {
      xmlSerializer.writeStartElement("email");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getEmail()));
      xmlSerializer.writeEndElement();
    }

    // field name (mapped with "name")
    if (object.getName()!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
      xmlSerializer.writeEndElement();
    }

    // field username (mapped with "username")
    if (object.getUsername()!=null) {
      xmlSerializer.writeStartElement("username");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUsername()));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public UserIdentity parseOnJackson(JsonParser jacksonParser) throws Exception {
    UserIdentity instance = new UserIdentity();
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
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setEmail(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "username":
            // field username (mapped with "username")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUsername(jacksonParser.getText());
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
  public UserIdentity parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    UserIdentity instance = new UserIdentity();
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
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setEmail(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "username":
            // field username (mapped with "username")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUsername(jacksonParser.getText());
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
  public UserIdentity parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    UserIdentity instance = new UserIdentity();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
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
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "email":
                  // property email (mapped on "email")
                  instance.setEmail(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "username":
                  // property username (mapped on "username")
                  instance.setUsername(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                default:
                break;
              }
            break;
            case XmlPullParser.END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case XmlPullParser.CDSECT:
            case XmlPullParser.TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }
