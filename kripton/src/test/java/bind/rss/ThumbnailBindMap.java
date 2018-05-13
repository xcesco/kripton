package bind.rss;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Thumbnail
 *
 * @see Thumbnail
 */
@BindMap(Thumbnail.class)
public class ThumbnailBindMap extends AbstractMapper<Thumbnail> {
  @Override
  public int serializeOnJackson(Thumbnail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    if (object.height!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("height", object.height);
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", UrlUtils.write(object.url));
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
  public int serializeOnJacksonAsString(Thumbnail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    if (object.height!=null)  {
      jacksonSerializer.writeStringField("height", PrimitiveUtils.writeInteger(object.height));
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", UrlUtils.write(object.url));
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
  public void serializeOnXml(Thumbnail object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("thumbnail");
    }

    // Persisted fields:

    // field height (mapped with "height")
    if (object.height!=null)  {
      xmlSerializer.writeAttribute("height", PrimitiveUtils.writeInteger(object.height));
    }

    // field url (mapped with "url")
    if (object.url!=null)  {
      xmlSerializer.writeAttribute("url", UrlUtils.write(object.url));
    }

    // field width (mapped with "width")
    if (object.width!=null)  {
      xmlSerializer.writeAttribute("width", PrimitiveUtils.writeInteger(object.width));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Thumbnail parseOnJackson(JsonParser jacksonParser) throws Exception {
    Thumbnail instance = new Thumbnail();
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
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=UrlUtils.read(jacksonParser.getText());
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
  public Thumbnail parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Thumbnail instance = new Thumbnail();
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
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.url=UrlUtils.read(jacksonParser.getText());
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
  public Thumbnail parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Thumbnail instance = new Thumbnail();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "height":
            // field height (mapped by "height")
            instance.height=PrimitiveUtils.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
          break;
          case "url":
            // field url (mapped by "url")
            instance.url=UrlUtils.read(xmlParser.getAttributeValue(attributeIndex));
          break;
          case "width":
            // field width (mapped by "width")
            instance.width=PrimitiveUtils.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
          break;
          default:
          break;
      }
    }

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
            // No property to manage here
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
