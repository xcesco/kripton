package bind.rss;

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
 * This class is binder map for Image
 *
 * @see Image
 */
@BindMap(Image.class)
public class ImageBindMap extends AbstractMapper<Image> {
  @Override
  public int serializeOnJackson(Image object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    if (object.height!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("height", object.height);
    }

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.link);
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", object.url);
    }

    // field width (mapped with "width")
    if (object.width!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("width", object.width);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Image object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    if (object.height!=null)  {
      jacksonSerializer.writeStringField("height", PrimitiveUtils.writeInteger(object.height));
    }

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.link);
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", object.url);
    }

    // field width (mapped with "width")
    if (object.width!=null)  {
      jacksonSerializer.writeStringField("width", PrimitiveUtils.writeInteger(object.width));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Image object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("image");
    }

    // Persisted fields:

    // field height (mapped with "height")
    if (object.height!=null)  {
      xmlSerializer.writeStartElement("height");
      xmlSerializer.writeInt(object.height);
      xmlSerializer.writeEndElement();
    }

    // field link (mapped with "link")
    if (object.link!=null) {
      xmlSerializer.writeStartElement("link");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.link));
      xmlSerializer.writeEndElement();
    }

    // field title (mapped with "title")
    if (object.title!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.title));
      xmlSerializer.writeEndElement();
    }

    // field url (mapped with "url")
    if (object.url!=null) {
      xmlSerializer.writeStartElement("url");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.url));
      xmlSerializer.writeEndElement();
    }

    // field width (mapped with "width")
    if (object.width!=null)  {
      xmlSerializer.writeStartElement("width");
      xmlSerializer.writeInt(object.width);
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
          case "height":
            // field height (mapped with "height")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.height=jacksonParser.getIntValue();
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=jacksonParser.getText();
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
            }
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=jacksonParser.getText();
            }
          break;
          case "width":
            // field width (mapped with "width")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.width=jacksonParser.getIntValue();
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
          case "height":
            // field height (mapped with "height")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.height=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=jacksonParser.getText();
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
            }
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=jacksonParser.getText();
            }
          break;
          case "width":
            // field width (mapped with "width")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.width=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
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
  public Image parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Image instance = new Image();
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
                case "height":
                  // property height (mapped on "height")
                  instance.height=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                break;
                case "link":
                  // property link (mapped on "link")
                  instance.link=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "title":
                  // property title (mapped on "title")
                  instance.title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "url":
                  // property url (mapped on "url")
                  instance.url=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "width":
                  // property width (mapped on "width")
                  instance.width=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
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
