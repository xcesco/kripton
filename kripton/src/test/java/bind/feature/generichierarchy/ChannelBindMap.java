package bind.feature.generichierarchy;

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
 * This class is binder map for Channel
 *
 * @see Channel
 */
@BindMap(Channel.class)
public class ChannelBindMap extends AbstractMapper<Channel> {
  @Override
  public int serializeOnJackson(Channel object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field imageSize (mapped with "imageSize")
    fieldCount++;
    jacksonSerializer.writeNumberField("imageSize", object.getImageSize());

    // field imageType (mapped with "imageType")
    if (object.getImageType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imageType", object.getImageType());
    }

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
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
  public int serializeOnJacksonAsString(Channel object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field imageSize (mapped with "imageSize")
    jacksonSerializer.writeStringField("imageSize", PrimitiveUtils.writeLong(object.getImageSize()));

    // field imageType (mapped with "imageType")
    if (object.getImageType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imageType", object.getImageType());
    }

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
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
  public void serializeOnXml(Channel object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("channel");
    }

    // Persisted fields:

    // field imageSize (mapped with "imageSize")
    xmlSerializer.writeStartElement("imageSize");
    xmlSerializer.writeLong(object.getImageSize());
    xmlSerializer.writeEndElement();

    // field imageType (mapped with "imageType")
    if (object.getImageType()!=null) {
      xmlSerializer.writeStartElement("imageType");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getImageType()));
      xmlSerializer.writeEndElement();
    }

    // field name (mapped with "name")
    if (object.getName()!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
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

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Channel parseOnJackson(JsonParser jacksonParser) throws Exception {
    Channel instance = new Channel();
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
          case "imageSize":
            // field imageSize (mapped with "imageSize")
            instance.setImageSize(jacksonParser.getLongValue());
          break;
          case "imageType":
            // field imageType (mapped with "imageType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setImageType(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
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
  public Channel parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Channel instance = new Channel();
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
          case "imageSize":
            // field imageSize (mapped with "imageSize")
            instance.setImageSize(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "imageType":
            // field imageType (mapped with "imageType")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setImageType(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
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
  public Channel parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Channel instance = new Channel();
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
                case "imageSize":
                  // property imageSize (mapped on "imageSize")
                  instance.setImageSize(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "imageType":
                  // property imageType (mapped on "imageType")
                  instance.setImageType(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
