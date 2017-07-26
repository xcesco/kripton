package bind.feat.generichierarchy.case1.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Message
 *
 * @see Message
 */
@BindMap(Message.class)
public class MessageBindMap extends AbstractMapper<Message> {
  @Override
  public int serializeOnJackson(Message object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field channelUid (mapped with "channelUid")
    if (object.getChannelUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("channelUid", object.getChannelUid());
    }

    // field faceUid (mapped with "faceUid")
    if (object.getFaceUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("faceUid", object.getFaceUid());
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.getOwnerUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerUid", object.getOwnerUid());
    }

    // field text (mapped with "text")
    if (object.getText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.getText());
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType().toString());
    }

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field updateTime (mapped with "updateTime")
    fieldCount++;
    jacksonSerializer.writeNumberField("updateTime", object.getUpdateTime());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Message object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field channelUid (mapped with "channelUid")
    if (object.getChannelUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("channelUid", object.getChannelUid());
    }

    // field faceUid (mapped with "faceUid")
    if (object.getFaceUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("faceUid", object.getFaceUid());
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.getOwnerUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("ownerUid", object.getOwnerUid());
    }

    // field text (mapped with "text")
    if (object.getText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.getText());
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType().toString());
    }

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field updateTime (mapped with "updateTime")
    jacksonSerializer.writeStringField("updateTime", PrimitiveUtils.writeLong(object.getUpdateTime()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Message object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("message");
    }

    // Persisted fields:

    // field channelUid (mapped with "channelUid")
    if (object.getChannelUid()!=null) {
      xmlSerializer.writeStartElement("channelUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getChannelUid()));
      xmlSerializer.writeEndElement();
    }

    // field faceUid (mapped with "faceUid")
    if (object.getFaceUid()!=null) {
      xmlSerializer.writeStartElement("faceUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getFaceUid()));
      xmlSerializer.writeEndElement();
    }

    // field ownerUid (mapped with "ownerUid")
    if (object.getOwnerUid()!=null) {
      xmlSerializer.writeStartElement("ownerUid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getOwnerUid()));
      xmlSerializer.writeEndElement();
    }

    // field text (mapped with "text")
    if (object.getText()!=null) {
      xmlSerializer.writeStartElement("text");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getText()));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getType().toString()));
      xmlSerializer.writeEndElement();
    }

    // field uid (mapped with "uid")
    if (object.getUid()!=null) {
      xmlSerializer.writeStartElement("uid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUid()));
      xmlSerializer.writeEndElement();
    }

    // field updateTime (mapped with "updateTime")
    xmlSerializer.writeStartElement("updateTime");
    xmlSerializer.writeLong(object.getUpdateTime());
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Message parseOnJackson(JsonParser jacksonParser) throws Exception {
    Message instance = new Message();
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
          case "channelUid":
            // field channelUid (mapped with "channelUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setChannelUid(jacksonParser.getText());
            }
          break;
          case "faceUid":
            // field faceUid (mapped with "faceUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setFaceUid(jacksonParser.getText());
            }
          break;
          case "ownerUid":
            // field ownerUid (mapped with "ownerUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setOwnerUid(jacksonParser.getText());
            }
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setText(jacksonParser.getText());
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setType(StringUtils.hasText(tempEnum)?MessageType.valueOf(tempEnum):null);
            }
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUid(jacksonParser.getText());
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            instance.setUpdateTime(jacksonParser.getLongValue());
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
  public Message parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Message instance = new Message();
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
          case "channelUid":
            // field channelUid (mapped with "channelUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setChannelUid(jacksonParser.getText());
            }
          break;
          case "faceUid":
            // field faceUid (mapped with "faceUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setFaceUid(jacksonParser.getText());
            }
          break;
          case "ownerUid":
            // field ownerUid (mapped with "ownerUid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setOwnerUid(jacksonParser.getText());
            }
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setText(jacksonParser.getText());
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setType(StringUtils.hasText(tempEnum)?MessageType.valueOf(tempEnum):null);
            }
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setUid(jacksonParser.getText());
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            instance.setUpdateTime(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
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
  public Message parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Message instance = new Message();
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
                case "channelUid":
                  // property channelUid (mapped on "channelUid")
                  instance.setChannelUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "faceUid":
                  // property faceUid (mapped on "faceUid")
                  instance.setFaceUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "ownerUid":
                  // property ownerUid (mapped on "ownerUid")
                  instance.setOwnerUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "text":
                  // property text (mapped on "text")
                  instance.setText(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "type":
                  // property type (mapped on "type")
                  instance.setType(MessageType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  instance.setUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "updateTime":
                  // property updateTime (mapped on "updateTime")
                  instance.setUpdateTime(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
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
