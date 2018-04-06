package bind.kripton110.model.stage1;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for Image.
 *
 * @see Image
 */
@BindMap(Image.class)
public class ImageBindMap extends AbstractMapper<Image> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Image object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field format (mapped with "format")
    if (object.format!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("format", object.format);
    }

    // field id (mapped with "id")
    if (object.id!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("id", object.id);
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", object.url);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Image object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field format (mapped with "format")
    if (object.format!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("format", object.format);
    }

    // field id (mapped with "id")
    if (object.id!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("id", object.id);
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", object.url);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization.
   *
   * @param object the object
   * @param xmlSerializer the xml serializer
   * @param currentEventType the current event type
   * @throws Exception the exception
   */
  @Override
  public void serializeOnXml(Image object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("image");
    }

    // Persisted fields:

    // field description (mapped with "description")
    if (object.description!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.description));
      xmlSerializer.writeEndElement();
    }

    // field format (mapped with "format")
    if (object.format!=null) {
      xmlSerializer.writeStartElement("format");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.format));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    if (object.id!=null) {
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.id));
      xmlSerializer.writeEndElement();
    }

    // field url (mapped with "url")
    if (object.url!=null) {
      xmlSerializer.writeStartElement("url");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.url));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the image
   * @throws Exception the exception
   */
  @Override
  public Image parseOnJackson(JsonParser jacksonParser) throws Exception {
    Image instance = new Image();
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
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "format":
            // field format (mapped with "format")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.format=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.id=jacksonParser.getText();
            }
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the image
   * @throws Exception the exception
   */
  @Override
  public Image parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Image instance = new Image();
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
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "format":
            // field format (mapped with "format")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.format=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.id=jacksonParser.getText();
            }
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml.
   *
   * @param xmlParser the xml parser
   * @param currentEventType the current event type
   * @return the image
   * @throws Exception the exception
   */
  @Override
  public Image parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Image instance = new Image();
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
                case "description":
                  // property description (mapped on "description")
                  instance.description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "format":
                  // property format (mapped on "format")
                  instance.format=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "url":
                  // property url (mapped on "url")
                  instance.url=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
