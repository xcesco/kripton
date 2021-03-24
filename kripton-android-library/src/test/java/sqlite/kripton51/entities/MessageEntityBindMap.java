package sqlite.kripton51.entities;

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
import sqlite.kripton51.internal.MessageType;

/**
 * This class is binder map for MessageEntity
 *
 * @see MessageEntity
 */
@BindMap(MessageEntity.class)
public class MessageEntityBindMap extends AbstractMapper<MessageEntity> {
  @Override
  public int serializeOnJackson(MessageEntity object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field channelId (mapped with "channelId")
    fieldCount++;
    jacksonSerializer.writeNumberField("channelId", object.channelId);

    // field channelUid (mapped with "channelUid")
    if (object.channelUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("channelUid", object.channelUid);
    }

    // field faceUid (mapped with "faceUid")
    if (object.faceUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("faceUid", object.faceUid);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field ownerType (mapped with "ownerType")
    if (object.ownerType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerType", object.ownerType.toString());
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.ownerUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerUid", object.ownerUid);
    }

    // field text (mapped with "text")
    if (object.text!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.text);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.type.toString());
    }

    // field uid (mapped with "uid")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.uid);
    }

    // field updateTime (mapped with "updateTime")
    fieldCount++;
    jacksonSerializer.writeNumberField("updateTime", object.updateTime);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(MessageEntity object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field channelId (mapped with "channelId")
    jacksonSerializer.writeStringField("channelId", PrimitiveUtils.writeLong(object.channelId));

    // field channelUid (mapped with "channelUid")
    if (object.channelUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("channelUid", object.channelUid);
    }

    // field faceUid (mapped with "faceUid")
    if (object.faceUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("faceUid", object.faceUid);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field ownerType (mapped with "ownerType")
    if (object.ownerType!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerType", object.ownerType.toString());
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.ownerUid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerUid", object.ownerUid);
    }

    // field text (mapped with "text")
    if (object.text!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.text);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.type.toString());
    }

    // field uid (mapped with "uid")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.uid);
    }

    // field updateTime (mapped with "updateTime")
    jacksonSerializer.writeStringField("updateTime", PrimitiveUtils.writeLong(object.updateTime));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(MessageEntity object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("messageEntity");
    }

    // Persisted fields:

    // field channelId (mapped with "channelId")
    xmlSerializer.writeStartElement("channelId");
    xmlSerializer.writeLong(object.channelId);
    xmlSerializer.writeEndElement();

    // field channelUid (mapped with "channelUid")
    if (object.channelUid!=null) {
      xmlSerializer.writeStartElement("channelUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.channelUid));
      xmlSerializer.writeEndElement();
    }

    // field faceUid (mapped with "faceUid")
    if (object.faceUid!=null) {
      xmlSerializer.writeStartElement("faceUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.faceUid));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field ownerType (mapped with "ownerType")
    if (object.ownerType!=null)  {
      xmlSerializer.writeStartElement("ownerType");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.ownerType.toString()));
      xmlSerializer.writeEndElement();
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.ownerUid!=null) {
      xmlSerializer.writeStartElement("ownerUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.ownerUid));
      xmlSerializer.writeEndElement();
    }

    // field text (mapped with "text")
    if (object.text!=null) {
      xmlSerializer.writeStartElement("text");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.text));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.type.toString()));
      xmlSerializer.writeEndElement();
    }

    // field uid (mapped with "uid")
    if (object.uid!=null) {
      xmlSerializer.writeStartElement("uid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.uid));
      xmlSerializer.writeEndElement();
    }

    // field updateTime (mapped with "updateTime")
    xmlSerializer.writeStartElement("updateTime");
    xmlSerializer.writeLong(object.updateTime);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public MessageEntity parseOnJackson(JsonParser jacksonParser) throws Exception {
    MessageEntity instance = new MessageEntity();
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
          case "channelId":
            // field channelId (mapped with "channelId")
            instance.channelId=jacksonParser.getLongValue();
          break;
          case "channelUid":
            // field channelUid (mapped with "channelUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.channelUid=jacksonParser.getText();
            }
          break;
          case "faceUid":
            // field faceUid (mapped with "faceUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.faceUid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "ownerType":
            // field ownerType (mapped with "ownerType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.ownerType=StringUtils.hasText(tempEnum)?OwnerType.valueOf(tempEnum):null;
            }
          break;
          case "ownerUid":
            // field ownerUid (mapped with "ownerUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.ownerUid=jacksonParser.getText();
            }
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.text=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.type=StringUtils.hasText(tempEnum)?MessageType.valueOf(tempEnum):null;
            }
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            instance.updateTime=jacksonParser.getLongValue();
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
  public MessageEntity parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    MessageEntity instance = new MessageEntity();
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
          case "channelId":
            // field channelId (mapped with "channelId")
            instance.channelId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "channelUid":
            // field channelUid (mapped with "channelUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.channelUid=jacksonParser.getText();
            }
          break;
          case "faceUid":
            // field faceUid (mapped with "faceUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.faceUid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "ownerType":
            // field ownerType (mapped with "ownerType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.ownerType=StringUtils.hasText(tempEnum)?OwnerType.valueOf(tempEnum):null;
            }
          break;
          case "ownerUid":
            // field ownerUid (mapped with "ownerUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.ownerUid=jacksonParser.getText();
            }
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.text=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.type=StringUtils.hasText(tempEnum)?MessageType.valueOf(tempEnum):null;
            }
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            instance.updateTime=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public MessageEntity parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    MessageEntity instance = new MessageEntity();
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
                case "channelId":
                  // property channelId (mapped on "channelId")
                  instance.channelId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "channelUid":
                  // property channelUid (mapped on "channelUid")
                  instance.channelUid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "faceUid":
                  // property faceUid (mapped on "faceUid")
                  instance.faceUid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "ownerType":
                  // property ownerType (mapped on "ownerType")
                  instance.ownerType=OwnerType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "ownerUid":
                  // property ownerUid (mapped on "ownerUid")
                  instance.ownerUid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "text":
                  // property text (mapped on "text")
                  instance.text=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "type":
                  // property type (mapped on "type")
                  instance.type=MessageType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  instance.uid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "updateTime":
                  // property updateTime (mapped on "updateTime")
                  instance.updateTime=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
