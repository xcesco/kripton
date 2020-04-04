package sqlite.kripton111.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for PhoneNumber
 *
 * @see PhoneNumber
 */
@BindMap(PhoneNumber.class)
public class PhoneNumberBindMap extends AbstractMapper<PhoneNumber> {
  @Override
  public int serializeOnJackson(PhoneNumber object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field actionType (mapped with "actionType")
    if (object.actionType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("actionType", object.actionType.toString());
    }

    // field contactId (mapped with "contactId")
    if (object.contactId!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("contactId", object.contactId);
    }

    // field contactName (mapped with "contactName")
    if (object.contactName!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("contactName", object.contactName);
    }

    // field countryCode (mapped with "countryCode")
    if (object.countryCode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("countryCode", object.countryCode);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field number (mapped with "number")
    if (object.number!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("number", object.number);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(PhoneNumber object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field actionType (mapped with "actionType")
    if (object.actionType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("actionType", object.actionType.toString());
    }

    // field contactId (mapped with "contactId")
    if (object.contactId!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("contactId", object.contactId);
    }

    // field contactName (mapped with "contactName")
    if (object.contactName!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("contactName", object.contactName);
    }

    // field countryCode (mapped with "countryCode")
    if (object.countryCode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("countryCode", object.countryCode);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field number (mapped with "number")
    if (object.number!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("number", object.number);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(PhoneNumber object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("phoneNumber");
    }

    // Persisted fields:

    // field actionType (mapped with "actionType")
    if (object.actionType!=null)  {
      xmlSerializer.writeStartElement("actionType");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.actionType.toString()));
      xmlSerializer.writeEndElement();
    }

    // field contactId (mapped with "contactId")
    if (object.contactId!=null) {
      xmlSerializer.writeStartElement("contactId");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.contactId));
      xmlSerializer.writeEndElement();
    }

    // field contactName (mapped with "contactName")
    if (object.contactName!=null) {
      xmlSerializer.writeStartElement("contactName");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.contactName));
      xmlSerializer.writeEndElement();
    }

    // field countryCode (mapped with "countryCode")
    if (object.countryCode!=null) {
      xmlSerializer.writeStartElement("countryCode");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.countryCode));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field number (mapped with "number")
    if (object.number!=null) {
      xmlSerializer.writeStartElement("number");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.number));
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
  public PhoneNumber parseOnJackson(JsonParser jacksonParser) throws Exception {
    PhoneNumber instance = new PhoneNumber();
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
          case "actionType":
            // field actionType (mapped with "actionType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.actionType=StringUtils.hasText(tempEnum)?ActionType.valueOf(tempEnum):null;
            }
          break;
          case "contactId":
            // field contactId (mapped with "contactId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.contactId=jacksonParser.getText();
            }
          break;
          case "contactName":
            // field contactName (mapped with "contactName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.contactName=jacksonParser.getText();
            }
          break;
          case "countryCode":
            // field countryCode (mapped with "countryCode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.countryCode=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "number":
            // field number (mapped with "number")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.number=jacksonParser.getText();
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
  public PhoneNumber parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    PhoneNumber instance = new PhoneNumber();
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
          case "actionType":
            // field actionType (mapped with "actionType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.actionType=StringUtils.hasText(tempEnum)?ActionType.valueOf(tempEnum):null;
            }
          break;
          case "contactId":
            // field contactId (mapped with "contactId")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.contactId=jacksonParser.getText();
            }
          break;
          case "contactName":
            // field contactName (mapped with "contactName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.contactName=jacksonParser.getText();
            }
          break;
          case "countryCode":
            // field countryCode (mapped with "countryCode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.countryCode=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "number":
            // field number (mapped with "number")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.number=jacksonParser.getText();
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
  public PhoneNumber parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    PhoneNumber instance = new PhoneNumber();
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
                case "actionType":
                  // property actionType (mapped on "actionType")
                  instance.actionType=ActionType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "contactId":
                  // property contactId (mapped on "contactId")
                  instance.contactId=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "contactName":
                  // property contactName (mapped on "contactName")
                  instance.contactName=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "countryCode":
                  // property countryCode (mapped on "countryCode")
                  instance.countryCode=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "number":
                  // property number (mapped on "number")
                  instance.number=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
  }
