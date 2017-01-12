package sqlite.example01;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
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
 * This class is the shared preference binder defined for ChannelMessage
 *
 * @see ChannelMessage
 */
@BindMap(ChannelMessage.class)
public class ChannelMessageBindMap extends AbstractMapper<ChannelMessage> {
  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(ChannelMessage object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field day (mapped with "day")
    if (object.getDay()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("day", object.getDay().toString());
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field image (mapped with "image")
    if (object.getImage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("image", object.getImage());
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
      jacksonSerializer.writeStringField("type", object.getType());
    }

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field updateTime (mapped with "updateTime")
    fieldCount++;
    jacksonSerializer.writeNumberField("updateTime", object.getUpdateTime());

    // field valid (mapped with "valid")
    fieldCount++;
    jacksonSerializer.writeBooleanField("valid", object.isValid());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(ChannelMessage object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field day (mapped with "day")
    if (object.getDay()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("day", object.getDay().toString());
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field image (mapped with "image")
    if (object.getImage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("image", object.getImage());
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
      jacksonSerializer.writeStringField("type", object.getType());
    }

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field updateTime (mapped with "updateTime")
    jacksonSerializer.writeStringField("updateTime", PrimitiveUtils.writeLong(object.getUpdateTime()));

    // field valid (mapped with "valid")
    jacksonSerializer.writeStringField("valid", PrimitiveUtils.writeBoolean(object.isValid()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(ChannelMessage object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("channelMessage");
    }

    // Persisted fields:

    // field day (mapped with "day")
    if (object.getDay()!=null)  {
      xmlSerializer.writeStartElement("day");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDay().toString()));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field image (mapped with "image")
    if (object.getImage()!=null) {
      xmlSerializer.writeStartElement("image");
      xmlSerializer.writeBinary(object.getImage());
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
    if (object.getType()!=null) {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getType()));
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

    // field valid (mapped with "valid")
    xmlSerializer.writeStartElement("valid");
    xmlSerializer.writeBoolean(object.isValid());
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * create new object instance
   */
  @Override
  public ChannelMessage parseOnJackson(JsonParser jacksonParser) throws Exception {
    ChannelMessage instance = new ChannelMessage();
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
          case "day":
            // field day (mapped with "day")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setDay(StringUtils.hasText(tempEnum)?DayType.valueOf(tempEnum):null);
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setImage(jacksonParser.getBinaryValue());
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
              instance.setType(jacksonParser.getText());
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
          case "valid":
            // field valid (mapped with "valid")
            instance.setValid(jacksonParser.getBooleanValue());
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public ChannelMessage parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ChannelMessage instance = new ChannelMessage();
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
          case "day":
            // field day (mapped with "day")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setDay(StringUtils.hasText(tempEnum)?DayType.valueOf(tempEnum):null);
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setImage(Base64Utils.decode(jacksonParser.getValueAsString()));
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
              instance.setType(jacksonParser.getText());
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
          case "valid":
            // field valid (mapped with "valid")
            instance.setValid(PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false));
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public ChannelMessage parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    ChannelMessage instance = new ChannelMessage();
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
                case "day":
                  // property day (mapped on "day")
                  instance.setDay(DayType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "image":
                  // property image (mapped on "image")
                  instance.setImage(xmlParser.getElementAsBinary());
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
                  instance.setType(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  instance.setUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "updateTime":
                  // property updateTime (mapped on "updateTime")
                  instance.setUpdateTime(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "valid":
                  // property valid (mapped on "valid")
                  instance.setValid(PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false));
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
